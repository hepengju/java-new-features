package com.hepengju.java15.new02_hiddenclass;

/**
 * 隐藏类: TODO 没太看懂, 也就不知道如何编写示例
 *
 * <pre>
 *      该提案通过启用标准 API 来定义无法发现且具有有限生命周期的隐藏类，从而提高 JVM 上所有语言的效率。
 * JDK内部和外部的框架将能够动态生成类，而这些类可以定义隐藏类。通常来说基于JVM的很多语言都有动态生成类的机制，
 * 这样可以提高语言的灵活性和效率。
 *      - 隐藏类天生为框架设计的，在运行时生成内部的class。
 *      - 隐藏类只能通过反射访问，不能直接被其他类的字节码访问。
 *      - 隐藏类可以独立于其他类加载、卸载，这可以减少框架的内存占用。
 *
 *      Hidden Classes是什么呢？
 *      Hidden Classes就是不能直接被其他class的二进制代码使用的class。Hidden Classes主要被一些框架用来生成运行时类，
 * 但是这些类不是被用来直接使用的，而是通过反射机制来调用。比如在JDK8中引入的lambda表达式，JVM并不会在编译的时候将lambda
 * 表达式转换成为专门的类，而是在运行时将相应的字节码动态生成相应的类对象。另外使用动态代理也可以为某些类生成新的动态类。
 *
 *      那么我们希望这些动态生成的类需要具有什么特性呢？
 *      - 不可发现性。因为我们是为某些静态的类动态生成的动态类，所以我们希望把这个动态生成的类看做是静态类的一部分。所以我们不希望除了该静态类之外的其他机制发现。
 *      - 访问控制。我们希望在访问控制静态类的同时，也能控制到动态生成的类。
 *      - 生命周期。动态生成类的生命周期一般都比较短，我们并不需要将其保存和静态类的生命周期一致。
 *
 *      API的支持
 *      所以我们需要一些API来定义无法发现的且具有有限生命周期的隐藏类。这将提高所有基于JVM的语言实现的效率。
 *      比如：
 *          java.lang.reflect.Proxy可以定义隐藏类作为实现代理接口的代理类。
 *          java.lang.invoke.StringConcatFactory可以生成隐藏类来保存常量连接方法；
 *          java.lang.invoke.LambdaMetaFactory可以生成隐藏的nestmate类，以容纳访问封闭变量的lambda主体；
 *
 *      普通类是通过调用ClassLoader::defineClass创建的，而隐藏类是通过调用Lookup::defineHiddenClass创建的。
 * 这使JVM从提供的字节中派生一个隐藏类，链接该隐藏类，并返回提供对隐藏类的反射访问的查找对象。调用程序可以通过返回的
 * 查找对象来获取隐藏类的Class对象。
 * </pre>
 *
 * @see <a href="https://openjdk.java.net/jeps/371">JEP 371: Hidden Classes</a>
 */
public class _HiddenClass {
}