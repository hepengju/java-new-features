package com.hepengju.java07.new03_switchString;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Switch对字符串的支持
 * 
 * <pre>
 *  说明: switch 语句中的变量类型可以是： byte、short、int、char 和 枚举
 *       从 Java SE 7 开始，switch 支持字符串 String 类型了，同时 case 标签必须为字符串常量或字面量。
 * </pre>
 * 
 * @author hepengju
 *
 */
public class _SwitchString {

    @Test public void testSwitchString() {
        List<String> list = Arrays.asList("孙悟空","猪八戒","唐玄奘","沙悟净","白骨精");
        
        for (String name : list) {
            switch (name) {
            case "孙悟空":
                System.out.println("大师兄好样的");
                break;
            case "猪八戒":
                System.out.println("二师兄喜欢美女,喜欢吃");
                break;
            case "唐玄奘":
                System.out.println("我是师傅,听我的");
                break;
            case "沙悟净":
                System.out.println("大师兄, 师傅被妖怪抓走啦");
                break;
            default:
                System.out.println("我是谁....");
                break;
            }
        }
    }
    
    
}
