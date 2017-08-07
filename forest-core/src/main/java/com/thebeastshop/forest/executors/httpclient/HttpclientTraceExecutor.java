package com.thebeastshop.forest.executors.httpclient;

import com.thebeastshop.forest.executors.httpclient.response.HttpclientResponseHandler;
import com.thebeastshop.forest.http.ForestRequest;
import org.apache.http.client.methods.HttpTrace;
import com.thebeastshop.forest.executors.httpclient.request.HttpclientRequestSender;
import com.thebeastshop.forest.executors.url.URLBuilder;

/**
 * @author gongjun[dt_flys@hotmail.com]
 * @since 2017-04-20 14:45
 */
public class HttpclientTraceExecutor extends AbstractHttpclientExecutor<HttpTrace> {

    private final static HttpclientRequestProvider<HttpTrace> httpTraceProvider =
            new HttpclientRequestProvider<HttpTrace>() {
                @Override
                public HttpTrace getRequest(String url) {
                    return new HttpTrace(url);
                }
            };

    @Override
    protected HttpclientRequestProvider<HttpTrace> getRequestProvider() {
        return httpTraceProvider;
    }

    @Override
    protected URLBuilder getURLBuilder() {
        return URLBuilder.getQueryableURLBuilder();
    }

    public HttpclientTraceExecutor(ForestRequest request, HttpclientResponseHandler httpclientResponseHandler, HttpclientRequestSender requestSender) {
        super(request, httpclientResponseHandler, requestSender);
    }

}
