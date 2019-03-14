package com.hepengju.java10.new11_other;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.junit.Test;

/**
 * 
 * Java10 新增扩展API类库
 * 
 * <pre>
 *  新增方法：
 *            * copyOf方法
 *            * 重载toString()方法
 *            * 流新增构造方法
 *            * transferTo方法
 *            * Formatter和Scanner新增构造
 * </pre>
 * 
 * 
 * @author WGR
 *
 */
public class _API {
    
    /**
     * List/Set/Map 新增加了一个静态方法copyOf()
     */
    @Test
    public void testCopyOf() {
        var list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        
        var list2 = List.copyOf(list);
        list2.stream().forEach(System.out::println); // a b c d
        
        var set = new HashSet<String>();
        set.add("赵");
        set.add("钱");
        set.add("孙");
        set.add("李");
        
        var set2 = Set.copyOf(set);
        set2.stream().forEach(System.out::println); // 赵 钱 孙 李
        
        var map = new HashMap<>();
        map.put("k1", "a");
        map.put("k2", "b");
        
        var map2 = Map.copyOf(map);
        var keys = map2.keySet();
        for (Object object : keys) {
            System.out.println(map2.get(object));   // a b
        }
    }
    
    /**
     * 
     * 重载toString()方法，通过使用指定的字符集编码字节，将缓冲区的内容转换为字符串。
     * @throws UnsupportedEncodingException
     * 
     */
    @Test
    public void testToString() throws UnsupportedEncodingException {
        
        String str = "上海天正";
        ByteArrayInputStream bis = new ByteArrayInputStream(str.getBytes("gbk"));
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        
        int c = 0;
        while((c = bis.read()) != -1) {
            bos.write(c);
        }
        //bos.toString() 默认的使用的UTF-8编码
        System.out.println(bos.toString());
        System.out.println(bos.toString("gbk"));

    }
    
    /**
     * java.io.PrintStream、java.io.PrintlWriter新增构造方法
     * @throws UnsupportedEncodingException 
     * @throws FileNotFoundException 
     */
    @Test
    public void testStructure1() throws Exception {
        
        String str = "上海天正";
        var p = new PrintWriter("d:/aa.txt", "gbk");
        p.println(str);
        p.flush();
        p.close();

    }
    
    /**
     * Reader:transferTo方法
     * 注：java9新增的是inputStream的transferTo方法
     * 
     * @throws Exception 
     * 
     */
    @Test
    public void testTransferTo() throws Exception {
        
        var reader = new BufferedReader(new InputStreamReader(new FileInputStream("d:/aa.txt"), "gbk"));
        var p = new PrintWriter(new File("d:/cc.txt"));
        reader.transferTo(p);
        p.flush();
        p.close();
        reader.close();

    }
    
    /**
     * 新增构造方法
     * @throws Exception
     */
    @Test
    public void testStructure2() throws Exception {
        
        @SuppressWarnings("resource")
        var scan = new Scanner(new FileInputStream(new File("d:/aa.txt")),"gbk");
        scan.useDelimiter(" |,");
        while(scan.hasNext()) {
            System.out.println(scan.next());
        }

    }



}
