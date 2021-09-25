
package org.util.http.client;

import org.util.http.exception.HttpRequestException;
import org.util.http.handler.BasicRequestHandler;
import org.util.http.handler.RequestHandler;
import org.util.http.log.ConsoleRequestLogger;
import org.util.http.log.RequestLogger;
import org.util.http.method.*;
import org.util.http.response.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Map;
import java.util.TreeMap;


public abstract class AbstractHttpClient {

    public static final String URLENCODED = "application/x-www-form-urlencoded;charset=UTF-8";
    public static final String MULTIPART = "multipart/form-data";
    public static final String JSON = "application/json";

    protected String baseUrl = "";

    protected RequestLogger requestLogger = new ConsoleRequestLogger();

    protected final RequestHandler requestHandler;

    private Map<String, String> requestHeaders = new TreeMap<String, String>();
    /**
     * 默认2s
     */
    protected int connectionTimeout = 5000;
    /**
     * 默认8s
     */
    protected int readTimeout = 8000;
    /**
     * 表明http连接状态， 被用于超时的逻辑
     */
    private boolean isConnected;


    private AbstractHttpClient() {
        this("");
    }


    private AbstractHttpClient(String baseUrl) {
        this(baseUrl, new BasicRequestHandler() {
        });
    }


    public AbstractHttpClient(String baseUrl, RequestHandler requestHandler) {
        this.baseUrl = baseUrl;
        this.requestHandler = requestHandler;
    }

//    public HttpResponse get(String path, ParameterMap params) {
//        return execute(new HttpGet(path, params));
//    }

    /**
     * 执行POST请求，并阻塞等待接收返回对象
     *
     * @param path   请求路径
     * @param params map参数
     * @return Response object
     */
    public HttpResponse post(String path, ParameterMap params) {
        return execute(new HttpPost(path, params));
    }

    public HttpResponse post(String path, ParameterMap params, String contentType,String accessKey,
                             String accessSecret) {
        return execute(new HttpPost(path, params, contentType,accessKey,accessSecret));
    }

    /**
     * 执行POST请求 并且携带参数
     *
     *
     * @param path
     * @param contentType
     * @param data
     * @return Response object
     */
    public HttpResponse post(String path, String contentType, byte[] data) {
        return execute(new HttpPost(path, null, contentType, data));
    }


    /**
     * This method wraps the call to doHttpMethod and invokes the custom error
     * handler in case of exception. It may be overridden by other clients such
     * purposes of retries, etc.
     *
     * @param httpRequest
     * @return Response object (may be null if request did not complete)
     */
    public HttpResponse execute(HttpRequest httpRequest) {
        HttpResponse httpResponse = null;
        try {
            httpResponse = doHttpMethod(httpRequest);
        } catch (HttpRequestException hre) {
            requestHandler.onError(hre);
        } catch (Exception e) {
            // In case a RuntimeException has leaked out, wrap it in HRE
            requestHandler.onError(new HttpRequestException(e, httpResponse));
        }
        return httpResponse;
    }

