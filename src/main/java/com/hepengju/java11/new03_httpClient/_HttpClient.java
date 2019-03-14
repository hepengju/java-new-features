package com.hepengju.java11.new03_httpClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

import org.junit.Test;

/**
 * 
 * JEP 321: HTTP Client (Standard)(重磅)
 * 新增HttpClient
 * 
 * <pre>
 *  说明：
 *        * Java 9开始引入的一个处理 HTTP请求的的孵化 HTTP Client API，该 API支持同步和异步。
 *        * Java 11 中已经为正式可用状态。
 *        
 *  @See <a href="http://openjdk.java.net/jeps/321">
 * </pre>
 * 
 * @author WGR
 * 
 */
public class _HttpClient {
    
    /**
     * 异步方法（sendAsync）
     * 优点：不会造成堵塞
     * @throws Exception
     */
    @Test
    public void testName2() throws Exception {
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create("http://127.0.0.1:8080/test/")).build();
        BodyHandler<String> responseBodyHandler = BodyHandlers.ofString();
        
        CompletableFuture<HttpResponse<String>> sendAsync = client.sendAsync(request, responseBodyHandler);
        sendAsync.thenApply(t -> t.body()).thenAccept(System.out::println);
        //HttpResponse<String> response = sendAsync.get();
        //String body = response.body();
        //System.out.println(body);
        
    }
    
    /**
     * 同步方法（send）
     * @throws Exception
     */
    @Test
    public void testSend() throws Exception {
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create("http://127.0.0.1:8080/test/")).build();
        
        BodyHandler<String> responseBodyHandler = BodyHandlers.ofString();
        HttpResponse<String> response = client.send(request, responseBodyHandler);
        String body = response.body();
        
        System.out.println(body);//会输出整个html的内容
    }

}
