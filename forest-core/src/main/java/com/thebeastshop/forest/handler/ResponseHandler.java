package com.thebeastshop.forest.handler;

import com.thebeastshop.forest.http.ForestRequest;
import com.thebeastshop.forest.http.ForestResponse;

import java.lang.reflect.Type;


/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2017-05-19 16:49
 */
public interface ResponseHandler {

    void handleSync(ForestRequest request, ForestResponse response);

    Object handleResultType(ForestRequest request, ForestResponse response);

    Object handleResultType(ForestRequest request, ForestResponse response, Type resultType, Class resultClass);

    Object handleSuccess(Object resultData, ForestRequest request, ForestResponse response);

    void handleError(ForestRequest request, ForestResponse response);

    void handleError(ForestRequest request, ForestResponse response, Exception ex);

    Object handleResult(Object resultData);

    Class getOnSuccessClassGenericType();

    Type getReturnType();

}
