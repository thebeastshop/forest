package com.thebeastshop.forest.executors.httpclient.request;

import com.thebeastshop.forest.executors.httpclient.response.HttpclientForestResponseFactory;
import com.thebeastshop.forest.http.ForestRequest;
import com.thebeastshop.forest.http.ForestResponse;
import com.thebeastshop.forest.http.ForestResponseFactory;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import com.thebeastshop.forest.executors.httpclient.conn.HttpclientConnectionManager;
import com.thebeastshop.forest.executors.httpclient.response.HttpclientResponseHandler;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2017-07-21 15:49
 */
public class AsyncHttpclientRequestSender extends AbstractHttpclientRequestSender {


    public AsyncHttpclientRequestSender(HttpclientConnectionManager connectionManager, ForestRequest request) {
        super(connectionManager, request);
    }

    @Override
    public void sendRequest(final ForestRequest request, final HttpclientResponseHandler responseHandler, final HttpUriRequest httpRequest) throws IOException {
        CloseableHttpAsyncClient client = connectionManager.getHttpAsyncClient();
        client.start();
        final ForestResponseFactory forestResponseFactory = new HttpclientForestResponseFactory();

        final Future<HttpResponse> future = client.execute(httpRequest, new FutureCallback<HttpResponse>() {
            public void completed(final HttpResponse httpResponse) {
                ForestResponse response = forestResponseFactory.createResponse(request, httpResponse);
                if (response.isSuccess()) {
                    responseHandler.handleSuccess(response);
                }
                else {
                    responseHandler.handleError(response);
                }
            }

            public void failed(final Exception ex) {
                ForestResponse response = forestResponseFactory.createResponse(request, null);
                responseHandler.handleError(response, ex);
            }

            public void cancelled() {
            }
        });

        responseHandler.handleFuture(future, forestResponseFactory);

    }
}
