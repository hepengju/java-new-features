package com.hepengju.java08.new07_collector;

import static java.util.Comparator.comparing;
import static java.util.Comparator.nullsLast;
import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.filtering;
import static java.util.stream.Collectors.flatMapping;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.groupingByConcurrent;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toConcurrentMap;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Collectors.toUnmodifiableList;
import static java.util.stream.Collectors.toUnmodifiableMap;
import static java.util.stream.Collectors.toUnmodifiableSet;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;


/**
 * 搜集器
 * 
 * <pre>
 *  Collector 接口
 *      - Supplier<A> supplier();                 供给可变容器
 *      - BiConsumer<A, T> accumulator();         将元素添加到容器
 *      - BinaryOperator<A> combiner();           将两部分容器合并为一个容器
 *      - Function<A, R> finisher();              执行最终转换
 *      - Set<Characteristics> characteristics(); 特征集(并发性,无序性,自结束)
 *      
 *  Collectors 工具类
 *      - toCollection(sup)                  转换为集合
 *      - toList()                           转换为List
 *      - toSet()                            转换为Set
 *      
 *      - toMap(kfun,vfun)                   键映射,值映射(**注意键不能重复,值不能为空**)
 *      - toMap(kfun,vfun,bio)               键映射,值映射,值合并
 *      - toMap(kfun,vfun,bio,sup)           键映射,值映射,值合并,Map类型
 *      - toConcurentMap(kfun,vfun)
 *      - toConcurentMap(kfun,vfun,bio)
 *      - toConcurentMap(kfun,vfun,bio,sup)
 *     
 *      - toUnmodifiableList()               不可变的
 *      - toUnmodifiableSet()
 *      - toUnmodifiableMap(fun,fun)
 *      - toUnmodifiableMap(fun,fun,bio)
 *      
 *      - summarizingInt/Long/Double         概况: 计算,求和,最大,最小,平均
 *      - averagingInt/Long/Double           平均值
 *      - summingInt/Long/Double             求和
 *      - counting                           计数
 *      - filtering(pre,cor)                 先过滤再搜集
 *      - mapping(fun,cor)                   先映射再搜集
 *      - flatMapping(fun,cor)               先拍平再搜集
 *      - maxBy(com)                         最大值
 *      - minBy(com)                         最小值
 *        
 *      - groupingBy(fun)                    分组,搜集为List
 *      - groupingBy(fun,cor)                分组,指定搜集方式
 *      - groupingBy(fun,sup,cor)            分组,指定Map类型,指定搜集方式
 *      - groupingByConcurrent(fun)
 *      - groupingByConcurrent(fun,cor)
 *      - groupingByConcurrent(fun,sup,cor)
 *      
 *      - partitioningBy(pred)               分区
 *      - partitioningBy(pred,cor)           分区,指定搜集方式
 *           
 *      - joining()                          连接起来
 *      - joining(s)                         连接起来, 指定连接符
 *      - joining(s,pre,suf)                 连接起来, 指定连接符, 指定前缀和后缀
 *      
 *      - reducing(bo)                       规约
 *      - reducing(t,bo)                     规约,指定起始值
 *      - reducing(u,fun,bo)                 规约,指定起始值,指定规约前转换元素
 *      
 *      - collectingAndThen(cor,fun)         搜集后的额外finisher操作
 *      - teeing(cor,cor,bifun)              指定两个搜集器及其如何合并(jdk12)
 * </pre>
 * 
 * @author hepengju
 *
 */
public class _Collector {

    /**
     * Map的键和值的Null问题
     * <pre>
     *  1. HashMap的键和值都可以为空
     *  2. TreeMap的键不可以为空,值可以为空                  --> 分析: key需要比较
     *  3. HashTable和ConcurrentHashMap的键和值都不能为空   --> 分析: 支持并发, get(key)返回null的话不能确认是没有还是放进去的就是空, 对于hashmap则可以使用contain(key)确定 
     *  </pre>
     */
    @Test public void testHashMap() {
        Map<String,String> map = new HashMap<>();
        map.put("abc", "ABC");
        map.put("abc", null);
        map.put(null, "ABC");
        map.put(null, "CDE");
        print(map);               // {null=CDE, abc=null}, class: class java.util.HashMap
        
        Map<String,String> tmap = new TreeMap<>();
        tmap.put("abc", "ABC");
        tmap.put("abc", null);
        //tmap.put(null, "ABC");
        //tmap.put(null, "CDE");
        print(tmap);               // {abc=null}, class: class java.util.TreeMap
        
        Map<String,String> conmap = new ConcurrentHashMap<>();
        conmap.put("abc", "ABC");
        //conmap.put("abc", null);
        //conmap.put(null, "ABC");
        //conmap.put(null, "CDE");
        print(conmap);             // {abc=ABC}, class: class java.util.concurrent.ConcurrentHashMap
        
        print(null == null);       // true
    }
    
