package com.rest.connectionpool;

/**
 * Created with IntelliJ IDEA.
 * User: arjit.gupta
 * Date: 12/12/13
 * Time: 12:17 PM
 */
public class RestClientFactory {
    private RestClientFactory(){}
    
                public static RestClient getInstance(RestClientConnectionPoolManager.Builder builder) throws UnsupportedClientException {
             RestClient restClient;
                if(builder.getRestClientType()==RestClientType.JERSEY){
                        restClient= JerseryClientConnectionPool.getInstance(builder);
                    }else {
                        throw new UnsupportedClientException("Client type not supported");
                    }
        
                        return restClient;
        
                    }
}
