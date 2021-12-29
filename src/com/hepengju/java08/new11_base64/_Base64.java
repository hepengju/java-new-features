package com.hepengju.java08.new11_base64;

import java.util.Base64;

import org.junit.Test;

/**
 * Base64
 * 
 * <pre>
 *  Base64: Base64是一种基于64个可打印字符来表示二进制数据的表示方法(编码后的数据比原始数据略长,约为原来的4/3)
 *  
 *  为什么?
 *      我们知道在计算机中任何数据都是按ascii码存储的，而ascii码的128～255之间的值是不可见字符。
 *      而在网络上交换数据时，比如说从A地传到B地，往往要经过多个路由设备，
 *      由于不同的设备对字符的处理方式有一些不同，这样那些不可见字符就有可能被处理错误，这是不利于传输的。
 *      所以就先把数据先做一个Base64编码，统统变成可见字符，这样出错的可能性就大降低了。
 *      
 *  使用场景:
 *      * 证书: 特别是根证书,一般都是作Base64编码的, 因为它要在网上被很多人下载
 *      * 电子邮件附件: 一般也作Base64编码的, 因为一个附件数据往往具有不可见字符
 *      * 网页的小图片: 直接以Base64编码嵌入, 不用再链接请求消耗资源
 *      * 迅雷下载链接
 *      
 *  说明: 在Java8中Base64编码已经成为Java类库的标准,Base64工具类提供了一套静态方法获取下面三种BASE64编解码器:
 *      * Basic: A-Za-z0-9+/
 *      * URL: 由于标准的Basic编码可能会出现+和/, 在URL中就不能直接作为参数，所以又有一种“url safe” 的Base64编码，其实就是吧字符+和/分别变成-和_ 
 *      * MIME: 输出每行不超过76字符，并且使用'\r'并跟随'\n'作为分割。编码输出最后没有行分割。
 *          - MIME（Multipurpose Internet Mail Extensions）多用途互联网邮件扩展类型
 *          
 *  内部类:
 *      * Base64.Decoder: 该类实现一个解码器，使用 Base64 编码来解码字节数据。
 *      * Base64.Encoder: 该类实现一个编码器，使用 Base64 编码来编码字节数据。
 *      
 * </pre>
 * 
 * @see <a href="https://zh.wikipedia.org/wiki/Base64">维基百科Base64</a>
 * @see <a href="https://www.cnblogs.com/straybirds/p/8395041.html">为什么要使用base64编码，有哪些情景需求？</a>
 * @see <a href="http://www.runoob.com/java/java8-base64.html">Java8 Base64</a>
 * 
 * @author hepengju
 *
 */
public class _Base64 {

    /**
     * Java8的base64处理
     */
    @Test public void testInJava8() {
        String src = "ossadmin";
        System.out.println("原始: " + src);                             // 原始: ossadmin
        byte[] encode = Base64.getEncoder().encode(src.getBytes());
        String tar = new String(encode);
        System.out.println("编码: " + tar);                             // 编码: b3NzYWRtaW4=
        
        byte[] decode = Base64.getDecoder().decode(tar.getBytes());
        String res = new String(decode);
        System.out.println("解码: " + res);                             // 解码: ossadmin
    }
    
    /**
     * Java8前的base64处理
     * 
     * <pre>
     *  未公开内部类: BASE64Encoder/BASE64Decoder
     *      sun.misc包下的BASE64Encoder及BASE64Decoder的sun.misc.BASE64Encoder/BASE64Decoder类
     *      这个类是sun公司的内部方法，并没有在java api中公开过，不属于JDK标准库范畴，但在JDK中包含了该类，可以直接使用。
     *      但是在Eclipse和MyEclipse中直接使用,却找不到该类
     *  第三方工具: commons.codec下的Base64
     *  
     * </pre>
     * 
     * @see <a href="https://blog.csdn.net/u011514810/article/details/72725398">解决Eclipse中无法直接使用Base64Encoder的问题</a>
     */
    @Test public void testBeforeJava8() {
        // 1.未公开内部类
        // BASE64Encoder encoder = new BASE64Encoder(); // sun.misc.BASE64Encoder
        // System.out.println(encoder.encode("ossadmin".getBytes()));//amF2YQ==
        
        // 2.commons-codec
        /*
        String src = "ossadmin";
        System.out.println("原始: " + src);                             // 原始: ossadmin
        byte[] encode = org.apache.commons.codec.binary.Base64.encodeBase64(src.getBytes());
        String tar = new String(encode);
        System.out.println("编码: " + tar);                             // 编码: b3NzYWRtaW4=
        
        byte[] dncodeBase64 = org.apache.commons.codec.binary.Base64.decodeBase64(tar.getBytes());
        String res = new String(dncodeBase64);
        System.out.println("解码: " + res);                             // 解码: ossadmin
        */
    }
    

}
