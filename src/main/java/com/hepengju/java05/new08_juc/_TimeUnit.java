package com.hepengju.java05.new08_juc;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * 时间单位
 * 
 * @author hepengju
 *
 */
public class _TimeUnit {

    /**
     * 时间单位转换
     */
    @Test public void testToXxx() {
        // 1天: 24小时,86400秒
        long hours = TimeUnit.DAYS.toHours(1);  
        long seconds = TimeUnit.DAYS.toSeconds(1); 
        System.out.println(hours);    // 24
        System.out.println(seconds);  // 86400
        
        // 4000: 分钟,小时(截断取整)
        long minutes = TimeUnit.SECONDS.toMinutes(4000);
        hours = TimeUnit.SECONDS.toHours(4000);
        System.out.println(minutes); // 66
        System.out.println(hours);   // 1
    }
    
    /**
     * 线程睡眠, 等待, 插入
     */
    @Test public void testThread() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3); // 睡眠3秒, 比"Thread.sleep(3000);"看起来更易读
        System.out.println("Over..");
        
        //TimeUnit.SECONDS.timedWait(this, 3);
        //TimeUnit.SECONDS.timedJoin(Thread.currentThread(), 3);
    }
}
