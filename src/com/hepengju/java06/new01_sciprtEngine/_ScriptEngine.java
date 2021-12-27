package com.hepengju.java06.new01_sciprtEngine;

import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;

/**
 * 脚本引擎
 * 
 * <pre>
 *  说明: 通过一套固定的接口与各种脚本语言交互，从而达到在java平台上调用各种脚本语言的目的
 *  使用: 
 *      * 命令行: jjs
 *      * 代码中: new脚本引擎管理器, 通过名字获得脚本引擎, 执行eval
 *  场景: 
 *      * 评估数学公式
 *      * 评估表达式 (比如反欺诈项目中定义的规则是一个表达式,需要评估出结果)
 * </pre>
 * 
 * @see <a href="https://docs.oracle.com/javase/6/docs/technotes/guides/scripting/programmer_guide/index.html">Java Scripting Programmer's Guide</a>
 * 
 * @see <a href="https://blog.csdn.net/ifollowyou/article/details/83125799">Java SE 6之脚本引擎 让程序如虎添翼</a>
 * @see <a href="https://www.w3cschool.cn/java/scripting-in-java-engine.html">Java 脚本引擎</a>
 * @see <a href="https://www.cnblogs.com/top8/p/6207945.html">Java 8的 Nashorn 脚本引擎教程</a>
 * @see <a href="https://www.jianshu.com/p/4be2644d3e65">java脚本引擎的设计原理浅析</a>
 * 
 * @author hepengju
 */
public class _ScriptEngine {

    /**
     * 脚本引擎工厂
     */
    @Test public void testScriptEngineManager() {
        ScriptEngineManager sem = new ScriptEngineManager();
        List<ScriptEngineFactory> sefList = sem.getEngineFactories();
        for (ScriptEngineFactory factory : sefList) {
            // 打印脚本信息
            System.out.printf(
                      "Name: %s%n"
                    + "Version: %s%n"
                    + "Language name: %s%n"
                    + "Language version: %s%n"
                    + "Extensions: %s%n"
                    + "Mime types: %s%n"
                    + "Names: %s%n"
                    , factory.getEngineName()        // 引擎名字: Name: Oracle Nashorn  --> 注意: JDK1.6的默认js引擎是: Mozilla Rhino, Nashorn引擎是1.8引入的
                    , factory.getEngineVersion()     // 引擎版本: 11.0.2
                    , factory.getLanguageName()      // 语言名字: ECMAScript
                    , factory.getLanguageVersion()   // 语言版本: ECMA - 262 Edition 5.1
                    , factory.getExtensions()        // 扩展名:   [js]
                    , factory.getMimeTypes()         // MIME类型: [application/javascript, application/ecmascript, text/javascript, text/ecmascript]
                    , factory.getNames());           // 名字:     [nashorn, Nashorn, js, JS, JavaScript, javascript, ECMAScript, ecmascript]
            System.out.println(factory);
        }
    }
    
    /**
     * 数学加减乘除
     */
    @Test public void testJSMath() throws ScriptException {
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine jsEngine = sem.getEngineByName("js");
        Object eval1 = jsEngine.eval("1 + 2");
        Object eval2 = jsEngine.eval("1 - 2");
        Object eval3 = jsEngine.eval("1 * 2");
        Object eval4 = jsEngine.eval("1 / 2");
        Object eval5 = jsEngine.eval("10 + 2*3 - 4*5 + 6/3"); // 10+6-20+2 = -2
        System.out.println(eval1); // 3
        System.out.println(eval2); // -1
        System.out.println(eval3); // 2
        System.out.println(eval4); // 0.5
        System.out.println(eval5); // -2
    }
    
    /**
     * 简单表达式
     */
    @Test public void testJSExpression() throws ScriptException {
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine jsEngine = sem.getEngineByName("js");
        Object eval1 = jsEngine.eval("5 > 1");
        Object eval2 = jsEngine.eval("'hepengju'.length > 'hpj'.length");
        Object eval3 = jsEngine.eval("/PENG/i.test('hepengju')");
        System.out.println(eval1); // true
        System.out.println(eval2); // true
        System.out.println(eval3); // true
    }
    
    /**
     * 脚本上下文
     */
    @Test public void testJSContext() {
        // TODO
    }
    
    /**
     * 调用js函数
     * 
     * <br> 需要把脚本引擎转换为Invovable 
     */
    @Test public void testJavaCallJSFunction() {
       // TODO
    }
    
    /**
     * JS调用java代码
     */
    @Test public void testJSCallJava() {
        // TODO
    }
    
    /**
     * Python引擎
     * 
     * @see <a href="https://www.jython.org/">Jython官网</a>
     */
    @Test public void testPythonEngine() {
        
    }
    
}
