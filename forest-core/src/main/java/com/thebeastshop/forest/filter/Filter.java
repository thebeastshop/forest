package com.thebeastshop.forest.filter;

import com.thebeastshop.forest.http.ForestRequest;

/**
 * @author gongjun[dt_flys@hotmail.com]
 * @since 2016-05-24
 */
public interface Filter {

    Object doFilter(ForestRequest request, Object data);
}
