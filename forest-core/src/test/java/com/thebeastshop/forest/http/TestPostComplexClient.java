package com.thebeastshop.forest.http;

import com.thebeastshop.forest.config.ForestConfiguration;
import com.thebeastshop.forest.http.client.PostClient;
import com.thebeastshop.forest.mock.PostComplexMockServer;
import com.thebeastshop.forest.mock.PostMockServer;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2017-05-11 16:55
 */
public class TestPostComplexClient {

    private final static Logger log = LoggerFactory.getLogger(TestPostComplexClient.class);

    @Rule
    public PostComplexMockServer server = new PostComplexMockServer(this);

    private static ForestConfiguration configuration;

    private static PostClient postClient;


    @BeforeClass
    public static void prepareClient() {
        configuration = ForestConfiguration.configuration();
        postClient = configuration.createInstance(PostClient.class);
    }


    @Before
    public void prepareMockServer() {
        server.initServer();
    }

    @Test
    public void testComplexPost() {
        String result = postClient.complexPost("1", "username=foo&password=123456");
        log.info("response: " + result);
        assertNotNull(result);
        Assert.assertEquals(PostMockServer.EXPECTED, result);
    }



}
