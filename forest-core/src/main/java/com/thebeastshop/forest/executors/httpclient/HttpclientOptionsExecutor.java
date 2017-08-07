package com.thebeastshop.forest.executors.httpclient;

import com.thebeastshop.forest.executors.httpclient.response.HttpclientResponseHandler;
import com.thebeastshop.forest.http.ForestRequest;
import org.apache.http.client.methods.HttpOptions;
import com.thebeastshop.forest.executors.httpclient.request.HttpclientRequestSender;
import com.thebeastshop.forest.executors.url.URLBuilder;

/**
 * @author gongjun[dt_flys@hotmail.com]
 * @since 2017-04-20 14:44
 */
public class HttpclientOptionsExecutor extends AbstractHttpclientExecutor<HttpOptions> {

    private final static HttpclientRequestProvider<HttpOptions> httpOptionsProvider =
            new HttpclientRequestProvider<HttpOptions>() {
                @Override
                public HttpOptions getRequest(String url) {
                    return new HttpOptions(url);
                }
            };


    @Override
    protected HttpclientRequestProvider<HttpOptions> getRequestProvider() {
        return httpOptionsProvider;
    }

    @Override
    protected URLBuilder getURLBuilder() {
        return URLBuilder.getQueryableURLBuilder();
    }


    public HttpclientOptionsExecutor(ForestRequest request, HttpclientResponseHandler httpclientResponseHandler, HttpclientRequestSender requestSender) {
        super(request, httpclientResponseHandler, requestSender);
    }

}
