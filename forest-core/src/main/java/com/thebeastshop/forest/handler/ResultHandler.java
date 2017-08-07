package com.thebeastshop.forest.handler;

import com.thebeastshop.forest.http.ForestRequest;
import com.thebeastshop.forest.http.ForestResponse;

import java.lang.reflect.Type;

/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2016-05-04
 */
public abstract class ResultHandler {

    public abstract Object getResult(ForestRequest request, ForestResponse response, Type resultType, Class resultClass);
}
