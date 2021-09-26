//package org.util.http;
//
//import org.util.http.client.BasicHttpClient;
//import org.util.http.method.ParameterMap;
//import org.util.http.response.HttpResponse;
//
//import java.util.Map;
//
///**
// * Date: 2021/8/18
// * Description:
// *
// * @author hz.lai
// */
//public class HttpUtil {
//
//    public static String doPost(String url, String path, Map<String, Object> params, String ctype,
//                                String accessKey,
//                                String accessSecret) {
//        BasicHttpClient httpClient = new BasicHttpClient(url);
//        ParameterMap paramMap = httpClient.newParams();
//        paramMap.putAll(params);
//        return _doPost(path, paramMap, ctype, accessKey, accessSecret, httpClient);
//    }
//
//    public static String _doPost(String path, ParameterMap paramMap, String ctype,
//                                 String accessKey,
//                                 String accessSecret,
//                                 BasicHttpClient httpClient) {
//        HttpResponse response = httpClient.post(path, paramMap, ctype, accessKey, accessSecret);
//        if (response == null) {
//            return null;
//        }
//        return response.getBodyString();
//    }
//
//
//}
