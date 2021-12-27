package com.hepengju.java08.new04_streamAPI;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.Comparator.*;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 强大的StreamAPI: 只需要告诉程序需要的是什么, 而不用管如何做
 * 
 * <pre>
 *  说明: Stream是Java8中处理集合的关键抽象概念,可以执行非常复杂的查找/过滤/映射等操作,类似SQL,高效且易用
 * 
 *  Stream: 是数据渠道,用于操作数据(集合,数组等)所生成的元素序列. "集合讲的是数据, 流讲的是计算"
 *      * Stream自己不会存储元素
 *      * Stream不会改变源对象
 *      * Stream操作是延迟执行的
 * 
 *  Stream操作的三个步骤
 *      * 创建流: 
 *          - 集合创建: Collection.stream
 *          - 数组创建: Arrays.stream
 *          - 多值创建: Stream.of
 *          - 函数创建: Stream.iterate, Stream.generate
 *
 *          - 实用流:  IntStream, LongStream, DoubleStream
 *              * 为什么需要原始类型流呢? 可以增加一些特有的方法, 比如sum, 同时可以降低内存使用
 *              * 转换为对象流: boxed, mapToObj
 *
 *          补充:
 *          - 文件的行: java.nio.file.Files#lines(java.nio.file.Path)
 *          - 正则分隔: Pattern#splitAsStream(java.lang.CharSequence)
 *
 *      * 中间操作:
 *          - 筛选: filter, distinct
 *          - 切片: limit, skip
 *          - 映射: map, mapToInt/Long/Double, flapMap
 *          - 排序: sorted, sorted(comparator)
 *          - 查找: findFirst, findAny
 *          - 匹配: all/any/noneMatch
 *          - 调试: peek
 *
 *      * 终止操作: 
 *          - 普通: count, max, min, forEach
 *          - 规约: reduce(T,BinaryOperator), reduce(BinaryOperator) 
 *              * 备注: map和reduce的连接通常称为map-reduce模式,因Google用它进行网络搜索而出名
 *          - 搜集: collect(Collector c)
 *              * toList, toSet, toCollection
 *              * counting, summingInt, averagingInt, summarizingInt
 *              * joining, maxBy, minBy, reducing
 *              * groupingBy, partitioningBy
 *              * collectingAndThen: 转换函数返回的类型, 包裹另一个收集器,对其结果进行转换
 * 
 *  并行流/串行流: 
 *      * parallel(): 并行流就是把内容分为多个数据块,并用不同的线程分别处理每个数据块的流
 *      * sequential()
 *  
 *  SQL与Stream:
 *      SQL                       Stream
 *      -------------------------------------------
 *      column                    map
 *      max,min,distinct          max,min,distinct
 *      where                     filter
 *      exists/in                 anyMatch, allMatch
 *      group by                  groupingBy, partitionBy
 *      orderBy                   sorted
 *      limit/rownum              skip,limit
 *      limit 1                   findFirst,findAny
 *      wm_concat/listagg         joining
 *      
 * </pre>
 * 
 * @author hepengju
 *
 */
public class _StreamAPI {

    /**
     * 创建流
     */
    @SuppressWarnings({ "unused", "resource" })
    @Test public void testCreateStream() throws IOException {
        // 由集合(Collection)创建流
        List<String> list = List.of("a", "b", "c"); //此处的of()是java09的新特性
        Set<String>  set  = Set.of("a", "b", "c");  //此处的of()是java09的新特性
        Stream<String> s01 = list.stream();
        Stream<String> s02 = set.stream();
        
        // 由数组创建流
        IntStream s03 = Arrays.stream(new int[] {1,2,3});
        
        // 由值创建流
        Stream<Integer> s04 = Stream.of(1,2,3);
        
        // 由函数创建流: 迭代,生成
        Stream<Integer> s05 = Stream.iterate(1, x -> x*x);
        Stream<Integer> s06 = Stream.generate(() -> new Random().nextInt(100));
        
        // 文件
        Stream<Path> pathStream = Files.walk(Paths.get("."));
        Stream<String> linesStream = Files.lines(Paths.get("."));
        
        // 正则
        Pattern pattern = Pattern.compile(",");
        Stream<String> strStream = pattern.splitAsStream("孙悟空,猪八戒,唐三藏,沙悟净");
    }
    
    //测试数据准备
    List<Person> plist = Arrays.asList(new Person("孙悟空","S",18,10000.00)
                                      ,new Person("猪八戒","Z",28,20000.00)
                                      ,new Person("唐三藏","T",38,30000.00)
                                      ,new Person("沙悟净","S",48,40000.00)
                                      ,new Person("小妖","X",10,10)
                                      ,new Person("小妖","X",10,20) // 重复数据
                                      );
    /**
     * 中间操作及终止
     */
    @Test public void testMiddleHandle() {
        Optional<String> first = plist.stream()
             .filter(p -> p.getAge() > 18)      // 筛选与切片
             .distinct()
             .skip(2)
             .limit(2)
             .map(Person::getName)              // 映射
             .sorted()                          // 排序
             .sorted(reverseOrder())
             .findFirst();                      // 终止
             ;
             
       String name = first.orElse("白骨精");
       System.out.println(name);  // 沙悟净
    }
    
