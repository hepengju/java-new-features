package com.hepengju.java12.new04_other;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * 其他改进
 *
 * <pre>
 *  String新增方法
 *      - transform: 进行映射转换
 *      - indent: 调整String实例的缩进
 *  Files新增mismatch方法
 *  Collectors新增teeing方法: 聚合两个downstream的结果
 * </pre>
 */
public class _Other {

    /**
     * 字符串转换(映射)
     */
    @Test
    public void testStringTransform(){
        var result = "hello".transform(s -> s + " world")
                            .transform(String::toUpperCase);
        Assert.assertEquals("HELLO WORLD", result);
    }

    /**
     * 字符串缩进
     */
    @Test
    public void testStringIndent(){
        // 正数增加空格, 负责删除空白字符
        var result = "  JAVA\n  Python\nC==";
        System.out.println(result);
        System.out.println(result.indent(4));
        System.out.println(result.indent(-4));
    }

    /**
     * 比较文件内容第一个不同字节的位置
     */
    @Test
    public void testFilesMisMatch() throws IOException {
        FileWriter fw01 = new FileWriter("a.txt");
        FileWriter fw02 = new FileWriter("b.txt");
        try(fw01; fw02){
            fw01.write("a");
            fw01.write("b");
            fw01.write("c");
            fw02.write("a");
            fw02.write("1");
            fw02.write("c");
        }

        long mismatch = Files.mismatch(Path.of("a.txt"), Path.of("b.txt"));
        System.out.println(mismatch);

        Files.delete(Path.of("a.txt"));
        Files.delete(Path.of("b.txt"));
    }

    /**
     * teeing: 三通(管道配件), 形象的表示将两个流汇聚到一个
     */
    @Test
    public void testCollectorsTeeing(){
        var result = Stream.of(5, 10, 15, 20).collect(
                teeing(counting(), summingInt(n -> n), NumResult::new)
        );
        System.out.println(result); // _Other.NumResult(count=4, sum=50)
    }

    @Data
    @AllArgsConstructor
    class NumResult{
        private Long    count; // 个数
        private Integer sum;   // 总数
    }
}
