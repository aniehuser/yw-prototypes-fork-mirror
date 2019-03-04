package org.yesworkflow.save.data;

import com.google.gson.annotations.SerializedName;
import org.yesworkflow.recon.Resource;

public class ResourceDto
{
    @SerializedName("id")
    public Integer id;
    @SerializedName("data")
    public Long data;
    @SerializedName("uri")
    public String uri;

    public ResourceDto(Integer id, Long data, String uri)
    {
        this.id = id;
        this.data = data;
        this.uri = uri;
    }

    public ResourceDto(Resource resource)
    {
        this.id = resource.id;
        this.data = resource.data;
        this.uri = resource.uri;
    }
}