    /**
     * 测试flatMap
     */
    @Test public void testFlatMap() {
        List<String> strs = Arrays.asList("孙悟空", "猪八戒");
        String collect = strs.stream()
                             .map(s -> {
                                        char[] arr01 = s.toCharArray();
                                        Character[] arr02 = new Character[arr01.length];
                                        for (int i = 0; i < arr02.length; i++) {
                                            arr02[i] = arr01[i];
                                        }
                                  return arr02;})
                             .flatMap(Arrays::stream)   // 扁平化
                             .map(String::valueOf)
                             .collect(joining(","));
        System.out.println(collect); // 孙,悟,空,猪,八,戒

    }
    
    /**
     * IntStream, LongStream, DoubleStream
     */
    @Test public void testIntStream() {
        IntStream.range(1, 10).forEach(System.out::println);
        LongStream.iterate(2, x -> x * 2).limit(10).forEach(System.out::println);
        DoubleStream.of(1.1, 2.2, 3.3).forEach(System.out::println);
    }
    
    /**
     * SQL与Stream
     * 
     */
    @Test public void testStreamCompSql() {
        // select name, max(age) from person where name in ('孙悟空','猪八戒') and age is not null group by name order by name
        LinkedHashMap<String, Optional<Integer>> map = 
            plist.stream()
                 .filter(p -> Arrays.asList("孙悟空","猪八戒").contains(p.getName())) // name in ('孙悟空','猪八戒')
                 .filter(p -> nonNull(p.getAge()))                                  // age is not null
                 .sorted(comparing(Person::getName,nullsLast(naturalOrder())))      // order by name, 注意空值问题
               //.collect(groupingBy(Person::getName)                               // Map<String, List<Person>>, 此处搜集到的还是人,但需要的是年龄,继续downstream搜集
                 .collect(groupingBy(Person::getName,LinkedHashMap::new,mapping(Person::getAge, maxBy(Integer::compare))))   // group by name
                 ;
        System.out.println(map);         // {孙悟空=Optional[18], 猪八戒=Optional[28]}
             
        // select * from person where age = (select max(age) from person) limit 1    
        Optional<Person> first = plist.stream().sorted((p1,p2) -> p2.getAge() - p1.getAge()).findFirst();    
        System.out.println(first.get()); // Person [name=沙悟净, age=48, salary=40000.0]
    }
    
    /**
     * Stream处理集合的简化示例
     * 
     * <br> 通讯录: 数据结构 Map<String,List<Person>>
     */
    @Test public void testStreamCompCollection() {
        // 普通集合方式
        Map<String,List<Person>> map01 = new LinkedHashMap<>();
        for (Person p: plist) {
            List<Person> existList = map01.get(p.getPyFirst());
            if(existList == null) {
                List<Person> list = new ArrayList<>();
                list.add(p);
                map01.put(p.getPyFirst(), list);
            }else {
                existList.add(p);
            }
            
        }

        // Stream方式
        Map<String, List<Person>> map02 = plist.stream().collect(groupingBy(Person::getPyFirst));                             // 分组
        Map<String, List<Person>> map03 = plist.stream().collect(groupingBy(Person::getPyFirst,LinkedHashMap::new,toList())); // 分组并指定数据结构
        
        System.out.println(map01); //{S=[Person [name=孙悟空, age=18, salary=10000.0], Person [name=沙悟净, age=48, salary=40000.0]], Z=[Person [name=猪八戒, age=28, salary=20000.0]], T=[Person [name=唐三藏, age=38, salary=30000.0]], X=[Person [name=小妖, age=10, salary=10.0], Person [name=小妖, age=10, salary=20.0]]}
        System.out.println(map02); //{S=[Person [name=孙悟空, age=18, salary=10000.0], Person [name=沙悟净, age=48, salary=40000.0]], T=[Person [name=唐三藏, age=38, salary=30000.0]], X=[Person [name=小妖, age=10, salary=10.0], Person [name=小妖, age=10, salary=20.0]], Z=[Person [name=猪八戒, age=28, salary=20000.0]]}
        System.out.println(map03); //{S=[Person [name=孙悟空, age=18, salary=10000.0], Person [name=沙悟净, age=48, salary=40000.0]], Z=[Person [name=猪八戒, age=28, salary=20000.0]], T=[Person [name=唐三藏, age=38, salary=30000.0]], X=[Person [name=小妖, age=10, salary=10.0], Person [name=小妖, age=10, salary=20.0]]}
    }

    /**
     * 并行流的使用
     */
    @Test public void testParallel() {
        Instant start = Instant.now();
        long max = 1000_0000_0000L; //1000亿
        
        // 普通for循环: 53s
        //long sum = 0L;
        //for (long i = 1; i <= max; i++) {
        //    sum += i;
        //}
        
        // 串行流: 40s
        //long sum = LongStream.range(1, max + 1).sum();
        
        // 并行流: 25s
        long sum = LongStream.range(1, max + 1).parallel().sum();
        
        Instant end = Instant.now();
        System.out.println(sum);
        System.out.println(Duration.between(start, end).getSeconds());
    }
}
