package com.thebeastshop.forest.http;

import com.thebeastshop.forest.config.ForestConfiguration;
import com.thebeastshop.forest.executors.httpclient.response.HttpclientForestResponseFactory;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2017-05-12 17:58
 */
public class TestResponseFactory {

    @Test
    public void  testHttpclientForestResponseFactory() {
        ForestRequest request = new ForestRequest(ForestConfiguration.configuration());
        HttpclientForestResponseFactory responseFactory = new HttpclientForestResponseFactory();
        HttpResponse httpResponse = mock(HttpResponse.class);
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        ForestResponse response = responseFactory.createResponse(request, httpResponse);
        assertNotNull(response);
        assertNull(response.getContent());
    }

}
