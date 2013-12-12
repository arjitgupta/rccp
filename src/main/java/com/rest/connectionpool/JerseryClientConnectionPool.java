package com.rest.connectionpool;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.client.apache.ApacheHttpClient;
import com.sun.jersey.client.apache.ApacheHttpClientHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;



/**
 * Created with IntelliJ IDEA.
 * User: arjit.gupta
 * Date: 12/12/13
 * Time: 12:10 PM
 */
public class JerseryClientConnectionPool {
    private JerseryClientConnectionPool() {}

    private RestClient init( RestClientConnectionPoolManager.Builder restConnectionPoolBuilder){
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = connectionManager.getParams();
        params.setConnectionTimeout(restConnectionPoolBuilder.getConnectionPerHost());
        params.setSoTimeout(restConnectionPoolBuilder.getSoTimeout());
        params.setDefaultMaxConnectionsPerHost(restConnectionPoolBuilder.getMaxTotalConnection());
        params.setMaxTotalConnections(restConnectionPoolBuilder.getMaxTotalConnection());
        ClientConfig clientConfig = restConnectionPoolBuilder.getClientConfig();
        Client client = new ApacheHttpClient(new ApacheHttpClientHandler(new HttpClient(connectionManager), clientConfig));
        return new JerseyRestClient(client,restConnectionPoolBuilder.getUrl());
    }

    public static RestClient getInstance(RestClientConnectionPoolManager.Builder restConnectionPoolBuilder){
        JerseryClientConnectionPool restConnectionPool = new JerseryClientConnectionPool();
        return restConnectionPool.init(restConnectionPoolBuilder);
    }

}
