package org.yesworkflow.save;

import org.yesworkflow.exceptions.YwSaveException;
import org.yesworkflow.save.data.LoginDto;
import org.yesworkflow.save.data.RunDto;
import org.yesworkflow.save.data.ScriptDto;
import org.yesworkflow.save.response.LoginResponse;
import org.yesworkflow.save.response.PingResponse;
import org.yesworkflow.save.response.SaveResponse;
import org.yesworkflow.save.response.UpdateResponse;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class HttpSaver implements Saver
{
    IYwSerializer ywSerializer = null;
    PrintStream out = null;
    PrintStream err = null;
    IClient client = null;
    Integer loginRetries = 3;
    Integer workflowId = null;
    String baseUrl = "http://localhost:8000/";
    String username = null;
    String title = "Title";
    String description = "Description";
    String graph = "";
    String model = "";
    String modelChecksum = "";
    String recon = "";
    List<String> tags = new ArrayList<String>();
    List<ScriptDto> scripts = null;


    public HttpSaver(IYwSerializer ywSerializer, PrintStream out, PrintStream err)
    {
        this.out = out;
        this.err = err;
        this.ywSerializer = ywSerializer;
        client = new YwClient(baseUrl, ywSerializer);
    }

    public Saver build(String model, String graph, String recon, List<String> sourceCodeList, List<String> sourcePaths)
    {
        this.model = model;
        this.graph = graph;
        this.recon = recon;
        this.scripts = new ArrayList<>();
        for (int i = 0; i < sourceCodeList.size(); i++)
        {
            String checksum = Hash.getStringHash(sourceCodeList.get(i));
            ScriptDto scriptDto = new ScriptDto(sourcePaths.get(i), sourceCodeList.get(i), checksum);
            scripts.add(scriptDto);
        }
        return this;
    }

    public Saver save() throws Exception
    {
        // TODO:: make model string representation and take checksum.
        modelChecksum = scripts.get(0).checksum;

        RunDto run = new RunDto.Builder(username, model, modelChecksum, graph, recon, scripts)
                                .setTitle(title)
                                .setDescription(description)
                                .setTags(tags)
                                .build();

        String message = "Succesfully uploaded this run to %s.\nWorkflow ID: %d\nVersion: %d\nRun Number: %d";
        if(workflowId == null) {
            SaveResponse response = client.SaveRun(run);
            message = String.format(message,
                                    baseUrl,
                                    response.ResponseObject.workflowId,
                                    response.ResponseObject.versionNumber,
                                    response.ResponseObject.runNumber);
        }
        else {
            UpdateResponse response = client.UpdateWorkflow(workflowId, run);
            String newVersionMessage = "\nChanges to the workflow script created a new version of your workflow on the server.";
            message = String.format(message,
                                    baseUrl,
                                    response.ResponseObject.workflowId,
                                    response.ResponseObject.versionNumber,
                                    response.ResponseObject.runNumber);

            if(response.ResponseObject.newVersion)
                message = message + newVersionMessage;
        }

        System.out.println(message);

        return this;
    }

    public Saver configure(Map<String, Object> config) throws Exception {
        if (config != null) {
            for (Map.Entry<String, Object> entry : config.entrySet()) {
                configure(entry.getKey(), entry.getValue());
            }
        }
        return this;
    }

    public Saver login() throws Exception
    {
        try {
            client.Ping();
        } catch(YwSaveException e)
        {
            throw new YwSaveException("Error connecting to server " + baseUrl + ":\n\t" + e.getMessage());
        }

        out.println("Enter credentials for " + baseUrl);
        LoginResponse loginResponse = null;
        for(int i=0; i<loginRetries; i++)
        {
            loginResponse = tryLogin(username);
            if(loginResponse != null)
                break;
            out.println("Incorrect username password combination.");
        }

        if(loginResponse == null)
            throw new YwSaveException("");

        return this;
    }

    private LoginResponse tryLogin(String username)
    {
        LoginResponse loginResponse = null;
        try(Scanner scanner = new Scanner(System.in))
        {
            if(username == null)
            {
                out.print("Username: ");
                username = scanner.nextLine();
            }
            else
            {
                out.println("Enter password for " + username + ".");
            }

            out.print("Password: ");
            String password = scanner.nextLine();
            LoginDto loginDto = new LoginDto.Builder(username, password)
                                            .build();
            loginResponse = client.Login(loginDto);
        } catch(YwSaveException e)
        { // We want to suppress this error and continue execution of the program
        }
        return loginResponse;
    }

    public Saver configure(String key, Object value) throws Exception {
        switch(key.toLowerCase())
        {
            case "serveraddress":
                baseUrl = formatUrl((String) value);
                break;
            case "username":
                username = (String) value;
                break;
            case "workflow":
                workflowId = Integer.parseInt((String) value);
                break;
            case "title":
                title = (String) value;
                break;
            case "description":
                description = (String) value;
                break;
            case "tags" :
                String valTags = (String) value;
                tags = new ArrayList<>(Arrays.asList(valTags.split("\\s*,\\s*")));
                break;
            default:
                break;
        }

        return this;
    }

    private String formatUrl(String url)
    {
        if(!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;

        if(!url.endsWith("/"))
            url = url + "/";

        return url;
    }
}
