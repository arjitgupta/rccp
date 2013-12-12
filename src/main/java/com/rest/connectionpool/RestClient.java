package com.rest.connectionpool;

import com.sun.jersey.api.client.WebResource;

/**
 * Created with IntelliJ IDEA.
 * User: arjit.gupta
 * Date: 12/12/13
 * Time: 12:15 PM
 */
public interface RestClient  {
    WebResource getWebResource();
}
