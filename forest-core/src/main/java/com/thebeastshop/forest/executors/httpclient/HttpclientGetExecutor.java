package com.thebeastshop.forest.executors.httpclient;

import com.thebeastshop.forest.executors.httpclient.request.HttpclientRequestSender;
import com.thebeastshop.forest.executors.httpclient.response.HttpclientResponseHandler;
import com.thebeastshop.forest.http.ForestRequest;
import org.apache.http.client.methods.HttpGet;
import com.thebeastshop.forest.executors.url.URLBuilder;

/**
 * @author gongjun
 * @since 2016-05-24
 */
public class HttpclientGetExecutor extends AbstractHttpclientExecutor<HttpGet> {

    private final static HttpclientRequestProvider<HttpGet> httpGetProvider =
            new HttpclientRequestProvider<HttpGet>() {
        @Override
        public HttpGet getRequest(String url) {
            return new HttpGet(url);
        }
    };

    @Override
    protected HttpclientRequestProvider<HttpGet> getRequestProvider() {
        return httpGetProvider;
    }

    @Override
    protected URLBuilder getURLBuilder() {
        return URLBuilder.getQueryableURLBuilder();
    }

    public HttpclientGetExecutor(ForestRequest request, HttpclientResponseHandler httpclientResponseHandler, HttpclientRequestSender requestSender) {
        super(request, httpclientResponseHandler, requestSender);
    }


}
