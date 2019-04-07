package com.hepengju.java12.new01_switch;

import org.junit.Test;

/**
 * switch: 语句和表达式
 *
 * @see <a href="https://bugs.java.com/bugdatabase/view_bug.do?bug_id=JDK-8192963">JEP 325: Switch Expressions</a>
 *
 * @author hepengju
 */
public class _Switch {

    //DayEnum day = DayEnum.MON;
    private DayEnum day = DayEnum.SUN;

    /**
     * 旧版写法
     */
    @Test
    public void testOldSwitch() {
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
     * 新版简化写法
     */
    @Test
    public void testNewSwitch() {
        System.out.print("NewSwitch: ");
        // 不存在则什么也不处理, 比如下面不存在SUN
        switch (day) {
            case MON, TUE, WED -> System.out.println("123");
            case THU, FRI      -> System.out.println("45");
            case SAT           -> System.out.println("party");
        }
    }

    /**
     * 表达式(简单): 表达式必须覆盖所有值或者使用default, 否则编译报错
     */
    @Test public void testExpression01() {
        String str = switch (day) {
            case MON, TUE, WED -> "123";
            case THU, FRI      -> "45";
            case SAT           -> "party";
            default            -> "haha";
          //case SAT, SUN      -> "party";
        };
        System.out.println("switch expression: " + str);
    }

    /**
     * 表达式(语句块): 可以写一个复杂的语句块,最后break后面返回结果值
     */
    @Test public void testExpression02() {

        String str = switch (day) {
            case MON, TUE, WED -> "123";
            case THU, FRI -> {
                String result = "4" + 5;
                break result;
            }
            case SAT, SUN -> {
                String result = "party";
                break result;
            }
            //case SAT, SUN      -> "party";
        };
        System.out.println("switch expression: " + str);
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * 日枚举: 构造方法里面有参数
     */
    enum DayEnum {
        MON(1), TUE(2), WED(3), THU(4), FRI(5), SAT(6), SUN(7);
        private int value;
        DayEnum(int value) {
            this.value = value;
        }
        public int getValue() {
            return this.value;
        }

    }
}
