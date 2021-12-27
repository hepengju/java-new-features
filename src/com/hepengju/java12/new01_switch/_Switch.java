package com.hepengju.java12.new01_switch;

import org.junit.Assert;
import org.junit.Test;

/**
 * switch: 语句和表达式(预览)
 *
 * <pre>
 *  传统的Switch语句的问题:
 *      - 匹配是自上而下的, 如果忘记写break, 后面的case语句不论匹配与否都会执行
 *      - 所有的case语句共用一个块范围, 在不同的case语句定义的变量名不能重复
 *      - 不能在一个case里写多个执行结果一致的条件
 *      - 整个switch不能作为表达式返回值
 *
 *  Java 12对switch声明语句进行扩展, 可将其作为增强版的 switch 语句或称为 "switch 表达式"来写出更加简化的代码
 *      - 不仅可以作为语句（statement）,还可以作为表达式（expression）
 *      - 两种写法都可以使用传统的 switch 语法, 或者使用简化的“case L ->”模式匹配语法作用于不同范围并控制执行流
 *      - 为了保持兼容性, case 条件语句中依然可以使用字符 : ,但不能混用 -> 和 : ,否则会有编译错误
 *
 *  预览语言的说明
 *      Switch 表达式也是作为预览语言功能的第一个语言改动被引入新版 Java 中来的, 预览语言功能的想法是在 2018 年
 * 初被引入 Java 中的, 本质上讲这是一种引入新特性的测试版的方法. 通过这种方式, 能够根据用户反馈进行升级、更改, 
 * 在极端情况下, 如果没有被很好的接纳, 则可以完全删除该功能. 预览功能的关键在于它们没有被包含在Java SE规范中
 *      编译的时候需要追加参数: -parameters --enable-preview
 * </pre>
 *
 * @see <a href="https://bugs.java.com/bugdatabase/view_bug.do?bug_id=JDK-8192963">JEP 325: Switch Expressions</a>
 * @author hepengju
 */
public class _Switch {

    /**
     * 日枚举
     */
    enum DayEnum {MON, TUE, WED, THU, FRI, SAT, SUN;}
    private DayEnum day = DayEnum.MON;

    /**
     * 旧版switch语句写法
     */
    @Test
    public void testSwitchOld() {
        System.out.print("OldSwitch: ");
        switch (day) {
            case MON:
            case TUE:
            case WED:
                System.out.println("123");
                break;
            case THU:
            case FRI:
                System.out.println("45");
                break;
            case SAT:
                System.out.println("party");
                break;
            case SUN:
                throw new IllegalArgumentException("sun is not allowed");
        }
    }

    /**
     * 新版switch语句的简化写法
     */
    @Test
    public void testSwitchNew() {
        System.out.print("NewSwitch: ");
        // 不存在则什么也不处理, 比如下面不存在SUN
        switch (day) {
            case MON, TUE, WED -> System.out.println("123");
            case THU, FRI      -> System.out.println("45");
            case SAT           -> System.out.println("party");
        }
    }

    /**
     * switch表达式(简单): 表达式必须覆盖所有值或者使用default, 否则编译报错
     */
    @Test
    public void testSwitchExpressionSimple() {
        String str = switch (day) {
            case MON, TUE, WED -> "123";
            case THU, FRI      -> "45";
            case SAT           -> "party";
            default            -> "default";
        };

        Assert.assertEquals("123", str);
    }

    /**
     * switch表达式(语句块): 可以写一个复杂的语句块, 最后yield返回结果值(jdk12是break关键字)
     */
    @Test public void testSwitchExpressionWithYield() {
        String str = switch (day) {
            case MON, TUE, WED -> "123";
            case THU, FRI -> {
                String result = "4" + 5;
                yield result;
            }
            case SAT, SUN -> {
                String result = "party";
                yield result;
            }
        };

        Assert.assertEquals("123", str);
    }

}
