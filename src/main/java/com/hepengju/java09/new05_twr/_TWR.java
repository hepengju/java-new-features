package com.hepengju.java09.new05_twr;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.junit.Test;

/**
 * TWR(try-with-resource)语句
 * 
 * <pre>
 *  如果一个资源被final或者等效于final变量引用，则在不需要声明一个新的变量的情况下，
 *  try-with-resources就可以管理这个资源。
 * </pre>
 * 
 * @author WGR
 *
 */
public class _TWR {
    
    /**
     * java9 中：可以在try()中调用已经实例化的资源对象
     * 直接在try括号中直接写入 变量就好，如果有多个流，就用分号隔开
     */
    @Test
    public void testTWR3(){
        InputStreamReader reader = new InputStreamReader(System.in);
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        try(reader;writer){
            //此时的reader是final的，不可再被赋值
            //reader = null;
            reader.read();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    

    /**
     * java8中：要求资源对象的实例化，必须放在try的一对()内完成。
     */
    @Test
    public void testTWR2(){
        try(InputStreamReader reader = new InputStreamReader(System.in)){
            reader.read();
        }catch(IOException e){
            e.printStackTrace();
        }  //不用显式的处理资源的关闭
    }
    
    /**
     * java8之前，习惯于这样处理资源的关闭：
     */
    @Test
    public void testTWR1(){

        InputStreamReader reader = null;

        try{
            reader  = new InputStreamReader(System.in);
            reader.read();
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            //资源的关闭操作
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
