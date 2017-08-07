package com.thebeastshop.forest.test;

import com.thebeastshop.forest.callback.OnError;
import com.thebeastshop.forest.client.ErrorClient;
import com.thebeastshop.forest.config.ForestConfiguration;
import com.thebeastshop.forest.exceptions.ForestRuntimeException;
import com.thebeastshop.forest.http.ForestRequest;
import com.thebeastshop.forest.http.ForestResponse;
import junit.framework.TestCase;

/**
 * @author gongjun
 * @date 2016-06-01
 */
public class ErrorTest extends TestCase {
    ForestConfiguration configuration = ForestConfiguration.configuration();
    ErrorClient errorClient = configuration.createInstance(ErrorClient.class);

    public void testError() {
        boolean t = false;
        try {
            String result = errorClient.testError();
        } catch (ForestRuntimeException e) {
            t = true;
        }
        assertTrue(t);
    }

    public void testErrorCallback() {
        final boolean[] ts = new boolean[] {false};
        errorClient.testError(new OnError() {
            public void onError(ForestRuntimeException ex, ForestRequest request, ForestResponse response) {
                ts[0] = true;
                assertNotNull(ex);
                assertNotNull(request);
            }
        });
        assertTrue(ts[0]);
    }
}
