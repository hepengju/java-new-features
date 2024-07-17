package com.hepengju.java21.new04_visual_threads;


import org.junit.Test;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.IntStream;

/**
 * 虚拟线程：协程，轻量级线程
 *
 * <pre>
 *     1. 永远不要池化虚拟线程: 线程池和所有资源池一样，旨在共享昂贵的资源，但虚拟线程并不昂贵，而且永远不需要将它们池化
 *     2. 虚拟线程永远是守护线程, Thread.setDaemon(boolean) 不能修改其为非守护线程
 *     3. 虚拟线程有固定的优先级 Thread.NORM_PRIORITY, Thread.setPriority(int)对虚拟线程没有影响
 *     4. 观察虚拟线程: jcmd <pid> Thread.dump_to_file -format=json <file>
 *
 *     x. SpringBoot3.2开启支持虚拟线程: spring.threads.virtual.enabled=true 可开启虚拟线程
 *          1）Tomcat/Jetty会使用虚拟线程处理请求
 *          2）Blocking Execution with Spring WebFlux 会使用虚拟线程
 *          3）Task Execution  会使用虚拟线程
 *          4）Task Scheduling 会使用虚拟线程
 *          5）spring.main.keep-alive=true，会开启一个平台线程，保证jvm不退出（因为虚拟线程都是守护线程）
 *          6）其他技术集成： RabbitMQ Listener, Kafka Listener, Spring Data Redis的ClusterCommandExecutor等也会使用虚拟线程
 * </pre>
 *
 * @see <a href="https://developer.huawei.com/consumer/cn/forum/topic/0202852436905570201?fid=23">Java下一代高并发技术——虚拟线程</a>
 * @see <a href="https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.2-Release-Notes">SpringBoot3.2发行说明</a>
 */
public class _VirtualThread {

    /**
     * 使用Executors创建虚拟线程
     */
    @Test
    public void testExecutors() {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 1_0000).forEach(i -> {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(1));
                    return i;
                });
            });
        }
        // executor.close() is called implicitly, and waits
    }

    /**
     * Thread的新增api用于创建虚拟线程
     */
    @Test
    public void testThread() throws InterruptedException {
        // Thread.Builder
        Thread.ofPlatform().name("platform thread").start(() -> System.out.println("我是平台线程"));
        Thread.ofVirtual().name("virtual thread").start(() -> System.out.println("我是虚拟线程"));

        // 虚拟线程工厂
        ThreadFactory factory = Thread.ofVirtual().factory();
        factory.newThread(() -> System.out.println("虚拟线程工厂创建的虚拟线程"));

        // Thread静态方法启动虚拟线程
        Thread.startVirtualThread(() -> System.out.println("Thread静态方法启动虚拟线程"));

        // 新增Duration作为参数
        Thread.sleep(Duration.ofSeconds(1));

        // join也新增duration作为参数，新增final方法threadId
        Thread thread = Thread.currentThread();
        thread.join(Duration.ofSeconds(1));
        thread.threadId();
        thread.isVirtual();
        System.out.println("threadId: " + thread.threadId() + ", isVirtual:  " + thread.isVirtual());
    }


}
