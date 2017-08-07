package com.thebeastshop.forest.interceptor;

import com.thebeastshop.forest.config.ForestConfiguration;
import com.thebeastshop.forest.exceptions.ForestRuntimeException;
import com.thebeastshop.forest.http.TestGetClient;
import com.thebeastshop.forest.mock.GetMockServer;
import com.thebeastshop.forest.Forest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static junit.framework.Assert.*;

/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2017-05-18 16:57
 */
public class TestInterceptor {

    private final static Logger log = LoggerFactory.getLogger(TestGetClient.class);

    @Rule
    public GetMockServer server = new GetMockServer(this);

    private static ForestConfiguration configuration;

    private static InterceptorClient interceptorClient;

    @BeforeClass
    public static void prepareClient() {
        configuration = ForestConfiguration.configuration();
        interceptorClient = configuration.createInstance(InterceptorClient.class);
    }


    @Before
    public void prepareMockServer() {
        server.initServer();
    }

    @Test
    public void testSimpleInterceptor() {
        String result = interceptorClient.simple();
        assertNotNull(result);
        assertEquals("XX: " + GetMockServer.EXPECTED, result);
    }

    @Test
    public void testMultipleInterceptor() {
        String result = interceptorClient.multiple();
        assertNotNull(result);
        assertEquals("YY: XX: " + GetMockServer.EXPECTED, result);
    }

    @Test
    public void testWrongInterceptorClass() {
        boolean error = false;
        try {
            configuration.createInstance(WrongInterceptorClient.class);
        } catch (ForestRuntimeException e) {
            error = true;
            log.error(e.getMessage());
            assertEquals("Class [" + Forest.class.getName() + "] is not a implement of [" +
                    Interceptor.class.getName() + "] interface.", e.getMessage());
        }
        assertTrue(error);
    }

    @Test
    public void testFalseInterceptor() {
        String result = interceptorClient.beforeFalse();
        assertNull(result);
    }


}
