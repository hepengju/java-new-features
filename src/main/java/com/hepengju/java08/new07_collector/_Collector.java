package com.hepengju.java08.new07_collector;

/**
 * 搜集器
 * 
 * <pre>
 *  分类
 *      1. 规约: reducing
 *      2. 统计: summarizing
 *      3. 分组: grouping
 *      4. 分区: partitioning
 *      5. 自定义: self customing
 *      
 *  API:
 *  Collector 接口
 *      - Supplier<A> supplier();            供给可变容器
 *      - BiConsumer<A, T> accumulator();    将元素添加到容器
 *      - BinaryOperator<A> combiner();      将两部分容器合并为一个容器
 *      - Function<A, R> finisher();         执行最终转换
 *      
 *  Collectors 工具类
 *      - averagingInt/Long/Double
 *      - summingInt/Long/Double
 *      - 
 *      - 
 *      - 
 * </pre>
 * 
 * @author hepengju
 *
 */
public class _Collector {

}
