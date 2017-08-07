package com.thebeastshop.forest.executors.httpclient;

import com.thebeastshop.forest.converter.json.ForestJsonConverter;
import com.thebeastshop.forest.exceptions.ForestRuntimeException;
import com.thebeastshop.forest.executors.AbstractHttpExecutor;
import com.thebeastshop.forest.executors.httpclient.body.HttpclientNonBodyBuilder;
import com.thebeastshop.forest.executors.httpclient.request.HttpclientRequestSender;
import com.thebeastshop.forest.executors.httpclient.response.HttpclientForestResponseFactory;
import com.thebeastshop.forest.executors.httpclient.response.HttpclientResponseHandler;
import com.thebeastshop.forest.handler.ResponseHandler;
import com.thebeastshop.forest.http.ForestRequest;
import com.thebeastshop.forest.http.ForestResponseFactory;
import com.thebeastshop.forest.mapping.MappingTemplate;
import com.thebeastshop.forest.utils.RequestNameValue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.client.methods.HttpRequestBase;
import com.thebeastshop.forest.executors.BodyBuilder;
import com.thebeastshop.forest.executors.url.URLBuilder;
import com.thebeastshop.forest.utils.StringUtils;

import java.io.IOException;
import java.util.List;

/**
 * @author gongjun
 * @since 2016-06-14
 */
public abstract class AbstractHttpclientExecutor<T extends  HttpRequestBase> extends AbstractHttpExecutor {
    private static Log log = LogFactory.getLog(AbstractHttpclientExecutor.class);

    protected final HttpclientResponseHandler httpclientResponseHandler;
    protected String url = buildUrl();
    protected final String typeName;
    protected T httpRequest;
    protected BodyBuilder<T> bodyBuilder;


    protected T buildRequest() {
        return getRequestProvider().getRequest(url);
    }

    protected abstract HttpclientRequestProvider<T> getRequestProvider();

    protected abstract URLBuilder getURLBuilder();


    protected String buildUrl() {
        return getURLBuilder().buildUrl(request);
    }


    protected void prepareBodyBuilder() {
        bodyBuilder = new HttpclientNonBodyBuilder();
    }

    protected void prepare() {
        httpRequest = buildRequest();
        prepareBodyBuilder();
        prepareHeaders();
        prepareBody();
    }

    public AbstractHttpclientExecutor(ForestRequest request, HttpclientResponseHandler httpclientResponseHandler, HttpclientRequestSender requestSender) {
        super(request, requestSender);
        this.typeName = request.getType().toUpperCase();
        this.httpclientResponseHandler = httpclientResponseHandler;
        prepare();
    }

    public void prepareHeaders() {
        ForestJsonConverter jsonConverter = request.getConfiguration().getJsonCoverter();
        List<RequestNameValue> headerList = request.getHeaderNameValueList();
        if (headerList != null && !headerList.isEmpty()) {
            for (RequestNameValue nameValue : headerList) {
                httpRequest.setHeader(nameValue.getName(), MappingTemplate.getParameterValue(jsonConverter, nameValue.getValue()));
            }
        }
    }

    public void prepareBody() {
        bodyBuilder.buildBody(httpRequest, request);
    }


    protected static void logContent(String content) {
        log.info("[Forest] " + content);
    }

    public void logRequest(int retryCount, T httpReq) {
        if (!request.isLogEnable()) return;
        String requestLine = getLogContentForRequestLine(retryCount, httpReq);
        String headers = getLogContentForHeaders(httpReq);
        String body = getLogContentForBody(httpReq);
        String content = "Request: \n\t" + requestLine;
        if (StringUtils.isNotEmpty(headers)) {
            content += "\n\tHeaders: \n" + headers;
        }
        if (StringUtils.isNotEmpty(body)) {
            content += "\n\tBody: " + body;
        }
        logContent(content);
    }

    protected String getLogContentForRequestLine(int retryCount, T httpReq) {
        if (retryCount == 0) {
            return httpReq.getRequestLine().toString();
        }
        else {
            return "[Retry: " + retryCount + "] " + httpReq.getRequestLine().toString();
        }
    }

    protected String getLogContentForHeaders(T httpReq) {
        StringBuffer buffer = new StringBuffer();
        Header[] headers = httpReq.getAllHeaders();
        for (int i = 0; i < headers.length; i++) {
            Header header = headers[i];
            buffer.append("\t\t" + header.getName() + ": " + header.getValue());
            if (i < headers.length - 1) {
                buffer.append("\n");
            }
        }
        return buffer.toString();
    }

    protected String getLogContentForBody(T httpReq) {
        return null;
    }


    public void execute(ResponseHandler responseHandler) {
        execute(0, responseHandler);
    }


    public void execute(int retryCount, ResponseHandler responseHandler) {
        try {
            logRequest(retryCount, httpRequest);
            requestSender.sendRequest(request, httpclientResponseHandler, httpRequest);
        } catch (IOException e) {
            if (retryCount >= request.getRetryCount()) {
                httpRequest.abort();
                ForestResponseFactory forestResponseFactory = new HttpclientForestResponseFactory();
                response = forestResponseFactory.createResponse(request, null);
                responseHandler.handleSync(request, response);
//                throw new ForestRuntimeException(e);
                return;
            }
            log.error(e.getMessage());
            execute(retryCount + 1, responseHandler);
        } catch (ForestRuntimeException e) {
            httpRequest.abort();
            throw e;
        }
    }

    public void close() {
/*
        if (httpResponse != null) {
            try {
                EntityUtils.consume(httpResponse.getEntity());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        client.getConnectionManager().closeExpiredConnections();
*/
    }


}
