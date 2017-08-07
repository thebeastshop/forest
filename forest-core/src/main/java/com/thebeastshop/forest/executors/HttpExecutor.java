package com.thebeastshop.forest.executors;

import com.thebeastshop.forest.handler.ResponseHandler;
import com.thebeastshop.forest.http.ForestRequest;
import com.thebeastshop.forest.http.ForestResponse;

/**
 * @author gongjun[dt_flys@hotmail.com]
 * @since 2017-04-20 13:33
 */
public interface HttpExecutor {

    ForestRequest getRequest();

    ForestResponse getResponse();


    void execute(ResponseHandler responseHandler);

    void close();
}
