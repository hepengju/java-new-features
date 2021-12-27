package com.hepengju.java08.new09_iostream;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * IO中加入的Stream流处理方法
 * 
 * <pre>
 *  字符串
 *      - Files.readAllBytes(Path)  +  new String(byte[], Charset)
 *      - Files.write(Path, byte[])
 *      - Files.readString(Path, Charset)
 *      - Files.writeString(Path, CharSequence, Charset, OpenOption...)
 *      
 *  所有行
 *      - List<String>   Files.readAllLines(Path, Charset)                            : 读取所有行
 *      - Stream<String> Files.lines(Path, Charset)                                   : 读取行流(延迟)
 *      - Path           Files.write(Path, Iterable<? extends CharSequence>, Charset) : 写入所有行
 *        
 *  缓存字符流
 *      - Files.newBufferedReader(Path, Charset)
 *      - Files.newBufferedWriter(Path, Charset, OpenOption...)
 *  
 *  目录
 *      - Stream<Path> Files.list(Path)                   : 列出当前目录的文件流
 *      - Stream<Path> Files.walk(Path)                   : 递归文件流
 *      - Stream<Path> Files.find(Path, int, BiPredicate) : 递归查找文件流
 *      
 * </pre>
 * 
 * @see <a href="https://www.jianshu.com/p/715659e4775f">看完这个，Java IO从此不在难</a>
 * @author hepengju
 *
 */
public class _IOStream {
    
    private Path pathFile = Paths.get("/Users/hepengju/HPJ/team/GIT/java-new-features/src/main/java/com/hepengju/java08/new09_iostream/_IOStream.java");
    private Path pathDir = Paths.get("/Users/hepengju/HPJ/team/GIT/java-new-features/src/main/java/com/hepengju");
    private Path outpath = Paths.get("/Users/hepengju/HPJ/team/GIT/java-new-features/src/main/java/com/hepengju/java08/new09_iostream/tmp.txt");

    /**
     * 字符串
     */
    @Test public void testString() throws IOException {
        String str1 = new String(Files.readAllBytes(pathFile),Charset.forName("UTF-8"));
        String str2 = Files.readString(pathFile);
        assertEquals(str1, str2);
        
        Files.write(outpath, str1.getBytes(Charset.forName("GBK")));
        Files.writeString(outpath, str2, Charset.forName("GBK"));
    }
    
    /**
     * 所有行
     */
    @Test public void testLines() throws IOException {
        List<String> lines = Files.readAllLines(pathFile, StandardCharsets.UTF_8);
        assertEquals("package com.hepengju.java08.new09_iostream;", lines.get(0));
        
        Files.lines(pathFile, StandardCharsets.UTF_8)
             .skip(2)
             .limit(1)
             .findFirst()
             .ifPresent(System.out::println); // import static org.junit.Assert.assertEquals;
        
        List<String> newlines = new ArrayList<>();
        newlines.add(lines.get(0));
        newlines.add(lines.get(1));
        newlines.add(lines.get(2));
        Files.write(outpath, newlines, Charset.forName("GBK"));
    }
    
    /**
     * 缓存字符流
     */
    @Test public void testBufferReaderAndWriter() throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(pathFile, Charset.forName("UTF-8"));) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
                System.out.println(line);
            }
        }
        
        try(BufferedWriter writer = Files.newBufferedWriter(outpath, Charset.forName("GBK"));){
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
    
    /**
     * 目录
     */
    @Test public void testDirectory() throws IOException {
        Files.list(pathDir).forEach(System.out::println);
        System.out.println("**********");
        Files.walk(pathDir).forEach(System.out::println);
        System.out.println("**********");
        Files.find(pathDir, Integer.MAX_VALUE, (p,bfa) -> p.toFile().isFile())
             .forEach(System.out::println); 
    }
    
    /**
     * 测试表达式的值
     */
    @Test public void testExpressionValue() {
        String line = null;
        System.out.println(line == "haha"); // false
        System.out.println(line = "haha");  // haha
    }
}
