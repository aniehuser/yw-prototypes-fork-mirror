package org.yesworkflow.save.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RunDto {

    @SerializedName("username")
    public String username;
    @SerializedName("title")
    public String title;
    @SerializedName("description")
    public String description;
    @SerializedName("model")
    public String model;
    @SerializedName("modelChecksum")
    public String modelChecksum;
    @SerializedName("graph")
    public String graph;
    @SerializedName("tags")
    public List<String> tags;
    @SerializedName("scripts")
    public List<ScriptDto> scripts;
    @SerializedName("files")
    public List<FileDto> files;
    @SerializedName("programBlocks")
    public List<ProgramBlockDto> programBlocks;
    @SerializedName("data")
    public List<DataDto> data;
    @SerializedName("ports")
    public List<PortDto> ports;
    @SerializedName("channels")
    public List<ChannelDto> channels;

    public RunDto(String username,
                  String title,
                  String description,
                  String model,
                  String modelChecksum,
                  String graph,
                  List<String> tags,
                  List<ScriptDto> scripts,
                  List<FileDto> files,
                  List<ProgramBlockDto> programBlocks,
                  List<DataDto> data,
                  List<PortDto> ports,
                  List<ChannelDto> channels)
    {
        this.username = username;
        this.title = title;
        this.description = description;
        this.model = model;
        this.modelChecksum = modelChecksum;
        this.graph = graph;
        this.tags = tags;
        this.scripts = scripts;
        this.files = files;
        this.programBlocks = programBlocks;
        this.data = data;
        this.ports = ports;
        this.channels = channels;
    }

    public static class Builder
    {
        public String username;
        public String title;
        public String description;
        public String model;
        public String modelChecksum;
        public String graph;
        public List<String> tags;
        public List<ScriptDto> scripts;
        public List<FileDto> files;
        public List<ProgramBlockDto> programBlocks;
        public List<DataDto> data;
        public List<PortDto> ports;
        public List<ChannelDto> channels;

        public Builder(String username, String model, String modelChecksum, String graph, List<ScriptDto> scripts)
        {
            this.username = username;
            this.model = model;
            this.modelChecksum = modelChecksum;
            this.graph = graph;
            this.scripts = scripts;
        }

        public Builder setTitle(String title)
        {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description)
        {
            this.description = description;
            return this;
        }

        public Builder setTags(List<String> tags)
        {
            this.tags = tags;
            return this;
        }

        public Builder setFiles(List<FileDto> files)
        {
            this.files = files;
            return this;
        }

        public Builder setProgramBlocks(List<ProgramBlockDto> programBlocks)
        {
            this.programBlocks = programBlocks;
            return this;
        }

        public Builder setData(List<DataDto> data)
        {
            this.data = data;
            return this;
        }

        public Builder setPorts(List<PortDto> ports)
        {
            this.ports = ports;
            return this;
        }

        public Builder setChannels(List<ChannelDto> channels)
        {
            this.channels = channels;
            return this;
        }

        public RunDto build()
        {
            return new RunDto(this);
        }
    }

    public RunDto(Builder builder)
    {
        this.username=builder.username;
        this.title=builder.title;
        this.description=builder.description;
        this.model=builder.model;
        this.modelChecksum =builder.modelChecksum;
        this.graph=builder.graph;
        this.tags=builder.tags;
        this.scripts=builder.scripts;
        this.files=builder.files;
        this.programBlocks=builder.programBlocks;
        this.data=builder.data;
        this.ports=builder.ports;
        this.channels=builder.channels;
    }
}
