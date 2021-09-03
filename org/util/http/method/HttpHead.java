package org.util.http.method;

/**
 * An HTTP HEAD request.
 * 
 * @author hz.lai
 */
public class HttpHead extends HttpRequest {

    /**
     * Constructs an HTTP HEAD request.
     * 
     * @param path Partial URL
     * @param params Name-value pairs to be appended to the URL
     */
    public HttpHead(String path, ParameterMap params) {
        super(path, params);
        this.httpMethod = HttpMethod.HEAD;
    }

}
