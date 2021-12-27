package com.hepengju.java13.new01_switch;

/**
 * switch: 语句和表达式(预览)
 *
 * <pre>
 *     在JDK 12中引入了Switch表达式作为预览特性。JDK 13提出了第二个switch表达式预览。JEP 354修改了这个特性，
 * 它引入了yield语句，用于返回值。这意味着，switch表达式(返回值)应该使用yield, switch语句(不返回值)应该使用
 * break。
 *      在 JDK 12中有一个，但是要进行一个更改：要从 switch 表达式中生成一个值 break，要删除with value语句以支持a
 * yield 声明。目的是扩展，switch 以便它可以用作语句或表达式，因此两个表单既可以使用 case ... : 带有连贯符号的
 * 传统标签，也可以使用新 case … -> 标签，而不需要通过，还有一个新的语句用于从 switch 表达式中产生值。这些
 * 更改将简化编码并为模式匹配做好准备。
 *
 *      和return的区别在于：return会直接跳出当前循环或者方法，而yield只会跳出当前switch块。
 *
 *  参考: java12新特性里面的_Switch, java13仅仅对于switch表达式的返回值由 "break 值" --> "yield 值"
 * </pre>
 *
 * @author hepengju
 */
public class _Switch { }
