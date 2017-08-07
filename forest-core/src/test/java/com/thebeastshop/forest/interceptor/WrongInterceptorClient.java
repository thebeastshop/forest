package com.thebeastshop.forest.interceptor;

import com.thebeastshop.forest.annotation.Request;
import com.thebeastshop.forest.Forest;

/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2017-05-18 18:10
 */
public interface WrongInterceptorClient {

    @Request(
            url = "http://localhost:5000/hello/user?username=foo",
            headers = {"Accept:text/plan"},
            interceptor = Forest.class
    )
    String wrongClass();

}
