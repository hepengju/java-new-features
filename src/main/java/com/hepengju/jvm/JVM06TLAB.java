package com.hepengju.jvm;

/**
 * JVM虚拟机参数: TLAB区
 *
 * <pre>
 *     -XX:MaxTenuringThreshold     设置经过多少次GC晋升老年代, 默认为15
 *     -XX:PreTenureSizeThreshold   设置超过多大的对象直接晋升老年代
 *
 *     -XX:+UseTLAB                 使用TLAB(默认)
 *     -XX:+TLABSize                设置TLAB大小
 *     -XX:TLABRefillWasteFraction  设置进入TLAB空间的单个对象大小, 比例值, 默认为64, 即如果对象大于整个空间的1/64则在堆创建对象
 *     -XX:+PrintTLAB               查看TLAB信息
 *     -XX:ResizeTLAB               自动调整TLABRefillWasteFraction阈值
 *     -XX:-DoEscapeAyalysis        (加上此参数, 才能在控制台查看TLAB信息)
 * </pre>
 */
public class JVM06TLAB {
}
