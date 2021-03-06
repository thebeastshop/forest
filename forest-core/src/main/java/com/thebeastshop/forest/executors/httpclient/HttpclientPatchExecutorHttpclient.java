package com.thebeastshop.forest.executors.httpclient;

import com.thebeastshop.forest.executors.httpclient.request.HttpclientRequestSender;
import com.thebeastshop.forest.executors.httpclient.response.HttpclientResponseHandler;
import com.thebeastshop.forest.executors.url.URLBuilder;
import com.thebeastshop.forest.http.ForestRequest;
import org.apache.http.client.methods.HttpPut;

/**
 * @author gongjun
 * @since 2016-05-24
 */
public class HttpclientPatchExecutorHttpclient extends AbstractHttpclientEntityExecutor<HttpclientPatchExecutorHttpclient.HttpPatch> {

    private final static HttpclientRequestProvider<HttpPatch> httpPatchProvider =
            new HttpclientRequestProvider<HttpPatch>() {
                @Override
                public HttpPatch getRequest(String url) {
                    return new HttpPatch(url);
                }
            };

    @Override
    protected HttpclientRequestProvider<HttpPatch> getRequestProvider() {
        return httpPatchProvider;
    }

    @Override
    protected URLBuilder getURLBuilder() {
        return URLBuilder.getSimpleURLBuilder();
    }

    public HttpclientPatchExecutorHttpclient(ForestRequest request, HttpclientResponseHandler httpclientResponseHandler, HttpclientRequestSender requestSender) {
        super(request, httpclientResponseHandler, requestSender);
    }


    public static class HttpPatch extends HttpPut {

        public HttpPatch(String url) {
            super(url);
        }

        @Override
        public String getMethod(){
            return "PATCH";
        }
    }

}



