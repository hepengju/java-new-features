package com.hepengju.java07.new09_forkjoin;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * ForkJoin框架
 * 
 * <pre>
 * 
 *  说明: 在必要的情况下,将一个大任务拆分(fork)为若干个小任务(拆到不可再拆时),再将一个个的小任务运算的结果进行(join)汇总
 *       其采用"工作窃取"模式(work-stealing): 当执行新的任务时它可以将其拆分为更小的任务执行,并将它放在自己的队列中
 *       
 *  优势: 相对于一般的线程池实现,fork/join框架的优势体现在对其中包含的任务的处理方式上. 在一般的线程池中,如果一个线程正在执行的任务
 *       由于某些原因无法继续运行,那么该线程会处理等待状态. 而在fork/join框架实现中,如果某个子问题由于等待另外一个子问题的完成而
 *       无法继续运行,那么处理该问题的线程会主动寻找其他尚未运行的子问题来执行,这种方式减少了线程的等待时间,提高了性能
 *     
 *  备注: Java8中的StreamAPI的并行方法, 底层用的就是ForkJoin框架
 *  
 * </pre>
 * 
 * @see ForkJoinCalculate
 * @author hepengju
 *
 */
public class _ForkJoin {

    // 计算运行耗时
    private Instant begin;
    private Instant end;
    
    @Before public void before() {
        begin = Instant.now();
    }
    
    @After public void after() {
        end = Instant.now();
        System.out.printf("耗时(毫秒): %s", Duration.between(begin, end).toMillis());
    }
    
                                                   // for     fork/join   parallelStream
    //private final Long MAX_NUM = 1_0000_0000L;   // 65        108         81
    //private final Long MAX_NUM = 10_0000_0000L;  // 546       459         295
    //private final Long MAX_NUM = 100_0000_0000L; // 5154      3183        2192
    private final Long MAX_NUM = 1000_0000_0000L;  // 51430     31436       21341
    
    

    /**
     * 普通for循环
     */
    @Test public void testNormalFor() {  // 此处发现一个东东, 这个方法的名字起名字为testFor, 某些时候执行会报错!
        long sum = 0;
        for (long i = 0; i <= MAX_NUM; i++) {
            sum += i;
        }
        System.out.println(sum);
    }
    
    /**
     * fork/join示例
     */
    @Test public void testForkJoin() {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0, MAX_NUM);
        Long sum = pool.invoke(task);
        System.out.println(sum);
    }
    
    /**
     * java8的StreamAPI
     */
    @Test public void testStreamAPI() {
        long sum = LongStream.range(0, MAX_NUM).parallel().sum();
        System.out.println(sum);
    }

}
