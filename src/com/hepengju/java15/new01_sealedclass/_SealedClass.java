package com.hepengju.java15.new01_sealedclass;


/**
 * 密封的类和接口(预览)
 *
 * <pre>
 *  概述: 通过密封的类和接口来增强 Java 编程语言，这是新的预览特性。
 *
 *  目的: 用于限制超类的使用，密封的类和接口限制其它可能继承或实现它们的其它类或接口。
 *
 *      这个特性的目标包括——允许类或接口的开发者来控制哪些代码负责实现，提供了比限制使用超类的访问修饰符声明方式更多选择,
 * 并通过支持对模式的详尽分析而支持模式匹配的未来发展。
 *      在Java中，类层次结构通过继承实现代码的重用，父类的方法可以被许多子类继承。
 * 但是，类层次结构的目的并不总是重用代码。有时，其目的是对域中存在的各种可能性进行建模，
 * 例如图形库支持的形状类型或金融应用程序支持的贷款类型。当以这种方式使用类层次结构时，我们可能需要限制子类集从而来简化建模。
 *
 *  使用:
 *      使用修饰符sealed，您可以将一个类声明为密封类。密封的类使用reserved关键字permits列出可以直接扩展它的类。
 *      1. 密封类必须有子类
 *      2. 子类应为 sealed、non-sealed 或 final 修饰符
 * </pre>
 *
 * @see <a href="https://openjdk.java.net/jeps/360">JEP 360: Sealed Classes (Preview)</a>
 */
public class _SealedClass {}

// 示例1: 图形, 只允许 圆形,矩形,正方形
sealed class Shape permits Circle, Rectangle, Square, ExtShape {};
final class Circle extends Shape {}
final class Rectangle extends Shape {}
final class Square extends Shape {}

// 以下仅仅为展示语法, 无实际意义
sealed class ExtShape extends Shape permits  ExtShape01, ExtShape02, ExtShapeXX {}
final class ExtShape01 extends ExtShape {}
final class ExtShape02 extends ExtShape {}
non-sealed class ExtShapeXX extends ExtShape {}

// 示例2: 和record关键字一起使用
sealed interface Expr permits ConstantExpr, PlusExpr, TimesExpr, NegExpr {}
record ConstantExpr(int i)       implements Expr {}
record PlusExpr(Expr a, Expr b)  implements Expr {}
record TimesExpr(Expr a, Expr b) implements Expr {}
record NegExpr(Expr e)           implements Expr {}