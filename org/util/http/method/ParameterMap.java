//package org.util.http.method;
//
//import org.util.http.handler.RequestHandler;
//import org.util.http.json.JSONObject;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
///**
// * Parameter map knows offers convenience methods for chaining add()s
// * as well as URL encoding.
// *
// * @author hz.lai
// */
//public class ParameterMap implements Map<String, Object> {
//
//    private Map<String, Object> map = new HashMap<String, Object>();
//
//    @Override
//    public void clear() {
//        map.clear();
//    }
//
//    @Override
//    public boolean containsKey(Object key) {
//        return map.containsKey(key);
//    }
//
//    @Override
//    public boolean containsValue(Object value) {
//        return map.containsValue(value);
//    }
//
//    @Override
//    public Set<Entry<String, Object>> entrySet() {
//        return map.entrySet();
//    }
//
//    @Override
//    public Object get(Object key) {
//        return map.get(key);
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return map.isEmpty();
//    }
//
//    @Override
//    public Set<String> keySet() {
//        return map.keySet();
//    }
//
//    @Override
//    public Object put(String key, Object value) {
//        return map.put(key, value);
//    }
//
//    @Override
//    public void putAll(Map<? extends String, ? extends Object> arg) {
//        map.putAll(arg);
//    }
//
//    @Override
//    public Object remove(Object key) {
//        return map.remove(key);
//    }
//
//    @Override
//    public int size() {
//        return map.size();
//    }
//
//    @Override
//    public Collection<Object> values() {
//        return map.values();
//    }
//
//    /**
//     * Convenience method returns this class so multiple calls can be chained.
//     *
//     * @param name
//     * @param value
//     * @return This map
//     */
//    public ParameterMap add(String name, String value) {
//        map.put(name, value);
//        return this;
//    }
//
//    /**
//     * Returns URL encoded data
//     *
//     * @return URL encoded String
//     */
//    public String urlEncode() {
//        StringBuilder sb = new StringBuilder();
//        for (String key : map.keySet()) {
//            if (sb.length() > 0) {
//                sb.append("&");
//            }
//            sb.append(key);
//            Object value = map.get(key);
//            if (value != null) {
//                sb.append("=");
//                try {
//                    sb.append(URLEncoder.encode(value.toString(), RequestHandler.UTF8));
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        System.out.println("param body :"+sb);
//        return sb.toString();
//    }
//
//
//    public String paramJson() {
//        return JSONObject.toJSONString(map);
//    }
//
//    /**
//     * Return a URL encoded byte array in UTF-8 charset.
//     *
//     * @return URL encoded bytes
//     */
//    public byte[] urlEncodedBytes() {
//        return this.urlEncode().getBytes(StandardCharsets.UTF_8);
//    }
//
//    /**
//     * 转化为json字节数组
//     * @return
//     */
//    public byte[] paramJsonBytes() {
//        return this.paramJson().getBytes(StandardCharsets.UTF_8);
//    }
//
//}