    //测试数据准备
    List<Person> plist = Arrays.asList(new Person("孙悟空",18,10000.00)   // 正常数据
                                      ,new Person("猪八戒",28,20000.00)
                                      ,new Person("唐三藏",38,30000.00)
                                      ,new Person("沙悟净",48,40000.00)
                                      ,new Person("小妖",10,10.0)
                                      //,new Person("小妖",20,20.0)        // 重复数据(人名)
                                      //,new Person(null,48,40000.00)    // 人名为null
                                      //,new Person("大妖",null,40000.00)   // 年龄为null
                                      );
    
    /**
     * 测试 toXXX 方法
     */
    @Test public void testToXXX() {
        /*
         * l01: 最普遍的搜集
         * l02: 自己提供集合实现类, LinkedList
         * s01: 需要重写hashCode和equals方法, 否则也可以运行但不会去重复(默认仅仅根据地址判断)
         * s02: 必须实现Comparable接口,且key不能为空
         */
        List<Person>       l01 = plist.stream().collect(toList());                              
        LinkedList<Person> l02 = plist.stream().collect(toCollection(LinkedList::new));   
        Set<Person>        s01 = plist.stream().collect(toSet());                                
        TreeSet<Person>    s02 = plist.stream().collect(toCollection(TreeSet::new));         
        print(l01, l02, s01, s02);
        
        /*
         * m01: 键不能重复,值不能为空
         * m02: 指定重复键的值处理方式后,键可以重复了. 值仍然不能为空
         * m03: 指定Map类型
         * m04: 演示toConcurrentMap
         */
        Map<String, Integer> m01 = plist.stream().collect(toMap(Person::getName, Person::getAge));
        Map<String, Integer> m02 = plist.stream().collect(toMap(Person::getName, Person::getAge,(v1,v2) -> v1)); 
        Map<String, Integer> m03 = plist.stream().collect(toMap(Person::getName, Person::getAge,(v1,v2) -> v1, LinkedHashMap::new)); 
        Map<String, Integer> m04 = plist.stream().collect(toConcurrentMap(Person::getName, Person::getAge, (v1,v2) -> v1));
        print(m01, m02, m03 ,m04);
        
        /*
         * 指定不可变的结果
         */
        List<Person>         l03 = plist.stream().collect(toUnmodifiableList());
        Set<Person>          s03 = plist.stream().collect(toUnmodifiableSet());
        Map<String, Integer> m06 = plist.stream().collect(toUnmodifiableMap(Person::getName, Person::getAge,(v1,v2) -> v1));
        print(l03, s03);
        
        Person person = new Person("hepengju", 28, 20_000.0);
        l01.add(person); // OK
        l02.add(person); // OK
        l03.add(person); // java.lang.UnsupportedOperationException
        
        m01.put("a", 1); // OK
        m06.put("a", 1); // java.lang.UnsupportedOperationException
        
    }
    
    /**
     * 测试 summarizing, averaging, summing
     */
    @Test public void testSummarizingAveragingSumming() {
        IntSummaryStatistics summaryAge = plist.stream().collect(summarizingInt(Person::getAge));
        Double               averageAge = plist.stream().collect(averagingInt(Person::getAge));
        Integer              sumAge     = plist.stream().collect(summingInt(Person::getAge));
        print(summaryAge, averageAge, sumAge);
        //IntSummaryStatistics{count=5, sum=142, min=10, average=28.400000, max=48}, class: class java.util.IntSummaryStatistics
        //28.4, class: class java.lang.Double
        //142, class: class java.lang.Integer

    }
    
