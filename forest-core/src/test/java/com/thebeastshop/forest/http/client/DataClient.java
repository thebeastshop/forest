package com.thebeastshop.forest.http.client;

import com.thebeastshop.forest.annotation.Request;
import com.thebeastshop.forest.annotation.DataParam;

import java.util.Map;

/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2017-06-06 16:11
 */
public interface DataClient {

    @Request(
            url = "http://localhost:3000/hello/data",
            headers = {"Accept:text/plan"},
            dataType = "json"
    )
    Map<String, Object> getData(@DataParam("type") String type);

}
