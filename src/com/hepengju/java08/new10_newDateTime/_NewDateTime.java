package com.hepengju.java08.new10_newDateTime;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.firstDayOfNextYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;
import static java.time.temporal.TemporalAdjusters.next;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import org.junit.Test;

/**
 * 
 * 新的日期时间API
 * 
 * <pre>
 *  1. 日期时间: LocalDate, LocalTime, LocalDateTime: 不可变对象, 不包含时区相关信息
 *      * now()    静态方法, 当前时间对象
 *      * of()     静态方法, 指定日期/时间
 *      * plusDays , plusWeeks , plusMonths , plusYears       加
 *      * minusDays, minusWeeks, minusMonths, minusYears      减
 *      * withDayOfMonth, withDayOfWeek, withMonth, withYear  设置
 *      * getDayOfMonth,  getDayOfYear , getDayOfWeek, getMonth, getMonthValue, getYear
 *      * until                获得两个日期之间的Period对象, 或指定 ChronoUnits 的数字
 *      * isBefore, isAfter    是否之前,是否之后
 *      * isLeapYear           是否是瑞年
 *      
 *  2. 时间戳: Instant, 以Unix元年(UTC时区1970年1月1日午夜时分)
 *      * now()
 *      
 *  3. 时间间隔: Duration, Period
 *      * Duration: 用于计算两个"时间"间隔
 *      * Period: 用于计算两个"日期"间隔
 *      
 *  4. 时间校正器: TemporalAdjuster, TemporalAdjusters 
 *      * TemporalAdjusters 提供大量常用的TemporalAdjuster的实现
 *          - 月初,月末,年初,年末,下周几...
 *          
 *  5. 解析和格式化: DateTimeFormatter 
 *      * 解析
 *          - DateTimeFormatter.ofPattern("格式").parse(text)
 *          - LocalDate.parse(text,DateTimeFormatter.ofPattern("格式"))
 *      
 *      * 格式化
 *          - DateTimeFormatter.ofPattern("格式").format(temporal)
 *          - LocalDate.format(DateTimeFormatter.ofPattern("格式"))
 * 
 *  6. 时区的处理: ZonedDateTime, ZoneId
 *  
 *  7. 与传统日期的转换
 *      * 说明: 定义在传统日期的类里面, 方法名: from/to/valueOf
 *      * 示例: 
 *          - Date.from(instant)                     date.toInstant()
 *          - GregorianCalendar.from(zonedDateTime)  cal.toZonedDateTime()
 *          - java.sql.Date.valueOf(localDate)       java.sql.date.toLocalDate()
 *          - formatter.toFormat()
 * </pre>
 * 
 * @author hepengju
 *
 */
public class _NewDateTime {

    /**
     * 传统日期及解析器: 可变的, 线程不安全的
     * 
     * <br> SimpleDateFormatter 不是线程安全的
     */
    @Test public void testSimpleDateFormat() throws InterruptedException, ExecutionException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        
        // 定义线程池, 任务, 提交100个解析任务并输出任务的执行情况
        ExecutorService pool = Executors.newFixedThreadPool(10);
        Callable<Date> task = () -> sdf.parse("20190305");
        List<Future<Date>> results = new ArrayList<>();
        IntStream.range(0, 100).forEach( i -> results.add(pool.submit(task)));
        for (Future<Date> future : results) {
            println(future.get());
        }
        
        // 发生异常
        // java.util.concurrent.ExecutionException: java.lang.NumberFormatException: For input string: ".33E13"
    }
    
    /**
     * 线程安全的 DateTimeFormatter
     */
    @Test public void testDateFormatter() throws InterruptedException, ExecutionException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        
        // 定义线程池, 任务, 提交100个解析任务并输出任务的执行情况
        ExecutorService pool = Executors.newFixedThreadPool(10);
        Callable<LocalDate> task = () -> LocalDate.parse("20190305",dtf);
        List<Future<LocalDate>> results = new ArrayList<>();
        IntStream.range(0, 100).forEach( i -> results.add(pool.submit(task)));
        for (Future<LocalDate> future : results) {
            println(future.get());
        }
    }
    
    /**
     * LocalDate
     */
    @Test public void testLocalDate() {
        println(LocalDate.now());         // 2019-03-05
        println(LocalTime.now());         // 21:07:12.656751
        println(LocalDateTime.now());     // 2019-03-05T21:07:12.656751
        
        LocalDate ld01 = LocalDate.now();
        LocalDate ld02 = LocalDate.of(2019, 4, 1);
        println(ld01.plusDays(1));        // 2019-03-06  加
        println(ld01.minusMonths(1));     // 2019-02-05  减
        println(ld01.withMonth(1));       // 2019-01-05  设置
        
        Period period = ld01.until(ld02); 
        println(period);                  // P27D
        println(period.getDays());        // 27
    }
    
    /**
     * Instant 
     */
    @Test public void testInstant() throws InterruptedException {
        println(Instant.now());           // 2019-03-05T13:12:47.332348Z
        
        // 计算运行时间
        Instant start = Instant.now();
        Thread.sleep(2000);
        Instant end = Instant.now();
        
        Duration duration = Duration.between(start, end);
        println(duration);               // PT2.001262S
        println(duration.getSeconds());  // 2
    }
    
    /**
     * TemporalAdjuster
     */
    @Test public void testTemporalAdjuster() {
        LocalDate ld = LocalDate.now();
        println(ld.with(firstDayOfMonth()));      // 2019-03-01 月初
        println(ld.with(firstDayOfNextYear()));   // 2020-01-01 年初
        println(ld.with(lastDayOfMonth()));       // 2019-03-31 月末
        println(ld.with(lastDayOfYear()));        // 2019-12-31 年末
        println(ld.with(next(SUNDAY)));           // 2019-03-10 下周日
        println(ld.with(previousOrSame(MONDAY))); // 2019-03-04 前一个或相同的周一
    }
    
    /**
     * 与传统日期的转换
     */
    @SuppressWarnings("unused")
    @Test public void testFromToDate() {
        // Date -- Instant
        Date date01 = new Date();
        Instant instant = date01.toInstant();
        Date date02 = Date.from(instant);
        
        // java.sql.Date -- LocalDate
        LocalDate ld01 = LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(ld01);
        LocalDate ld02 = sqlDate.toLocalDate();
    }
    
    
    private void println(Object obj) {
        System.out.println(obj);
    }
    
}
