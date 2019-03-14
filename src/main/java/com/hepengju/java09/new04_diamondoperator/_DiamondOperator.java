package com.hepengju.java09.new04_diamondoperator;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * 钻石操作符
 * 
 * <pre>
 *  说明：
 *      * Java7给出的钻石操作符使我们编写代码更简单了。
 *      * Java7中钻石操作符不允许在匿名类上使用,但在Java9中改善了这一情况，允许钻石操作符在匿名类上使用。
 * </pre>
 * 
 * @author WGR
 *
 */
public class _DiamondOperator {
    
    /**
     * set相当于创建一个继承于HashSet的匿名子类的对象 
     */
    @SuppressWarnings("serial")
    @Test
    public void diamondOperator(){

        Set<String> set = new HashSet<>(){};//编译通过
        set.add("MM");
        set.add("JJ");
        set.add("GG");
        set.add("DD");

        for(String s : set){
            System.out.println(s);
        }

    }

}
