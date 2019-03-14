package com.hepengju.java05.new10_processBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

/**
 * 进程创建器
 * 
 * <pre>
 *  执行系统命令的两种方式: 
 *      * Runtime.getRuntime().exec(command)
 *      * new ProcessBuilder(cmdArray).start()
 *  相同: Runtime.getRuntime().exec(command)底层调用的new ProcessBuilder(cmdArray).start()
 *  不同: Runtime.getRuntime.exec()可以把命令和参数写在一个String中，用空格分开
 *       ProcessBuilder则是构造函数的参数中，传递一个由命令和参数组成的list或数组
 * </pre>
 * 
 * @see <a href="https://blog.csdn.net/seapeak007/article/details/69668600">调用Process.waitfor导致的进程挂起</a>
 * @see <a href="https://blog.csdn.net/c315838651/article/details/72085739">Java进程Runtime、Process、ProcessBuilder调用外部程序</a>
 * @see <a href="https://blog.csdn.net/timo1160139211/article/details/75006938">调用java.lang.Runtime.exec的正确姿势</a>
 * @see <a href="https://blog.csdn.net/timo1160139211/article/details/75006938">Java Runtime.exec()的使用</a>
 * 
 * @author hepengju
 *
 */
public class _ProcessBuilder {

    Runtime runtime = Runtime.getRuntime();  // 单例模式
    
    /**
     * 测试 Runtime 基础功能
     */
    @Test public void testRuntimeBase() {
        // 虚拟机关闭钩子
        Thread hook = new Thread(() -> {System.out.println("******虚拟机要退出啦******");});
        runtime.addShutdownHook(hook);
        
        // 处理器,内存信息
        println("JVM可利用处理器个数: " + runtime.availableProcessors());
        println("JVM最终可使用的最大内存: " + runtime.maxMemory()  / 1024 / 1024 + "M");
        println("JVM当前的总内存: " + runtime.totalMemory()  / 1024 / 1024 + "M");
        println("JVM当前的空闲内存: " + runtime.freeMemory() / 1024 / 1024 + "M");
        println("JRE Version:" + Runtime.version());
        
        // 退出进程
        // runtime.exit(0); // java.lang.System.exit(int)调用的也是这个方法
    }
    
    /**
     * 测试 Runtime 执行系统命令
     */
    @Test public void testRuntimeExec() throws IOException, InterruptedException {
        // 字符串直接传
        String command;
        command = "pwd";             // ok /Users/hepengju/HPJ/team/GIT/java-new-features
        command = "ls -l";           // ok
        command = "java -version";   // ok
        
        // 环境变量
        //println(System.getenv("PATH")); // /usr/bin:/bin:/usr/sbin:/sbin
        //System.getenv().forEach((k,v) -> println(k + ": " + v));
        
        //command = "/Users/hepengju/HPJ/java/apache-maven-3.5.4/bin/mvn -v"; // mvn -v 需要写绝对路径
        //Process process = runtime.exec(command);
        
        // 写相对路径,需要指定运行目录
        command = "./mvn -v";
        Process process = runtime.exec(command,null,new File("/Users/hepengju/HPJ/java/apache-maven-3.5.4/bin"));
        printlnProcess(process);
        
        //command = "node -v";
        //command = "npm -v";
    }

    @Test public void testProcessBuilder() throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("./mvn","-v");
       
        //切换环境变量没有成功, 查找原因: TODO
        //Map<String, String> env = pb.environment();
        //env.put("PATH", env.get("PATH") + ":/Users/hepengju/HPJ/java/apache-maven-3.5.4/bin");
        //env.forEach((k,v) -> println(k + ": " + v));
        
        pb.directory(new File("/Users/hepengju/HPJ/java/apache-maven-3.5.4/bin"));
        Process process = pb.start();
        printlnProcess(process);
    }
    
    private void printlnProcess(Process process) throws InterruptedException {
        //开启新线程处理正常输出
        new Thread(() ->  {
            try(InputStream inputStream = process.getInputStream();
            BufferedReader bufr = new BufferedReader(new InputStreamReader(inputStream));){
            String out = null;
                while ((out = bufr.readLine()) != null) {
                    println(out);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        
        //开启新线程处理错误输出
        new Thread(() ->  {
            try(InputStream inputStream = process.getErrorStream();
                    BufferedReader bufr = new BufferedReader(new InputStreamReader(inputStream));){
                String out = null;
                while ((out = bufr.readLine()) != null) {
                    println(out);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        
        process.waitFor();
        
        // 不会阻塞进程,但是调用时如果没有完成会报错
        if (process.exitValue() != 0) {
            println("exec os command failure");
        } else {
            println("exec os command success");
        }
    }
    
    private void println(Object obj) {
        System.out.println(obj);
    }
}
