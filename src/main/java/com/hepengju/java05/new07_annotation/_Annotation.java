package com.hepengju.java05.new07_annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;

import com.hepengju.java05.new05_enum.WorkdayEnum;

/**
 * 注解: 相当于一种标记，在程序中加入注解就等于为程序打上某种标记
 * 
 * <pre>
 *  定义: @interface 注解名称, 所有的注解都是 Annotation 的子类
 *  属性: 类型 属性名();
 *      - 属性可以添加默认值
 *      - value属性特权: 在使用注解时,如果只给名为value的属性赋值,可以不写属性名称,直接给出值
 *      - 注解的属性类型: 8种基本类型,String,Enum,Class,注解类型以及以上类型的一维数组类型
 *      - 当给数组类型的属性赋值时,若数组元素的个数为1,可以省略大括号
 *  限定: 
 *      - 保留策略限定: @Retention
 *      - 作用目标限定: @Target
 *  读取: 
 *      - 要求: 注解的保存策略必须是 RUNTIME
 *      - 说明: 反射注解需要从作用目标上反射
 *          * 类上的注解  : 使用 java.lang.Class<T>
 *          * 方法上的注解: 使用 java.lang.reflect.Method 
 *          * 构造器上注解: 使用 java.lang.reflect.Constructor<T>
 *          * 成员上的注解: 使用 java.lang.reflect.Field
 *      - 方法:
 *          * isAnnotationPresent(Class): 判断注解是否存在
 *          * getAnnotation(Class)      : 返回目标上指定类型的注解
 *          * getAnnotations()          : 返回目标上所有注解
 *          * getDeclaredAnnotations()  : 返回直接存在于此元素上的所有注释。与此接口中的其他方法不同，该方法将忽略继承的注释
 * 
 *  元注解: 
 * 		@Documented  注解是否将包含在JavaDoc中
 *      @Inherited   是否允许子类继承该注解
 *      @Retention   什么时候使用该注解
 * 	        RetentionPolicy.SOURCE  源代码阶段, 编译时丢弃                            比如: @Override
 * 	        RetentionPolicy.CLASS   Class文件中, VM运行时丢弃(默认)
 * 	        RetentionPolicy.RUNTIME Class文件中, VM运行时保留, 可以通过反射获取注解信息
 *      @Target      注解用于什么地方
 * 	        ElementType.TYPE               类,接口(包括注解),枚举
 * 	        ElementType.FIELD              字段(包括枚举常量)
 * 	        ElementType.METHOD             方法
 * 	        ElementType.PARAMETER          参数
 * 	        ElementType.CONSTRUCTOR        构造器
 * 	        ElementType.LOCAL_VARIABLE     局部变量
 * 	        ElementType.ANNOTATION_TYPE    注解类型
 * 	        ElementType.PACKAGE            包
 * 
 *  
 *  JDK1.5内部提供的三个注解: @SuppressWarnings, @Deprecated, @Override
 * </pre>
 * 
 * @see <a href="https://www.cnblogs.com/xdp-gacl/p/3622275.html">Java基础加强总结(一)——注解(Annotation)</a>
 * 
 * @author hepengju
 *
 */

@A_Anno("A_Anno_value")   //一个注解就是一个类，在这里使用了这个注解就是创建了A_Anno类的一个实例对象
@B_Anno(WorkdayEnum.SAT) @C_Anno @_Annotation.D_Anno
public class _Annotation extends Parent{
    
    /**
     * 注解定义,限定及属性
     * 
     * @see A_Anno
     * @see B_Anno
     * @see C_Anno
     * @see D_Anno
     */
    @Test public void testAnnotationDefine() {}
    
    @Retention(RUNTIME)
    @Target(TYPE)
    @interface D_Anno{
        String value() default "";
    }  


    /**
     * 读取类上的注解
     */
    @Test public void testReadAnnotationOnClass() {
        Class<? extends _Annotation> clazz = this.getClass();
        
        println("******类名******");
        println(A_Anno.class); // interface com.hepengju.java05.new07_annotation.A_Anno
        println(B_Anno.class); // interface com.hepengju.java05.new07_annotation.B_Anno
        println(C_Anno.class); // interface com.hepengju.java05.new07_annotation.C_Anno
        println(D_Anno.class); // interface com.hepengju.java05.new07_annotation._Annotation$D_Anno (内部注解)
        
        println("******是否存在******");
        println(clazz.isAnnotationPresent(A_Anno.class));  // false --> 保留策略限制 SOURCE
        println(clazz.isAnnotationPresent(B_Anno.class));  // false --> 保留策略限制 CLASS
        println(clazz.isAnnotationPresent(C_Anno.class));  // true
        println(clazz.isAnnotationPresent(D_Anno.class));  // true
        println(clazz.isAnnotationPresent(P1_Anno.class)); // true
        println(clazz.isAnnotationPresent(P2_Anno.class)); // false --> 不可被继承
        
        println("******获取指定注解******");
        A_Anno a = clazz.getAnnotation(A_Anno.class);
        println(a);                                       // null
        C_Anno c = clazz.getAnnotation(C_Anno.class);
        println(c);                                       // @com.hepengju.java05.new07_annotation.C_Anno(name="", workdayArray={SAT, SUN}, value=true, age=18)
        println(c.value());                               // true
        println(c.age());                                 // 18
        println(c.workdayArray());                        // [Lcom.hepengju.java05.new05_enum.WorkdayEnum;@5e25a92e
        
        println("******获取全部注解******");
        Annotation[] annos01 = clazz.getAnnotations();
        Annotation[] annos02 = clazz.getDeclaredAnnotations();
        println(Arrays.toString(annos01));                // P1_Anno, C_Anno, _Annotation$D_Anno
        println(Arrays.toString(annos02));                // C_Anno, _Annotation$D_Anno
    }
    
    private void println(Object obj) {
        System.out.println(obj);
    }
    
    @C_Anno(false)
    private String name;
    
    /**
     * 读取方法和成员上的注解
     */
    @C_Anno(age=20,workdayArray=WorkdayEnum.SAT)
    @Test public void testReadAnnotationOnFieldAndMethod() throws NoSuchFieldException, SecurityException, NoSuchMethodException { 
        Class<? extends _Annotation> clazz = this.getClass();
        
        println("******字段上的注解******");
        //Field field = clazz.getField("name"); // public 才可以获取
        Field field = clazz.getDeclaredField("name");
        println(field);
        println(field.getDeclaredAnnotation(C_Anno.class));
        
        println("******方法上的注解******");
        //Method method = clazz.getMethod("testReadAnnotationOnFieldAndMethod");
        Method method = clazz.getDeclaredMethod("testReadAnnotationOnFieldAndMethod");
        println(method);
        println(method.getDeclaredAnnotation(C_Anno.class));
    }
    
}
