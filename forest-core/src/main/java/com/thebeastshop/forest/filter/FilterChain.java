package com.thebeastshop.forest.filter;

import com.thebeastshop.forest.http.ForestRequest;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2017-07-12 16:30
 */
public class FilterChain implements Filter {

    private LinkedList<Filter> filters = new LinkedList<>();

    @Override
    public Object doFilter(ForestRequest request, Object data) {
        Iterator<Filter> iter = filters.iterator();
        Object result = data;
        for ( ; iter.hasNext(); ) {
            Filter filter = iter.next();
            result = filter.doFilter(request, result);
        }
        return result;
    }
}