    /**
     * 测试 counting, filtering, mapping, flatMapping, maxBy, minBy; 并与stream的操作对比
     */
    @Test public void testCountingEtc() {
        long c01 = plist.stream().count();
        Long c11 = plist.stream().collect(counting());
        
        List<Person> l01 = plist.stream().filter(p -> p.getAge() > 40).collect(toList());
        List<Person> l11 = plist.stream().collect(filtering(p -> p.getAge() > 40, toList()));
        
        List<String> l02 = plist.stream().map(Person::getName).collect(toList());
        List<String> l12 = plist.stream().collect(mapping(Person::getName, toList()));
        
        List<String> l03 = plist.stream()
                                .map(Person::getName)               //映射为名字
                                .flatMapToInt(String::chars)        //每个名字映射为char流,并扁平化
                                .mapToObj(Character::toString)      //转换为字符串
                                .collect(toList());                 //搜集: [孙, 悟, 空, 猪, 八, 戒, 唐, 三, 藏, 沙, 悟, 净, 小, 妖]
        List<String> l13 = plist.stream()
                                .map(Person::getName)               
                                .collect(flatMapping(str -> str.chars().mapToObj(Character::toString), toList()));
              
        Comparator<Person> comparator = nullsLast(comparing(Person::getName));
        Optional<Person> o01 = plist.stream().max(comparator);
        Optional<Person> o11 = plist.parallelStream().collect(maxBy(comparator));
        Optional<Person> o02 = plist.stream().min(comparator);
        Optional<Person> o12 = plist.parallelStream().collect(minBy(comparator));
        
        print(c01, c11, l01, l11, l02, l12, l03, l13, o01, o11, o02, o12); 
    }
    
    /**
     * 测试 groupingBy 和 partitioningBy
     */
    @Test public void testGroupingByAndPartioningBy() {
        Map<String, List<Person>> m01 = plist.stream().collect(groupingBy(Person::getName));
        Map<String, Set<Double>>  m02 = plist.stream().collect(groupingBy(Person::getName, mapping(Person::getSalary,toSet())));
        Map<String, Set<Double>>  m03 = plist.stream().collect(groupingBy(Person::getName,LinkedHashMap::new, mapping(Person::getSalary,toSet())));
        Map<String, List<Person>> m04 = plist.stream().collect(groupingByConcurrent(Person::getName));
        
        Map<Boolean, List<Person>> m05 = plist.stream().collect(partitioningBy(p -> p.getAge() > 30));
        Map<Boolean, List<String>> m06 = plist.stream().collect(partitioningBy(p -> p.getAge() > 30, mapping(Person::getName,toList())));
      
        print(m01, m02, m03, m04, m05, m06);
    }
    
    /**
     * 测试 joining
     */
    @Test public void testJoining() {
        String s01 = plist.stream().collect(mapping(Person::getName, joining()));           // 连接
        String s02 = plist.stream().map(Person::getName).collect(joining(","));             // 连接,指定分隔符
        String s03 = plist.stream().map(Person::getName).collect(joining(",", "{", "}"));   // 连接,指定分隔符及前缀后缀
        print(s01, s02, s03);
    }
    
    /**
     * 测试 reducing
     */
    @Test public void testReducing() {
        Optional<String> r01 = plist.stream().map(Person::getName).reduce((a, b) -> a + ":" + b);           // 规约
        String           r02 = plist.stream().map(Person::getName).reduce("", (a, b) -> a + ":" + b);                 // 规约, 给定第一个(不会返回Optional)
        Integer          r03 = plist.stream().map(Person::getName).reduce(0, (i, s) -> i + s.length(), Integer::sum);// 规约, 给定U类型/输入U和T返回U的BI函数/U的二元操作符
        print(r01, r02, r03);
    }
    
    /**
     * 测试其他
     * 
     * collectingAndThen 搜集完的额外处理
     * teeing            jdk12才有 TODO
     */
    @Test public void testOther() {
        // class: class java.util.Collections$SynchronizedRandomAccessList
        List<Person> l01 = plist.stream().collect(collectingAndThen(toList(), Collections::synchronizedList));
        print(l01);
    }
    
    public void print(Object... objs) {
        for (Object obj : objs) {
            System.out.println(Objects.requireNonNull(obj) + ", class: " + obj.getClass());
        }
    }
}
