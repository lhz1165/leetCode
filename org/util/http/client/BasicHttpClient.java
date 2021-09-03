
package org.util.http.client;


import org.util.http.handler.BasicRequestHandler;
import org.util.http.handler.RequestHandler;
import org.util.http.method.HttpRequest;

import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

/**
 * Minimal HTTP client that facilitates simple GET, POST, PUT, and DELETE
 * requests. To implement buffering, streaming, or set other request properties,
 * set an alternate {@link RequestHandler}.
 *
 * <p>Sample usage:</p>
 * <pre>
 *    BasicHttpClient httpClient = new BasicHttpClient("http://www.google.com");
 *    ParameterMap params = httpClient.newParams().add("q", "GOOG");
 *    HttpResponse httpResponse = httpClient.get("/finance", params);
 *    System.out.println(httpResponse.getBodyAsString());
 * </pre>
 *
 * @author hz.lai
 */
public class BasicHttpClient extends AbstractHttpClient {

    /**
     * Constructs the default client with empty baseUrl.
     */
    public BasicHttpClient() {
        this("");
    }

    /**
     * Constructs the default client with baseUrl.
     *
     * @param baseUrl
     */
    public BasicHttpClient(String baseUrl) {
        this(baseUrl, new BasicRequestHandler() {
        });
    }

    /**
     * Constructs a client with baseUrl and custom {@link RequestHandler}.
     *
     * @param baseUrl
     * @param requestHandler
     */
    public BasicHttpClient(String baseUrl, RequestHandler requestHandler) {
        super(baseUrl, requestHandler);
    }

    @Override
    protected void prepareSign(HttpURLConnection uc, HttpRequest request) {
        String body = new String(request.getContent(), StandardCharsets.UTF_8);
        Long timestamp = System.currentTimeMillis();
        String data = uc.getURL().getPath() + body;
//        String sign = SignHelper.getSign(data, timestamp, request.getAccessSecret());
//        uc.setRequestProperty(SignConstant.ACCESS_KEY, request.getAccessKey());
//        uc.setRequestProperty(SignConstant.SIGN, sign);
//        uc.setRequestProperty(SignConstant.TIME, timestamp.toString());
//        uc.setRequestProperty("data", body);
    }
}
