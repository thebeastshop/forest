package com.thebeastshop.forest.client;

import com.thebeastshop.forest.annotation.DataVariable;
import com.thebeastshop.forest.model.Coordinate;
import com.thebeastshop.forest.model.Location;
import com.thebeastshop.forest.annotation.Request;
import com.thebeastshop.forest.annotation.DataObject;
import com.thebeastshop.forest.annotation.DataParam;
import com.thebeastshop.forest.model.Result;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 地图服务客户端接口
 * @author gongjun
 * @since 2016-06-01
 */
public interface MapClient {

    @Request(
        url = "http://ditu.amap.com/service/regeo",
        dataType = "json"
    )
    Map getLocation(@DataParam("longitude") BigDecimal longitude, @DataParam("latitude") BigDecimal latitude);


    @Request(
        url = "http://ditu.amap.com/service/regeo",
        dataType = "json"
    )
    Map getLocation(@DataObject Coordinate coordinate);

    @Request(
            url = "http://ditu.amap.com/service/regeo",
            dataType = "json"
    )
    Result<Location> getLocationWithJavaObject(@DataObject Coordinate coordinate);


    @Request(
        url = "http://ditu.amap.com/service/regeo",
        dataType = "json",
        data = {
            "longitude=${coord.longitude}",
            "latitude=${coord.latitude}"
        }
    )
    Map getLocationByCoordinate(@DataVariable("coord") Coordinate coordinate);


}
