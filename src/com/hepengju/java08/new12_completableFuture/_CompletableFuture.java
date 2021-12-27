package com.hepengju.java08.new12_completableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static java.util.concurrent.CompletableFuture.delayedExecutor;
import static java.util.concurrent.CompletableFuture.runAsync;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * CompletableFuture
 * 
 * <pre>
 *  引入: Future + CompletionStage --> CompletableFuture
 *  分析: 
 *      - Future接口
 *          * 优点: 异步提交任务后,当前线程可以执行其他操作了,需要其结果时get()得到即可
 *          * 缺点: 
 *              - 没有提供通知机制,即无法得知Future什么完成了,也无法注册Future完成事件
 *              - 无法直接表述多个Future结果之间的依赖性
 *  API:
 *      - 建立异步操作
 *          * runAsync            无返回值, 不指定executor则使用ForkJoinPoll.commonPool()作为它的线程池执行异步代码
 *          * supplyAsync         有返回值
 *     
 *      - 计算结果完成的回调
 *          * whenComplete        结果完成或抛出异常时执行特定的操作
 *          * exceptionally       当运行时出现异常,此处可以进行补偿
 *          * handle              同上, 不同的是handle在任务完成后再执行,还可以处理异常的任务; thenApply只可以执行正常任务, 任务出现异常则不执行
 *      
 *      - 变换    
 *          * thenApply           进行变换
 *          * thenCombine         结合两个结果进行转化
 *          * applyToEither       谁计算的快就用它的结果转化
 *          
 *      - 消费    
 *          * thenAccept          进行消费
 *          * thenAcceptBoth      结合两个结果进行消费     
 *          * acceptEither        谁计算的快就用它的结果消费
 *          
 *      - 执行    
 *          * thenRun             不关心结果,执行下一个操作
 *          * runAfterBoth        两个都运行完后执行
 *          * runAfterEither      任何一个完成都会执行下一步操作
 *          
 *      - 其他
 *          * isDone()            是否完成
 *          * get()               获得结果(方法抛出异常必须外部try-catch)
 *          * join()              获得结果(不抛出未检查异常)
 *          * getNow(T dv)        获得结果, 完成则取结果, 未完成则取默认值
 *          
 *          * completedFuture        返回一个新的已经带着指定值完成的CompletableFuture
 *          * completeExceptionally  如果还没有完成, 引发调用get()和相关方法去抛出指定异常
 *          
 *      - 静态方法
 *          * allOf  所有任务全部完成后构造的CompletableFuture完成
 *          * anyOf  只要有一个任务完成构造的CompletableFuture完成
 *          
 *          
 *  并发与并行
 *      - 并发(concurrency ): 不同的代码块交替执行
 *      - 并行(parallellism): 不同的代码块同时执行    
 *      
 * </pre>
 * 
 * @see <a href="https://www.cnblogs.com/cjsblog/p/9267163.html">CompletableFuture基本用法</a>
 * @see <a href="https://mahmoudanouti.wordpress.com/2018/01/26/20-examples-of-using-javas-completablefuture/">20 Examples of Using Java’s CompletableFuture</a>
 * @see <a href-"http://www.importnew.com/28319.html">20 个使用 Java CompletableFuture的例子</a>
 * 
 * @author hepengju
 *
 */
public class _CompletableFuture {

    /**
     * 1. 创建一个已经完成的CompletableFuture
     * 
     * <p> getNow任务完成则返回结果值,否则返回参数值
     */
    @Test public void test01() {
        CompletableFuture<String> cf = completedFuture("message");
        assertTrue(cf.isDone());
        assertEquals("message", cf.getNow(null));
    }
    
    /**
     * 2. 运行一个简单的异步阶段
     * 
     * <p> 1) 以Async结果的方法会异步执行
     * <p> 2) 没有指定exector时默认采用ForkJoinPool实现(使用守护线程执行任务)
     */
    @Test public void test02() {
        CompletableFuture<Void> cf = runAsync(() -> {      
            assertTrue(Thread.currentThread().isDaemon());
            randomSleep();
        });
        
        assertFalse(cf.isDone());
        sleepEnouth();
        assertTrue(cf.isDone());
    }

    /**
     * 3. 在前阶段后应用一个函数
     * 
     * <p> 1) then  表示前阶段正常完成(没有异常)后执行的阶段
     * <p> 2) apply 表示前阶段的返回值会被应用到后面的阶段
     */
    
