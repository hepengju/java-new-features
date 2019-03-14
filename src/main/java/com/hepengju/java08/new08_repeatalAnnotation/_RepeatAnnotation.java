package com.hepengju.java08.new08_repeatalAnnotation;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;

/**
 * 可重复注解, 类型注解
 * 
 * @author hepengju
 *
 */
public class _RepeatAnnotation {

    /**
     * 可重复注解在标注一次和标注多次时的读取
     */
    @Test public void testAnnotation() throws NoSuchMethodException, SecurityException {
        Class<?> clazz = this.getClass(); 
        Method method01 = clazz.getDeclaredMethod("show01", String.class);
        Method method02 = clazz.getDeclaredMethod("show02", String.class);
        
        // 1. 旧方法读取
        // 1.1 可重复注解标注一次时, 正常读取
        MyAnno anno01 = method01.getAnnotation(MyAnno.class);
        MyAnno anno02 = method02.getAnnotation(MyAnno.class);
        System.out.println(anno01);   // @com.hepengju.java08.new08_repeatalAnnotation.MyAnno(value="three")
        System.out.println(anno02);   // null
        
        // 1.2 可重复注解标注多次时, 编译后其实标注的是它的容器类 MyAnnos
        MyAnnos annos01 = method01.getAnnotation(MyAnnos.class);
        MyAnnos annos02 = method02.getAnnotation(MyAnnos.class);
        System.out.println(annos01); // null
        System.out.println(annos02); // @com.hepengju.java08.new08_repeatalAnnotation.MyAnnos(value={@com.hepengju.java08.new08_repeatalAnnotation.MyAnno(value="one"), @com.hepengju.java08.new08_repeatalAnnotation.MyAnno(value="two")})
        
        // 2. 新方法读取
        // 2.1 可重复注解标注一次时, 也可以读到
        MyAnno[] as01 = method01.getAnnotationsByType(MyAnno.class); 
        MyAnnos[] as02 = method01.getAnnotationsByType(MyAnnos.class);
        System.out.println(Arrays.toString(as01)); // [@com.hepengju.java08.new08_repeatalAnnotation.MyAnno(value="three")]
        System.out.println(Arrays.toString(as02)); // []
        
        // 2.2 可重复注解标注多次时
        MyAnno[] as03 = method02.getAnnotationsByType(MyAnno.class); 
        MyAnnos[] as04 = method02.getAnnotationsByType(MyAnnos.class);
        System.out.println(Arrays.toString(as03)); // [@com.hepengju.java08.new08_repeatalAnnotation.MyAnno(value="one"), @com.hepengju.java08.new08_repeatalAnnotation.MyAnno(value="two")]
        System.out.println(Arrays.toString(as04)); // [@com.hepengju.java08.new08_repeatalAnnotation.MyAnnos(value={@com.hepengju.java08.new08_repeatalAnnotation.MyAnno(value="one"), @com.hepengju.java08.new08_repeatalAnnotation.MyAnno(value="two")})]

    }
    
    /**
     * 类型参数读取
     */
    @Test public void testTypeParameter() throws NoSuchMethodException, SecurityException {
        Class<?> clazz = this.getClass(); 
        Method method01 = clazz.getDeclaredMethod("show01", String.class);
        AnnotatedType[] types = method01.getAnnotatedParameterTypes();
        for (AnnotatedType at : types) {
            MyAnno anno = at.getAnnotation(MyAnno.class);
            System.out.println(anno); // @com.hepengju.java08.new08_repeatalAnnotation.MyAnno(value="src")
        }
    }
    
    /**
     * 可重复注解标注一次的情况
     */
    @MyAnno("three")
    public void show01(@MyAnno("src")String src) {
        @MyAnno("src")int i = 10;
        System.out.println(i + ": " + src);
    }

    /**
     * 可重复注解标注多次的情况
     */
    @MyAnno("one") 
    @MyAnno("two")
    public void show02(@MyAnno("src")String src) {
        @MyAnno("src")int i = 10;
        System.out.println(i + ": " + src);
    }
}
