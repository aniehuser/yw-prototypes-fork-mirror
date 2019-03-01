package org.yesworkflow.recon;

public class Resource {
    
    public final Integer id;
    public final String uri;
    public Long data;

    
    public Resource(Integer id, String uri) {
        this.id = id;
        this.uri = uri;
        this.data = null;
    }

    public Resource(Integer id, String uri, Long data) {
        this.id = id;
        this.uri = uri;
        this.data = data;
    }
}
