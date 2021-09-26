//package org.util.http.response;
//
//import java.io.IOException;
//import java.net.HttpURLConnection;
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//import java.util.Map;
//
///**
// * 网络中的http响应类
// *
// * @author hz.lai
// */
//public class HttpResponse {
//
//    private int status;
//    private String url;
//    private Map<String, List<String>> headers;
//    private byte[] body;
//
//    public HttpResponse(HttpURLConnection urlConnection, byte[] body) {
//        try {
//            this.status = urlConnection.getResponseCode();
//            this.url = urlConnection.getURL().toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        this.headers = urlConnection.getHeaderFields();
//        this.body = body;
//    }
//
//
//    public String getBodyString() {
//        return new String(body,StandardCharsets.UTF_8);
//    }
//
//
//    public int getStatus() {
//        return status;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public Map<String, List<String>> getHeaders() {
//        return headers;
//    }
//
//    public byte[] getBody() {
//        return body;
//    }
//
//    public String getBodyAsString() {
//        if (body != null) {
//            return new String(body, StandardCharsets.UTF_8);
//        }
//        return null;
//    }
//
//}
