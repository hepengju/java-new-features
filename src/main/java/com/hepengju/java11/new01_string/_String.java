package com.hepengju.java11.new01_string;

import java.io.FileInputStream;

import org.junit.Test;

/**
 * 
 * 本类对String新增方法探索
 * 
 * <pre>
 *  新增阐述：
 *            * 新增strip、stripLeading、stripTrailing方法
 *            * 新增repeat方法，重复
 *            * 新增lines方法,变成流，方便操作
 * </pre>
 * @author WGR
 * 
 */

public class _String {
    
    /**
     * 新增isBlank方法
     * 判断字符串中的字符是否都是空白
     */
    @Test
    public void testIsBlank() {
        
        String string = " \t  \r\n ";
        System.out.println(string.isBlank()); //true
        
    }
    
    /**
     * 新增strip、stripLeading、stripTrailing方法
     * strip         去重字符串首尾的空白, 包括英文和其他所有语言中的空白字符(功能比trim强大)
     * stripLeading  去重字符串首部的空白
     * stripTrailing 去重字符串尾部的空白
     * trim          去重字符串首尾的空白字符, 只能去除码值小于等于32的空白字符
     */
    @Test
    public void testStrip() {
        
        String string = " \t  \r\n abc \t　";
        String string2 = string.strip(); 
        System.out.println(string2);           //abc
        System.out.println(string2.length());  //3
        
        String string3 = string.trim(); 
        System.out.println(string3);           //abc    　;部分特殊空格去掉不了；
        System.out.println(string3.length());  //6
        
        String string4 = string.stripLeading();
        System.out.println(string4);           //abc   　;尾部空格没有去掉; 
        System.out.println(string4.length());  //6
        
        String string5 = string.stripTrailing(); 
        System.out.println(string5);           //  abc;
        System.out.println(string5.length());  //10
        
    }
    
    /**
     * 新增repeat方法，重复
     */
    @Test
    public void testRepeat()  {
        String string = "Java";
        String string2 = string.repeat(5);          //JavaJavaJavaJavaJava
        System.out.println(string2);
    }
    
    /**
     * 新增lines方法,变成流，方便操作
     * @throws Exception
     */
    @Test
    public void testLines() throws Exception {
        FileInputStream fis = new FileInputStream("src/com/hepengju/java11/new03_string/_String.java");
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        String string = new String(buffer);
        string.lines().forEach(System.out::println); //会输出整个文件内容
    }
    
    

}