    /**
     * This is the method that drives each request. It implements the request
     * lifecycle defined as open, prepare, write, read. Each of these methods in
     * turn delegates to the {@link RequestHandler} associated with this client.
     *
     * @return Response object
     * @throws HttpRequestException
     */
    @SuppressWarnings("finally")
    protected HttpResponse doHttpMethod(HttpRequest httpRequest) throws HttpRequestException {

        HttpURLConnection uc = null;
        HttpResponse httpResponse = null;

        try {
            isConnected = false;
            uc = openConnection(httpRequest.getPath());
            prepareConnection(uc, httpRequest.getHttpMethod(), httpRequest.getContentType());
            appendRequestHeaders(uc);
            prepareSign(uc,httpRequest);
            if (requestLogger.isLoggingEnabled()) {
                requestLogger.logRequest(uc, httpRequest.getContent());
            }
            //该没有启动网关服务或者网关地址错误，那么会超时直接到下面的catch
            uc.connect();
            isConnected = true;
            if (uc.getDoOutput() && httpRequest.getContent() != null) {
                writeOutputStream(uc, httpRequest.getContent());
            }
            if (uc.getDoInput()) {
                httpResponse = readInputStream(uc);
            } else {
                httpResponse = new HttpResponse(uc, null);
            }
        } catch (Exception e) {
            // 1.读取错误流，来获取状态码例如404,500
            // 2.io异常 这里一般是超时，访问不到网关
            try {
                httpResponse = readErrorStream(uc);
            } catch (Exception ee) {
                //io异常 这里一般是超时，访问不到网关
                e.printStackTrace();
            } finally {
                // 如果网关启动正常返回响应的http响应
                if (httpResponse != null && httpResponse.getStatus() > 0) {
                    return httpResponse;
                }
                throw new HttpRequestException(e, httpResponse);
            }
        } finally {
            if (requestLogger.isLoggingEnabled()) {
                requestLogger.logResponse(httpResponse);
            }
            if (uc != null) {
                uc.disconnect();
            }
        }
        return httpResponse;
    }

    /**
     * Validates a URL and opens a connection. This does not actually connect
     * to a server, but rather opens it on the client only to allow writing
     * to begin. Delegates the open operation to the {@link RequestHandler}.
     *
     * @param path Appended to this client's baseUrl
     * @return An open connection (or null)
     * @throws IOException
     */
    protected HttpURLConnection openConnection(String path) throws IOException {
        String requestUrl = baseUrl + path;
        try {
            new URL(requestUrl);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(requestUrl + " is not a valid URL", e);
        }
        return requestHandler.openConnection(requestUrl);
    }

    /**
     * http链接的基本设置
     * @param urlConnection
     * @param httpMethod
     * @param contentType
     * @throws IOException
     */
    protected void prepareConnection(HttpURLConnection urlConnection, HttpMethod httpMethod,
            String contentType) throws IOException {
        urlConnection.setConnectTimeout(connectionTimeout);
        urlConnection.setReadTimeout(readTimeout);
        requestHandler.prepareConnection(urlConnection, httpMethod, contentType);
    }

    /**
     * 签名回调方法
     * @param uc
     * @param request
     */
    protected void prepareSign(HttpURLConnection uc, HttpRequest request) {

    }

    /**
     * Append all headers added with {@link #addHeader(String, String)} to the
     * request.
     *
     * @param urlConnection
     */
    private void appendRequestHeaders(HttpURLConnection urlConnection) {
        for (String name : requestHeaders.keySet()) {
            String value = requestHeaders.get(name);
            urlConnection.setRequestProperty(name, value);
        }


    }

