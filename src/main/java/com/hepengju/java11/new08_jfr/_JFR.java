package com.hepengju.java11.new08_jfr;

import java.util.ArrayList;
import java.util.List;

import com.hepengju.java11.new07_epsilon.Garbage;

/**
 * JEP 328: Flight Recorder
 * 
 * <pre>
 *  具体概述：
 *           * Flight Recorder以前是商业版的特性，在java11当中开源出来
 *           * 它可以导出事件到文件中，之后可以用Java Mission Control来分析
 *  用法如下：
 *           * java -XX:StartFlightRecording
 *           *
 *  @see <a href="https://pan.baidu.com/s/16CamUE5Za5Vm8Wkt3KKmxQ 提取码：zpjt">         
 *  参考命令:
 *           * jfr print test.jfr
 *           * jfr print --events CPULoad,GarbageCollection test.jfr
 *           * jfr print --json --events "GC,JVM,Java" test.jfr
 *           * jfr summary test.jfr
 *           * jfr metadata test.jfr
 *           
 *  测试示例：
 *           * jcmd 3628 JFR.start (3628为java的pid)
 *           * jcmd 3628 JFR.dump filename=test.jfr name =1
 *           * jcmd 3628 JFR.stop (会生成test.jfr的文件)
 *        附：
 *           * 由于Java11没有提供jmc工具(java mission control)
 *           * 只能用Jdk12查看这个test.jfr二进制文件，输入jfr(开启改功能)  
 *           
 * </pre>
 * 
 * @author WGR
 * 
 */

public class _JFR {
    
    /**
     * 执行测试方法，测试记录文件
     */
    public void testJFR(){
        
        List<Garbage> list = new ArrayList<>();
        boolean flag = true;
        int count = 0;
        while (flag) {
            list.add(new Garbage());
            if (count++ == 20) {
                list.clear();
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }

}
