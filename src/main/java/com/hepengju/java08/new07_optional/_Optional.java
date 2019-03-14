package com.hepengju.java08.new07_optional;

import java.util.Optional;

import org.junit.Test;

/**
 * 
 * Optional
 * 
 * <pre>
 *  说明: Optional<T> 是一个容器类, 代表一个值存在或不存在.
 *       原来用null表示一个值不存在, 现在Optional可以更好的表达这个概念, 并且可以最大限度避免空指针异常
 *  
 *  常用方法:
 *      * Optional.of(T t)            创建一个Optional实例
 *      * Optional.empty()            创建一个空的Optional示例
 *      * Optional.ofNullable(T t)    若t不为null创建Optional实例,否则创建空示例
 *      * isPresent()                 判断是否包含值
 *      * orElse(T t)                 如果调用对象包含值,返回该值,否则返回t
 *      * orElseGet(Supplier s)       如果调用对象包含值,返回该值,否则返回s获取的值
 *      * map(Function f)             如果有值对其处理并返回处理后的Optional,否则返回Optional.empty()
 *      * flatMap(Function f)         与map类似,要求返回值必须是Optional
 * </pre>
 * 
 * @author hepengju
 *
 */
public class _Optional {

    /**
     * Optional
     */
    @Test public void testOptional() {
        Optional<Integer> op = Optional.of(1);
        System.out.println(op.isPresent());      // true
        System.out.println(op.get());            // 1
        System.out.println(op.orElse(2));        // 1
        System.out.println(op.map(x -> x *2 ));  // Optional[2]
    }
}
