package com.thebeastshop.forest.http.client;

import com.thebeastshop.forest.annotation.BaseURL;
import com.thebeastshop.forest.annotation.Request;

/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2017-05-17 17:24
 */
@BaseURL("${baseURL}")
public interface BaseURLVarClient {

    @Request(
            url = "/hello/user?username=foo",
            headers = {"Accept:text/plan"}
    )
    String simpleGet();

}
