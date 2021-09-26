//package org.util.http.method;
//
///**
// * Holds data for an HTTP request to be made with the attached HTTP client.
// *
// * @author hz.lai
// */
//public abstract class HttpRequest {
//
//    public static final String URLENCODED = "application/x-www-form-urlencoded;charset=utf-8";
//    public static final String APPLICATION_JSON = "application/json";
//    public static final String MULTIPART = "multipart/form-data";
//
//    protected String path = ""; // avoid null in URL
//    protected HttpMethod httpMethod;
//    protected String contentType;
//    protected byte[] content;
//    protected String contentString;
//    protected String accessSecret;
//    protected String accessKey;
//
//
//    /**
//     * Constructs a request with optional params appended
//     * to the query string.
//     *
//     * @param path
//     * @param params
//     */
//    public HttpRequest(String path,String accessKey,String accessSecret) {
//        this.accessKey = accessKey;
//        this.accessSecret = accessSecret;
//        this.path = path;
//    }
//    public HttpRequest(String path, ParameterMap params) {
//        //拼接url
////        if (URLENCODED.equals(contentType)) {
////            String queryString = null;
////            if (path != null) {
////                this.path = path;
////            }
////            if (params != null) {
////                queryString = params.urlEncode();
////                this.path += "?" + queryString;
////            }
////        }
//        this.path = path;
//    }
//
//    public String getPath() {
//        return path;
//    }
//
//    public HttpMethod getHttpMethod() {
//        return httpMethod;
//    }
//
//    public String getContentType() {
//        return contentType;
//    }
//
//    public byte[] getContent() {
//        return content;
//    }
//
//    public String getAccessSecret() {
//        return accessSecret;
//    }
//
//    public void setAccessSecret(String accessSecret) {
//        this.accessSecret = accessSecret;
//    }
//
//    public String getAccessKey() {
//        return accessKey;
//    }
//
//    public void setAccessKey(String accessKey) {
//        this.accessKey = accessKey;
//    }
//}
