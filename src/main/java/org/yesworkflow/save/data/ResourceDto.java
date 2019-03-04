package org.yesworkflow.save.data;

import com.google.gson.annotations.SerializedName;

public class ResourceDto
{
    @SerializedName("id")
    public int id;
    @SerializedName("data")
    public int data;
    @SerializedName("uri")
    public String uri;

    public ResourceDto(int id, int data, String uri)
    {
        this.id = id;
        this.data = data;
        this.uri = uri;
    }
}
