package com.rest.connectionpool;

import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: arjit.gupta
 * Date: 12/12/13
 * Time: 12:14 PM
 */
public class RestClientConnectionPoolManager {
    private static Map<String,RestClient> poolMap =new ConcurrentHashMap<String, RestClient>();

    private RestClientConnectionPoolManager(){}


    public static RestClient getInstance(RestClientConnectionPoolManager.Builder builder) throws UnsupportedClientException {
        if(poolMap.containsKey(builder.getUrl())){
            synchronized (RestClientConnectionPoolManager.class){
                if(poolMap.containsKey(builder.getUrl())){
                    RestClient client = RestClientFactory.getInstance(builder);
                    poolMap.put(builder.getUrl(),client);
                }
            }
        }
        return poolMap.get(builder.getUrl());

    }

    public static class Builder {

        private int connectionTimeOut = 10* 1000;
        private int soTimeout = 10*1000;
        private int connectionPerHost = 10;
        private int maxTotalConnection = 10;
        private ClientConfig clientConfig;
        private String url;
        private RestClientType restClientType;

        public String getUrl() {
            return url;
        }

        public int getConnectionTimeOut() {
            return connectionTimeOut;
        }

        public Builder setConnectionTimeOut(int connectionTimeOut) {
            this.connectionTimeOut = connectionTimeOut;
            return this;
        }

        public int getSoTimeout() {
            return soTimeout;
        }

        public Builder setSoTimeout(int soTimeout) {
            this.soTimeout = soTimeout;
            return this;
        }

        public int getConnectionPerHost() {
            return connectionPerHost;
        }

        public Builder setConnectionPerHost(int connectionPerHost) {
            this.connectionPerHost = connectionPerHost;
            return this;
        }

        public int getMaxTotalConnection() {
            return maxTotalConnection;
        }

        public Builder setMaxTotalConnection(int maxTotalConnection) {
            this.maxTotalConnection = maxTotalConnection;
            return this;
        }

        public ClientConfig getClientConfig() {
            return clientConfig;
        }

        public Builder setClientConfig(ClientConfig clientConfig) {
            this.clientConfig = clientConfig;
            return this;
        }

        public RestClient build(String serverUrl) throws UnsupportedClientException {
            url = serverUrl;
            return RestClientConnectionPoolManager.getInstance(this);
        }

        public Builder(){
            clientConfig = new DefaultClientConfig();
            clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        }

        public RestClientType getRestClientType() {
            return restClientType;
        }

        public Builder setRestClientType(RestClientType restClientType) {
            this.restClientType = restClientType;
            return this;
        }
    }
}