package com.hepengju.java11.new09_zgc;


/**
 * JEP 333: ZGC: A Scalable Low-Latency Garbage Collector(Experimental)(重磅)
 * ZGC垃圾收集器
 * 
 * <pre>
 *    实现：
 *           * 停顿时间不超过10ms
 *           * 停顿时间不随heap大小或存活对象大小增大而增大
 *           * 可以处理从几百兆到几T的内存大小
 *    概述：
 *           * ZGC是个令人兴奋的新垃圾收集器，旨在为大堆提供非常低的暂停时间。
 *           * 它通过使用着色指针和读屏障来实现这一点，这些是Hotspot新近开发的GC技术，并为未来增加了很多可能性。
 *    用法:   
 *           * -XX:+UseZGC
 *    测试：
 *           * 由于ZGC不支持Windows系统，可以在Linux环境自测。
 *           
 *    @See <a href="https://mp.weixin.qq.com/s/nAjPKSj6rqB_eaqWtoJsgw">
 *    @See <a href="https://segmentfault.com/a/1190000015725327">
 *    @See <a href="http://openjdk.java.net/jeps/333">
 *    
 * </pre>
 * 
 * @author WGR
 * 
 */
public class _ZGC {
    
    /*
     * Error occurred during initialization of VM 
     * Option -XX:+UseZGC not supported
     */

}
