package com.hepengju.java17.new02_switch;

import org.junit.Test;

/**
 * 为 switch 支持模式匹配
 *
 * @see com.hepengju.java12.new01_switch._Switch
 * @see com.hepengju.java13.new01_switch._Switch
 */
public class _Switch {

    @Test
    public void testPatternMatchingSwitchStatement() {
        System.out.println(formatter(99));
        System.out.println(formatter(999999999999999999L));
        System.out.println(formatter(99.99));
        System.out.println(formatter("java"));
        System.out.println(formatter(null));
        System.out.println("-------------------------------");

        System.out.println(formatterPatternSwitch(99));
        System.out.println(formatterPatternSwitch(999999999999999999L));
        System.out.println(formatterPatternSwitch(99.99));
        System.out.println(formatterPatternSwitch("java"));
        System.out.println(formatterPatternSwitch(null));
    }

    /**
     * 模式匹配 + Switch 写法
     */
    String formatterPatternSwitch(Object o) {
        return switch (o) {
            case null      -> null; // case null存在则匹配null的处理; 否则和之前版本一致, 抛出NPE异常(并不会进入default语句)
            case Integer i -> String.format("int %d", i);
            case Long l    -> String.format("long %d", l);
            case Double d  -> String.format("double %f", d);
            case String s  -> String.format("String %s", s);
            default        -> o.toString();
        };
    }

    /**
     * 模式匹配写法
     */
    String formatter(Object o) {
        if (o == null) return null;

        String formatted = "unknown";
        if (o instanceof Integer i) {
            formatted = String.format("int %d", i);
        } else if (o instanceof Long l) {
            formatted = String.format("long %d", l);
        } else if (o instanceof Double d) {
            formatted = String.format("double %f", d);
        } else if (o instanceof String s) {
            formatted = String.format("String %s", s);
        }
        return formatted;
    }

    @Test
    public void testSwitchLabel() {
        Object o = 123L;
        String formatted = switch (o) {
            case null      -> null;
            case Integer i -> String.format("int %d", i);
            case Long l    -> String.format("long %d", l);
            case Double d  -> String.format("double %f", d);
            case String s  -> String.format("String %s", s);
            default -> o.toString();
        };

        System.out.println(formatted);
    }
}
