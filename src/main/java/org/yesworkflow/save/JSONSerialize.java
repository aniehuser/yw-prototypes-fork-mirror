package org.yesworkflow.save;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONSerialize {

    public JSONObject getSerialization(String modelOutput, String modelURI, String graphOutput, String graphURI, String reconOutput, String reconURI) {

        JSONObject obj = new JSONObject();

        JSONArray model = new JSONArray();
        model.add("output : " + modelOutput);
        model.add("uri : " + modelURI);

        JSONArray graph = new JSONArray();
        graph.add("output : " + graphOutput);
        graph.add("uri : " + graphURI);

        JSONArray recon = new JSONArray();
        recon.add("output: " + reconOutput);
        recon.add("uri : " + reconURI);

        obj.put("model", model);
        obj.put("graph", graph);
        obj.put("recon", recon);

        return obj;
    }
}