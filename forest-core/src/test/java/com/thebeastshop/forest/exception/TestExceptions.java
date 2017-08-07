package com.thebeastshop.forest.exception;

import com.thebeastshop.forest.exceptions.ForestHandlerException;
import com.thebeastshop.forest.exceptions.ForestNetworkException;
import com.thebeastshop.forest.exceptions.ForestRuntimeException;
import com.thebeastshop.forest.http.ForestRequest;
import com.thebeastshop.forest.http.ForestResponse;
import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.mock;

/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2017-05-18 12:33
 */
public class TestExceptions {


    @Test
    public void testForestNetworkException() {
        try {
            throw new ForestNetworkException("test network exception", 500, null);
        } catch (ForestNetworkException e) {
            assertEquals("HTTP 500 Error: test network exception", e.getMessage());
            assertEquals(Integer.valueOf(500), e.getStatusCode());
        }
    }

    @Test
    public void testForestRuntimeException() {
        try {
            throw new Exception("first Exception");
        } catch (Exception e) {
            try {
                throw new ForestRuntimeException(e);
            } catch (ForestRuntimeException fe) {
                assertEquals(e, fe.getCause());
            }
        }

        try {
            throw new Exception("first Exception");
        } catch (Exception e) {
            try {
                throw new ForestRuntimeException("second Exception", e);
            } catch (ForestRuntimeException fe) {
                assertEquals("second Exception", fe.getMessage());
                assertEquals(e, fe.getCause());
            }
        }

        try {
            throw new ForestRuntimeException("runtime exception");
        } catch (ForestRuntimeException fe) {
            assertEquals("runtime exception", fe.getMessage());
        }
    }

    @Test
    public void testForestHandlerException() {
        ForestRequest request = mock(ForestRequest.class);
        ForestResponse response = mock(ForestResponse.class);
        try {
            throw new ForestHandlerException("test", request, response);
        } catch (ForestHandlerException e) {
            assertEquals("test", e.getMessage());
            Assert.assertEquals(request, e.getRequest());
            Assert.assertEquals(response, e.getResponse());
            e.setRequest(null);
            assertNull(e.getRequest());
            e.setResponse(null);
            assertNull(e.getResponse());
        }

        try {
            throw new Exception("first Exception");
        } catch (Exception e) {
            try {
                throw new ForestHandlerException("second Exception", e, request, response);
            } catch (ForestHandlerException fe) {
                assertEquals("second Exception", fe.getMessage());
                assertEquals(e, fe.getCause());
                Assert.assertEquals(request, fe.getRequest());
                Assert.assertEquals(response, fe.getResponse());
            }
        }

        try {
            throw new Exception("first Exception");
        } catch (Exception e) {
            try {
                throw new ForestHandlerException( e, request, response);
            } catch (ForestHandlerException fe) {
                assertEquals(e, fe.getCause());
                Assert.assertEquals(request, fe.getRequest());
                Assert.assertEquals(response, fe.getResponse());
            }
        }


    }

}
