package org.yesworkflow.save.data;

import com.google.gson.annotations.SerializedName;
import org.yesworkflow.recon.Resource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class ResourceDto
{
    @SerializedName("resourceId")
    public Integer id;
    @SerializedName("data")
    public Long data;
    @SerializedName("uri")
    public String uri;
    @SerializedName("name")
    public String name;
    @SerializedName("checksum")
    public String checksum;
    @SerializedName("size")
    public Long size;
    @SerializedName("lastModified")
    public LocalDateTime lastModified;

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

    public ResourceDto(Builder builder)
    {
        this.id = builder.id;
        this.data = builder.data;
        this.uri = builder.uri;
        this.name = builder.name;
        this.checksum = builder.checksum;
        this.size = builder.size;
        this.lastModified = builder.lastModified;
    }

    public static class Builder
    {
        public Integer id;
        public Long data;
        public String uri;
        public String name;
        public String checksum;
        public Long size;
        public LocalDateTime lastModified;

        public Builder(Integer id, Long data, String uri)
        {
            this.id = id;
            this.data = data;
            this.uri = uri;
        }

        public Builder(Resource resource)
        {
            this.id = resource.id;
            this.data = resource.data;
            this.uri = resource.uri;
        }

        public Builder setName(String name)
        {
            this.name = name;
            return this;
        }

        public Builder setChecksum(String checksum)
        {
            this.checksum = checksum;
            return this;
        }

        public Builder setSize(Long size)
        {
            this.size = size;
            return this;
        }

        public Builder setLastModified(LocalDateTime lastModified)
        {
            this.lastModified = lastModified;
            return this;
        }

        public ResourceDto build()
        {
            return new ResourceDto(this);
        }
    }
}
