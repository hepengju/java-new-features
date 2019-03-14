package com.hepengju.java09.new08_httpclient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.Test;

//import jdk.incubator.http.HttpClient;
//import jdk.incubator.http.HttpRequest;
//import jdk.incubator.http.HttpResponse;

//说明：JDK9中HttpClient是要引用模块的，由于改成Maven项目，统一用JDK11，这里只做演示。
/**
 * 全新的Http客户端API
 * 
 * <pre>
 * 
 *  说明：
 *      * 提供了一个新的HTTP客户端（HttpClient），它将替代仅适用于blocking模式的HttpURLConnection。
 *      * 提供对WebSocket和HTTP/2的支持，可以从jdk.incubator.httpclient模块中获取。
 *      * 在Java 11中又再次升级，处于为正式可用状态。
 *  
 *  Java11的变化: 
 *      * 从java9的jdk.incubator.httpclient模块迁移到java.net.http模块，包名由jdk.incubator.http改为java.net.http
 *      * 原来的诸如HttpResponse.BodyHandler.asString()方法变更为HttpResponse.BodyHandlers.ofString()，
 *        变化一为BodyHandler改为BodyHandlers，变化二为asXXX()之类的方法改为ofXXX()，由as改为of
 * </pre>
 * 
 * @see <a href="https://www.jb51.net/article/148199.htm">Java11新特性之HttpClient小试牛刀</a>
 *  
 * @author WGR
 *
 */
public class _HttpClient {
    
    /**
     * 使用 HttpClient替换原有的HttpURLConnection
     */
    @Test public void testHttpClient() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest req = HttpRequest.newBuilder(URI.create("http://www.baidu.com")).GET().build();
            HttpResponse<String> response = null;
            //response = client.send(req, HttpResponse.BodyHandler.asString());
            response = client.send(req, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());      //200
            System.out.println(response.version().name());  //HTTP_1_1
            System.out.println(response.body());            //输出页面的内容
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
