package com.hepengju.java07.new08_nio;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

/**
 * NIO(new IO)
 * 
 * <pre>
 *  说明: 
 *  主要:
 *      * bytebuffer
 *      * filechannel
 *      
 *      * Path  取代File
 *      * Paths 工具类,获取Path
 *      * Files 工具类的实用方法
 * </pre>
 * @author hepengju
 *
 */
public class _NIO {

    /**
     * 把文件读取为字符串
     */
    @Test public void testFiles() throws IOException {
        Path path = Paths.get("/Users/hepengju/HPJ/work/01_调度工具/调度工具-部署-何鹏举/readme.md");
        String content = new String(Files.readAllBytes(path),UTF_8);
        System.out.println(content);
        
        // ## 文档说明
        // - 日期: 20180211
        // - 作者: 何鹏举
        // - 邮箱: he.pj@topcheer.com
        // - 简述: 对Perl版本的调度工具进行说明,并对代码予以分析与追加注释
        // ...

    }
    
    /**
     * 把文件读取每行进行处理
     */
    @Test public void testFilesLines() throws IOException {
        Path path = Paths.get("/Users/hepengju/HPJ/work/01_调度工具/调度工具-部署-何鹏举/readme.md");
        
        // 一次性读取完
        List<String> allLines = Files.readAllLines(path);
        allLines.forEach(System.out::println);
        
        System.out.println("--------------------------");
        
        // 流处理
        Files.lines(path).forEach(System.out::println);
        
        // ## 文档说明
        // - 日期: 20180211
        // - 作者: 何鹏举
        // - 邮箱: he.pj@topcheer.com
        // - 简述: 对Perl版本的调度工具进行说明,并对代码予以分析与追加注释
        // ...
        
    }
    
    
    
    /**
     * 遍历文件夹(java8新增)
     * 
     * @see java.nio.file.Files.walk(Path, FileVisitOption...)
     */
    @Test public void testFilesWalk() throws URISyntaxException, IOException {
        Path path = Paths.get("/Users/hepengju/HPJ");
        List<File> allNeedFile = Files.walk(path)
             .map(Path::toFile)
             .filter(f -> f.getName().endsWith(".md"))
             .filter(f -> f.getName().toLowerCase().equals(f.getName()))
             .filter(f -> f.length() > 0)
             .collect(toList());
        
        allNeedFile.forEach(System.out::println);
        
        // /Users/hepengju/HPJ/team/GIT/ExcelVBAUtils/01_数据库设计工具/帮助手册/数据库设计工具.md
        // /Users/hepengju/HPJ/work/09_Jar包修改/mybatis/readme.md
        // /Users/hepengju/HPJ/work/09_Jar包修改/mybatisPlus/readme.md
        // /Users/hepengju/HPJ/work/01_调度工具/调度工具-部署-何鹏举/readme.md
        // /Users/hepengju/HPJ/work/01_调度工具/调度工具-部署-何鹏举/TBS/doc/readme.md
        // ...
    }
}
