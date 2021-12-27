package com.hepengju.java08.new14_parameterName;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.junit.Test;

/**
 * 参数名字
 * 
 * <pre>
 *  说明: Java8可以通过设置使得编译后的class文件中保留参数名字,并通过反射获取
 *  设置:
 *      * javac -parameters
 *      * Eclipse: 
 *          - 全局设置: Preferences --> Java --> Compiler --> 勾选 Store information about method parameters(usable via reflection)
 *          - 工程设置: 工程右键 --> Properties --> Java Compiler --> 勾选 Store information about method parameters(usable via reflection)
 *      * maven: 
 *          <plugin>
 *              <groupId>org.apache.maven.plugins</groupId>
 *              <artifactId>maven-compiler-plugin</artifactId>
 *              <version>3.3</version>
 *              <configuration>
 *                  <compilerArgs>
 *                      <arg>-parameters</arg>
 *                  </compilerArgs>
 *              </configuration>
 *          </plugin>
 *          
 *  疑问: TODO 
 *       SpringMVC中的Controller中不加 @RequestParam 且不开启编译保留参数时,它也可以通过反射获取到,其原理是什么? 
 *       MyBatis就必须开启编译保留参数才可以省略Mapper接口方法参数上的 @Param
 * </pre>
 * 
 * @author hepengju
 *
 */
public class _ParameterName {

    /**
     * 反射获取参数名字
     */
    @Test public void testGetParameterName() throws NoSuchMethodException, SecurityException {
        Method method = this.getClass().getMethod("show", String.class, int.class);
        Parameter[] parameters = method.getParameters();
        for (Parameter para : parameters) {
            System.out.println(para.getName()); 
            // 未开: arg0 arg1
            // 开启: sql  num
        }
    }
    
    
    
    public void show(String sql,int num) {
        System.out.println(sql + ":" + num);
    }
}
