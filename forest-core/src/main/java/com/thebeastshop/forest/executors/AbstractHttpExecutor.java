package com.thebeastshop.forest.executors;

import com.thebeastshop.forest.handler.ResponseHandler;
import com.thebeastshop.forest.http.ForestResponse;
import com.thebeastshop.forest.executors.httpclient.request.HttpclientRequestSender;
import com.thebeastshop.forest.http.ForestRequest;

/**
 * @author gongjun
 * @since 2016-05-18
 */
public abstract class AbstractHttpExecutor implements HttpExecutor {


    protected final ForestRequest request;

    protected HttpclientRequestSender requestSender;

    protected ForestResponse response;

    public ForestRequest getRequest() {
        return request;
    }

    public AbstractHttpExecutor(ForestRequest request, HttpclientRequestSender requestSender) {
        this.request = request;
        this.requestSender = requestSender;
    }

    public ForestResponse getResponse() {
        return response;
    }

    public void setResponse(ForestResponse response) {
        this.response = response;
    }

    public abstract void execute(ResponseHandler responseHandler);

    public abstract void close();

}
