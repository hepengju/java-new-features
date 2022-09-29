package com.hepengju.java19.new01_virtualthread;

import org.junit.Test;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * 虚拟线程：协程，轻量级线程
 *
 * <pre>
 *     1. 不池化虚拟线程: 线程池和所有资源池一样，旨在共享昂贵的资源，但虚拟线程并不昂贵，而且永远不需要将它们池化。
 *     2. 观察虚拟线程: jcmd <pid> Thread.dump_to_file -format=json <file>
 *     3. 调度虚拟线程: JDK的调度程序将虚拟线程分配给平台线程，而不是直接将虚拟线程分配给处理器（虚拟线程的M:N调度）
 *          JDK的虚拟线程调度程序是一个窃取工作的工具ForkJoinPool在FIFO模式下工作。
 *          调度程序的并行性是可用于调度虚拟线程的平台线程数。默认情况下，它等于可用处理器，
 *          但它可以使用系统属性jdk.virtualThreadScheduler.parallelism进行调整。
 *          请注意，此ForkJoinPool不同于公共池，例如，它用于并行流的实现，并在后进先出模式下工作。
 *     4. 执行虚拟线程
 *          JDK中的绝大多数阻塞操作将卸载虚拟线程，释放其载体和底层操作系统线程来承担新的工作。
 *          但是，JDK中的一些阻塞操作不会卸载虚拟线程，因此会阻塞其载体和底层操作系统线程。
 *          这是因为操作系统级别（例如，许多文件系统操作）或JDK级别(例如，Object.wait())。
 *          这些阻塞操作的实现将通过临时扩展调度程序的并行性来补偿操作系统线程的捕获。
 *          因此，调度程序的ForkJoinPool中的平台线程数量可能暂时超过可用处理器的数量。
 *     5. 内存使用和与垃圾回收的交互
 *          与平台线程栈不同，虚拟线程栈不是GC根，因此其中包含的引用不会被执行并发扫描的垃圾收集器在停止世界暂停中遍历。
 * </pre>
 *
 * @see <a href="https://developer.huawei.com/consumer/cn/forum/topic/0202852436905570201?fid=23">Java下一代高并发技术——虚拟线程</a>
 */
public class _VirtualThread {

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

    @Test
    public void testThread() throws InterruptedException {
        // Thread.Builder
        Thread.ofPlatform().name("platform thread").start(() -> System.out.println("我是平台线程"));
        Thread.ofVirtual().name("virtual thread").start(() -> System.out.println("我是虚拟线程"));

        // 虚拟线程工厂
        Thread.ofVirtual().factory();

        // Thread静态方法启动虚拟线程
        Thread.startVirtualThread(() -> System.out.println("我是虚拟线程2"));

        // 新增Duration作为参数
        Thread.sleep(Duration.ofSeconds(10));

        // join也新增duration作为参数，新增final方法threadId
        Thread thread = Thread.currentThread();
        thread.join(Duration.ofSeconds(10));
        thread.threadId();
        thread.isVirtual();

    }
}