    @Test(expected =  CompletionException.class)
    public void test03() {
        CompletableFuture<String> cf = completedFuture("message").thenApply(s -> {
            assertFalse(Thread.currentThread().isDaemon()); //此处为主线程
            return s.toUpperCase();
        });
        assertEquals("MESSAGE", cf.getNow(null));
        
        //测试前阶段没有正常返回时的情况
        supplyAsync(() -> 10 / 0).thenApply(i -> i * 10).join();
        //java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
    }
    
    /**
     * 4. 在前阶段异步地应用一个函数
     */
    @Test public void test04() {
        CompletableFuture<String> cf = completedFuture("message").thenApplyAsync(s -> {
           assertTrue(Thread.currentThread().isDaemon());
           randomSleep();
           return s.toUpperCase();
        });
        assertNull(cf.getNow(null));
        assertEquals("MESSAGE", cf.join());
    }
    
    /**
     * 5. 在前阶段异步地应用一个函数, 并指定定制的执行器(线程池)
     */
    @Test public void test05() {
        ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
            int count = 1;
            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "custom-executor-" + count++);
            } 
        });
        
        CompletableFuture<String> cf = completedFuture("message").thenApplyAsync(s -> {
            assertTrue(Thread.currentThread().getName().startsWith("custom-executor-"));
            assertFalse(Thread.currentThread().isDaemon());
            randomSleep();
            return s.toUpperCase();
        }, executor); 
        
        assertNull(cf.getNow(null));
        assertEquals("MESSAGE", cf.join());
    }
    
    /**
     * 6. 消费前阶段结果
     */
    @Test public void test06() {
        StringBuilder result = new StringBuilder();
        completedFuture("thenAccept message").thenAccept(s -> result.append(s));
        assertTrue("Result was empty", result.length() > 0);
    }
    
    /**
     * 7. 异步地消费前阶段结果
     */
    @Test public void test07() {
        StringBuilder result = new StringBuilder();
        completedFuture("thenAccept message")
            .thenAcceptAsync(s -> result.append(s))
            .join();
        
        assertTrue("Result was empty", result.length() > 0);
    }
    
    /**
     * 8. 完成计算异常
     */
    @Test public void test08() {
        CompletableFuture<String> cf = completedFuture("message")
            .thenApplyAsync(String::toUpperCase, delayedExecutor(1, TimeUnit.SECONDS));
        CompletableFuture<String> exceptionHandler = cf.handle((e, th) -> th != null ? "message upon cancel" : "");
        cf.completeExceptionally(new RuntimeException("completed exceptionlly"));
        
        assertTrue("Was not completed expceptionally", cf.isCompletedExceptionally());
        
        try {
            cf.join();
            fail("Should have thrown an exception");
        } catch (CompletionException e) { // just for testing
            assertEquals("completed exceptionlly", e.getCause().getMessage());
        } 
        
        assertEquals("message upon cancel", exceptionHandler.join());

    }
    
    /**
     * 9. 取消计算
     * 
     * <p> 和completeExceptionally类似, 我们可以调用cancel(boolean mayInterruptIfRunning)取消计算
     * 对于CompletableFuture类, 布尔参数并没有被使用, 这是因为它并没有使用中断去取消操作, 相反, cancel
     * 等价于completeException(new CancellationException)
     */
    @Test public void test09() {
        CompletableFuture<String> cf = completedFuture("message")
                .thenApplyAsync(String::toUpperCase, delayedExecutor(1, TimeUnit.SECONDS));
        CompletableFuture<String> cf2 = cf.exceptionally(th -> "canceled message");
        assertTrue("was not canceled", cf.cancel(true));
        assertTrue("was not completed exceptionly", cf.isCompletedExceptionally());
        assertEquals("canceled message", cf2.join());
        
    }
    
    /**
     * 10. 对两个完成阶段的任意一个应用一个函数
     */
    @Test public void test10() {
        String original = "Message";
        CompletableFuture<String> cf1 = completedFuture(original).thenApplyAsync(s -> delayedUpperCase(s));
        CompletableFuture<String> cf2 = completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s));
        CompletableFuture<String> cf = cf1.applyToEither(cf2, s -> s + " from applyToEither");
        assertTrue(cf.join().endsWith(" from applyToEither"));
        System.out.println(cf.join()); 
        //message from applyToEither 
        // 或者
        //MESSAGE from applyToEither
    }
    
    /**
     * 11. 对两个完成阶段的任意一个进行消费
     */
    @Test public void test11() {
        String original = "Message";
        StringBuffer result = new StringBuffer(); // 此处使用现场安全的StringBuffer
        CompletableFuture<String> cf1 = completedFuture(original).thenApplyAsync(s -> delayedUpperCase(s));
        CompletableFuture<String> cf2 = completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s));
        CompletableFuture<Void> cf = cf1.acceptEither(cf2, s -> result.append(s).append(" acceptEither"));
        
        cf.join();
        assertTrue("Result was empty", result.toString().endsWith(" acceptEither"));
        System.out.println(result);
    }
    
    /**
     * 12. 在两个阶段都完成后运行Runnable
     */
    @Test public void test12() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        completedFuture(original).thenApply(String::toUpperCase)
            .runAfterBoth(completedFuture(original).thenApply(String::toLowerCase)
                    , () -> result.append("done"));
        assertTrue("Result was empty", result.length() > 0);
    }
    
    /**
     * 13. 在两个阶段都完成后接受BiConsumer
     */
    @Test public void test13() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        completedFuture(original).thenApply(String::toUpperCase)
            .thenAcceptBoth(completedFuture(original).thenApply(String::toLowerCase)
                    , (s1,s2) -> result.append(s1 + s2));
        assertEquals("MESSAGEmessage", result.toString());
    }
    
    /**
     * 14. 在两个阶段都完成后应用BiFunction
     */
    @Test public void test14() {
        String original = "Message";
        CompletableFuture<String> cf = completedFuture(original).thenApply(String::toUpperCase)
            .thenCombine(completedFuture(original).thenApply(String::toLowerCase)
                    , (s1,s2) -> s1 + s2);
        assertEquals("MESSAGEmessage", cf.getNow(null));
    }
    
    /**
     * 15. 异步地在两个阶段结果上应用BiFunction
     */
    @Test public void test15() {
        String original = "Message";
        CompletableFuture<String> cf = completedFuture(original).thenApplyAsync(s -> delayedUpperCase(s))
                .thenCombine(completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s))
                           , (s1,s2) -> s1 + s2
                            );
        assertEquals("MESSAGEmessage", cf.join());
    }
    
    /**
     * 16. 组合CompletableFutures
     */
    @Test public void test16() {
        String original = "Message";
        CompletableFuture<String> cf = completedFuture(original)
                .thenApplyAsync(s -> delayedUpperCase(s))
                .thenCompose(upper -> completedFuture(original)
                                        .thenApplyAsync(s -> delayedLowerCase(s))
                                        .thenApply(s -> upper + s)
                            );
        
        assertEquals("MESSAGEmessage", cf.join());
    }
    
    /**
     * 17. 创建一个阶段(当几个阶段任何一个完成时)
     */
    @Test public void test17() {
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture<String>> futures = messages.stream()
                .map(msg -> completedFuture(msg).thenApply(s -> delayedUpperCase(s)))
                .collect(toList());
        
        CompletableFuture.anyOf(futures.toArray(new CompletableFuture[futures.size()]))
                         .whenComplete((res, th) -> {
                            if(th == null) {
                                assertTrue(isUpperCase((String) res));
                                result.append(res);
                            }
                         });
        assertTrue("Result was empty", result.length() > 0);
    }

    /**
     * 18. 创建一个阶段(当几个阶段全部完成时)
     */
    @Test public void test18() {
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture<String>> futures = messages.stream()
                .map(msg -> completedFuture(msg).thenApply(s -> delayedUpperCase(s)))
                .collect(toList());
        
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                         .whenComplete((v, th) -> {
                            futures.forEach(cf -> assertTrue(isUpperCase(cf.getNow(null))));
                            result.append("done");
                         });
        assertTrue("Result was empty", result.length() > 0);
    }
    
    /**
     * 19. 创建一个异步完成的阶段(当几个阶段全部完成时)
     */
    @Test public void test19() {
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture<String>> futures = messages.stream()
                .map(msg -> completedFuture(msg).thenApplyAsync(s -> delayedUpperCase(s)))
                .collect(toList());
        
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                         .whenComplete((v, th) -> {
                            futures.forEach(cf -> assertTrue(isUpperCase(cf.getNow(null))));
                            result.append("done");
                         }).join();
        assertTrue("Result was empty", result.length() > 0);
    }
    
    /**
     * 20. 真实生活中的列子
     * 
     * <pre>
     *  1. 首先异步调用cars方法获得Car的列表，它返回CompletionStage场景。cars消费一个远程的REST API。
     *  2. 然后我们复合一个CompletionStage填写每个汽车的评分，通过rating(manufacturerId)返回一个CompletionStage, 它会异步地获取汽车的评分(可能又是一个REST API调用)
     *  3. 当所有的汽车填好评分后，我们结束这个列表，所以我们调用allOf得到最终的阶段， 它在前面阶段所有阶段完成后才完成。
     *  4. 在最终的阶段调用whenComplete(),我们打印出每个汽车和它的评分。
     * </pre>
     * 
     * <p>因为每个汽车的实例都是独立的，得到每个汽车的评分都可以异步地执行，这会提高系统的性能(延迟)，而且等待所有的汽车评分被处理使用的是allOf方法，
     * 而不是手工的线程等待(Thread#join() 或 a CountDownLatch)。
     */
    @Test public void test20() {
        Instant begin = Instant.now();
        
        List<Car> cars = cars();
        cars.forEach(car -> {
            float rating = rating(car.manufacturerId);
            car.setRating(rating);
        });

        cars.forEach(System.out::println);
        
        Instant end = Instant.now();
        System.out.println("Cost: " + Duration.between(begin, end).toMillis() + "ms");
    }
    
    @Test public void test20_1() {
        Instant begin = Instant.now();
        
        List<Car> cars = cars();
        cars.parallelStream()
              .forEach(car -> {
                  float rating = rating(car.manufacturerId);
                  car.setRating(rating);
              });
        
        cars.forEach(System.out::println);
        
        Instant end = Instant.now();
        System.out.println("Cost: " + Duration.between(begin, end).toMillis() + "ms");
    }
    
    @Test public void test20_2() {
        Instant begin = Instant.now();
        
        cars2().thenCompose(cars -> {
            List<CompletionStage<Car>> updatedCars =
                    cars.stream()
                        .map(car -> rating2(car.manufacturerId).thenApply(r -> {car.setRating(r); return car;}))
                        .collect(toList());

            CompletableFuture<Void> done = CompletableFuture.allOf(updatedCars.toArray(new CompletableFuture[updatedCars.size()]));
            return done.thenApply(v -> updatedCars.stream()
                                                  .map(CompletionStage::toCompletableFuture)
                                                  .map(CompletableFuture::join)
                                                  .collect(toList()));
        }).whenComplete((cars, th) -> {
            if (th == null) {
                cars.forEach(System.out::println);
            } else {
                throw new RuntimeException(th);
            }
        }).toCompletableFuture().join();
            
        Instant end = Instant.now();
        System.out.println("Cost: " + Duration.between(begin, end).toMillis() + "ms");
    }
    

    
    private List<Car> cars() {
        List<Car> carList = new ArrayList<>();
        carList.add(new Car(1, 3, "Fiesta", 2017));
        carList.add(new Car(2, 7, "Camry", 2014));
        carList.add(new Car(3, 2, "M2", 2008));
        return carList;
    }
    
    private CompletionStage<List<Car>> cars2() {
        return CompletableFuture.supplyAsync(() -> cars());
    }
    
    private float rating(int manufacturer) {
        try {
            simulateDelay();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        switch (manufacturer) {
        case 2:
            return 4f;
        case 3:
            return 4.1f;
        case 7:
            return 4.2f;
        default:
            return 5f;
        }
        
    }
    
    private CompletionStage<Float> rating2(int manufacturer) {
        return CompletableFuture.supplyAsync(() -> rating(manufacturer)).exceptionally(th -> -1f);
    }

    private void simulateDelay() throws InterruptedException {
        Thread.sleep(5000);
    }
    
    private boolean isUpperCase(String res) {
        return res.equals(res.toUpperCase());
    }
    
    private String delayedLowerCase(String s) {
        randomSleep();
        return s.toLowerCase();
    }

    private String delayedUpperCase(String s) {
        randomSleep();
        return s.toUpperCase();
    }

    /**
     * 充足睡: 2秒
     */
    private void sleepEnouth() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * 随机睡: 1秒内
     */
    private void randomSleep() {
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
}