    /**
     * Writes the request to the server. Delegates I/O to the {@link RequestHandler}.
     *
     * @param urlConnection
     * @param content to be written
     * @return HTTP status code
     * @throws Exception in order to force calling code to deal with possible
     *             NPEs also
     */
    protected int writeOutputStream(HttpURLConnection urlConnection, byte[] content) throws Exception {
        OutputStream out = null;
        try {
            out = requestHandler.openOutput(urlConnection);
            if (out != null) {
                requestHandler.writeStream(out, content);
            }
            return urlConnection.getResponseCode();
        } finally {
            // catch not necessary since method throws Exception
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    // Swallow to show first cause only
                }
            }
        }
    }

    /**
     * Reads the input stream. Delegates I/O to the {@link RequestHandler}.
     *
     * @param urlConnection
     * @return HttpResponse, may be null
     * @throws Exception
     */
    protected HttpResponse readInputStream(HttpURLConnection urlConnection) throws Exception {
        InputStream in = null;
        byte[] responseBody = null;
        try {
            in = requestHandler.openInput(urlConnection);
            if (in != null) {
                responseBody = requestHandler.readStream(in);
            }
            return new HttpResponse(urlConnection, responseBody);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    // Swallow to avoid dups
                }
            }
        }
    }

    /**
     * Reads the error stream to get an HTTP status code like 404.
     * Delegates I/O to the {@link RequestHandler}.
     *
     * @param urlConnection
     * @return HttpResponse, may be null
     * @throws Exception
     */
    protected HttpResponse readErrorStream(HttpURLConnection urlConnection) throws Exception {
        InputStream err = null;
        byte[] responseBody = null;
        try {
            err = urlConnection.getErrorStream();
            if (err != null) {
                responseBody = requestHandler.readStream(err);
            }
            return new HttpResponse(urlConnection, responseBody);
        } finally {
            if (err != null) {
                try {
                    err.close();
                } catch (Exception e) {
                    // Swallow to avoid dups
                }
            }
        }
    }

    /**
     * Convenience method creates a new ParameterMap to hold query params
     *
     * @return Parameter map
     */
    public ParameterMap newParams() {
        return new ParameterMap();
    }

    /**
     * Adds to the headers that will be sent with each request from this client
     * instance. The request headers added with this method are applied by
     * calling {@link HttpURLConnection#setRequestProperty(String, String)}
     * after {@link #prepareConnection(HttpURLConnection, HttpMethod, String)},
     * so they may supplement or replace headers which have already been set.
     * Calls to {@link #addHeader(String, String)} may be chained. To clear all
     * headers added with this method, call {@link #clearHeaders()}.
     *
     * @param name
     * @param value
     * @return this client for method chaining
     */
    public AbstractHttpClient addHeader(String name, String value) {
        requestHeaders.put(name, value);
        return this;
    }

    /**
     * Clears all request headers that have been added using
     * {@link #addHeader(String, String)}. This method has no effect on headers
     * which result from request properties set by this class or its associated
     * {@link RequestHandler} when preparing the {@link HttpURLConnection}.
     */
    public void clearHeaders() {
        requestHeaders.clear();
    }

    /**
     * Returns the {@link CookieManager} associated with this client.
     *
     * @return CookieManager
     */
    public static CookieManager getCookieManager() {
        return (CookieManager) CookieHandler.getDefault();
    }

    /**
     * Sets the logger to be used for each request.
     *
     * @param logger
     */
    public void setRequestLogger(RequestLogger logger) {
        this.requestLogger = logger;
    }

    /**
     * Initialize the app-wide {@link CookieManager}. This is all that's
     * necessary to enable all Web requests within the app to automatically send
     * and receive cookies.
     */
    public static void ensureCookieManager() {
        if (CookieHandler.getDefault() == null) {
            CookieHandler.setDefault(new CookieManager());
        }
    }

    /**
     * Determines whether an exception was due to a timeout. If the elapsed time
     * is longer than the current timeout, the exception is assumed to be the
     * result of the timeout.
     *
     * @param t Any Throwable
     * @return true if caused by connection or read timeout
     */
    protected boolean isTimeoutException(Throwable t, long startTime) {
        long elapsedTime = System.currentTimeMillis() - startTime + 10; // fudge
        if (requestLogger.isLoggingEnabled()) {
            requestLogger.log("ELAPSED TIME = " + elapsedTime + ", CT = " + connectionTimeout
                    + ", RT = " + readTimeout);
        }
        if (isConnected) {
            return elapsedTime >= readTimeout;
        } else {
            return elapsedTime >= connectionTimeout;
        }
    }

    /**
     * Sets the connection timeout in ms. This is the amount of time that
     * {@link HttpURLConnection} will wait to successfully connect to the remote
     * server. The read timeout begins once connection has been established.
     *
     * @param connectionTimeout
     */
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    /**
     * Sets the read timeout in ms, which begins after connection has been made.
     * For large amounts of data expected, bump this up to make sure you allow
     * adequate time to receive it.
     *
     * @param readTimeout
     */
    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

}
