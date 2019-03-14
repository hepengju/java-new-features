package com.hepengju.java08.new05_interfaceMethod;

import org.junit.Test;

/**
 * 接口方法
 * 
 * <pre>
 *  说明: 接口中可以写"默认方法"和"静态方法"了
 *  冲突: 
 *      1.实现类的方法与接口默认方法相同时: 接口默认方法的"类优先"原则
 *      2.实现两个接口的默认方法相同时, 必须重写此方法
 *  
 *  思考: 
 *      1.为什么需要加入"默认方法"呢?
 *          a) Iterable接口(Collection接口的父接口)想添加forEach方法,
 *              由于其实现类有非常的多(jdk自身及第三方类库)都需要修改.
 *              而这个方法的实现其实是很简单的,向接口中添加默认方法可以使用最小代价实现兼容性和代码复用
 *          b) WindowListener 接口, WindowAdapter 适配器
 *              之前设计接口, 另外提供xxxAdapter抽象类的空实现方法, 以便方便使用.
 *              加入默认方法后, 接口本身就可以提供空实现了, 不必再使用适配器
 *              比如SpringMVC5的处理器拦截器 HandlerInterceptor 就添加了默认实现, 以便实现本接口的时候 @Overide 相应方法就好
 *               
 *      2.为什么需要加入"静态方法"呢?
 *          Collection, Collections 工具类
 *          Path      , Paths 工具类
 *          既然第一点已经加入默认方法,那就在加入静态方法是的接口和工具类统一在一起
 *      
 *      3.java9中接口也可以加入私有方法
 *          由于多个默认方法或静态方法可能需要代码的复用抽取, 因此java9又加入了可以写私有方法
 * </pre>
 * 
 * @author hepengju
 *
 */
public class _InterfaceMethod {

    /**
     * 测试默认方法和静态方法
     */
    @Test public void testDefaultMethodAndStaticMethod() {
        MyInter.out("我是静态方法");
        
        MyInter mi = s -> s + "**";
        String src = "apply";
        String apply = mi.apply(src);
        String applyTwoTime = mi.applyTwoTime(src);
        System.out.println(apply);
        System.out.println(applyTwoTime);
    }
    
    @FunctionalInterface
    interface MyInter{
        String apply(String src);
        
        // 默认方法
        default String applyTwoTime(String src) {
            String one = apply(src);
            String two = apply(one);
            return two;
        }
        
        // 静态方法
        static void out(String out) {
            System.out.println(out);
        }
    }
}
