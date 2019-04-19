package com.hepengju.java08.new12_completableFuture;

/**
 * 事件监听, 异步回调机制
 * 
 * <pre>
 *  并发与并行
 *      - 并发(concurrency ): 不同的代码块交替执行
 *      - 并行(parallellism): 不同的代码块同时执行
 *  
 *  
 *  引入: Future --> CompletableFuture
 *  分析: 
 *      - Future背景: 一个任务花费较长时间, 为了不阻塞在这里, 可以先返回一个对象Future, 继续做其他事情, 在需要用到的时候再get得到结果
 *      - CompltetableFuture背景: get得到结果需要做的事件, 提前注册好, 其任务完成后自动调用
 *      
 *  API:
 *      - supplyAsync
 *      - thenApply
 *      - whenComplete
 *      - handle
 *      - thenRun
 *      - thenAccept
 *      - thenCompose
 *      - thenCombine
 *      - thenAccpetBoth   
 *       
 * </pre>
 * 
 * @author hepengju
 *
 */
public class _CompletableFuture {

}
