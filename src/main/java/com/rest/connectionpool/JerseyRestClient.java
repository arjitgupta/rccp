package com.rest.connectionpool;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * Created with IntelliJ IDEA.
 * User: arjit.gupta
 * Date: 12/12/13
 * Time: 12:18 PM
 */
public class JerseyRestClient implements RestClient{

    private final Client client;
    private final String url;

    @Override
    public WebResource getWebResource() {
        return client.resource(url);
    }

    public JerseyRestClient(Client client, String url) {
                this.client = client;
                this.url = url;
            }
}
