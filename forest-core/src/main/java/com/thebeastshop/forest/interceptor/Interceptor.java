package com.thebeastshop.forest.interceptor;

import com.thebeastshop.forest.exceptions.ForestRuntimeException;
import com.thebeastshop.forest.http.ForestResponse;
import com.thebeastshop.forest.http.ForestRequest;

/**
 * @author gongjun[dt_flys@hotmail.com]
 * @since 2016-06-26
 */
public interface Interceptor<T> {

    boolean beforeExecute(ForestRequest request);

    void onSuccess(T data, ForestRequest request, ForestResponse response);

    void onError(ForestRuntimeException ex, ForestRequest request, ForestResponse response);

    void afterExecute(ForestRequest request, ForestResponse response);

}
