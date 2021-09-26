//
//package org.util.http.log;
//
//import org.util.http.response.HttpResponse;
//
//import java.io.IOException;
//import java.net.HttpURLConnection;
//
///**
// *
// * @author hz.lai
// */
//public interface RequestLogger {
//
//    /**
//     *
//     * 请求是否需要日志打印
//     *
//     * @return true if enabled
//     */
//    boolean isLoggingEnabled();
//
//    /**
//     * 打印信息
//     *
//     * @param msg
//     */
//    void log(String msg);
//
//    /**
//     * Log the HTTP request and content to be sent with the request.
//     *
//     * @param urlConnection
//     * @param content
//     * @throws IOException
//     */
//    void logRequest(HttpURLConnection urlConnection, Object content) throws IOException;
//
//    /**
//     * Logs the HTTP response.
//     *
//     * @param httpResponse
//     * @throws IOException
//     */
//    void logResponse(HttpResponse httpResponse);
//
//}
