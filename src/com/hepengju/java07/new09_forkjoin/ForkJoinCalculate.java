package com.hepengju.java07.new09_forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * fork/join计算类
 * 
 * <br> 继承 RecursiveTask(有返回值) 或 RecursiveAction(无返回值)
 * 
 * @author hepengju
 *
 */
@SuppressWarnings("serial")
public class ForkJoinCalculate extends RecursiveTask<Long>{

    private long start;   // 起始值
    private long end;     // 结束值
    
    private static final long THRESHOLD = 10000; // 临界值
    
    
    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        
        // 小于临界值直接计算
        if (length <= THRESHOLD) {
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            // 大于临界值, 拆分子任务计算然后合并
            long middle = (start + end) / 2;
            ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
            left.fork();
            
            ForkJoinCalculate right = new ForkJoinCalculate(middle + 1, end);
            right.fork();
            
            return left.join() + right.join();
        }
    }
}
