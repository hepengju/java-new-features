# java-new-features
Java新特性 5, 6, 7, 8, 9, 10, 11, 12, 13, 14

## 项目目标
1. 列举Java5-14版本的关键新特性
2. 对每个新特性进行全面的阐述与代码示例
3. 在doc文件夹中生成xmind,ppt,pdf,javadoc等离线参考文档

## 新特性目标(为什么需要不断升级)
- 速度更快
- 代码更少
- 更加安全

![图片展示](./src/main/resources/Java%E6%96%B0%E7%89%B9%E6%80%A7.png)

## 官网新特性
| Oracle官网                                                   | OpenJDK官网                                       |
| ------------------------------------------------------------ | ------------------------------------------------- |
| [Java05新特性](https://docs.oracle.com/javase/1.5.0/docs/relnotes/features.html) |                                                   |
| [Java06新特性](https://www.oracle.com/technetwork/java/javase/features-141434.html) | [jdk6](http://openjdk.java.net/projects/jdk6/)    |
| [Java07新特性](https://www.oracle.com/technetwork/java/javase/jdk7-relnotes-418459.html) | [jdk7](http://openjdk.java.net/projects/jdk7/)    |
| [Java08新特性](https://www.oracle.com/technetwork/java/javase/8-whats-new-2157071.html) | [jdk8](http://openjdk.java.net/projects/jdk8/)    |
| [Java09新特性](https://docs.oracle.com/javase/9/whatsnew/toc.htm#JSNEW-GUID-C23AFD78-C777-460B-8ACE-58BE5EA681F6) | [jdk9](http://openjdk.java.net/projects/jdk9/)    |
| [Java10新特性](https://www.oracle.com/technetwork/java/javase/10-relnote-issues-4108729.html#NewFeature) | [Jdk10](http://openjdk.java.net/projects/jdk/10/) |
| [Java11新特性](https://www.oracle.com/technetwork/java/javase/11-relnote-issues-5012449.html#NewFeature) | [Jdk11](http://openjdk.java.net/projects/jdk/11/) |
| [Java12新特性](https://www.oracle.com/technetwork/java/javase/12-relnote-issues-5211422.html#NewFeature) | [Jdk12](http://openjdk.java.net/projects/jdk/12/) |
| [Java13新特性](https://www.oracle.com/technetwork/java/javase/13all-relnotes-5461743.html) | [Jdk13](http://openjdk.java.net/projects/jdk/13/) |
| [Java14新特性](https://www.oracle.com/technetwork/java/javase/14all-relnotes-5809668.html#NewFeature) | [Jdk14](http://openjdk.java.net/projects/jdk/14/) |


## 视频教程
| 视频说明                | 百度网盘链接                                    | 密码 |
| ----------------------- | ----------------------------------------------- | ---- |
| Java8新特性(尚硅谷)     | https://pan.baidu.com/s/1tQEdb5RGH17CLe-Bj6plfQ | eylh |
| Java8新特性高级         | https://pan.baidu.com/s/1U4WGdxGkq1D1w_wHplMKvA | 1yb0 |
| Java9新特性(尚硅谷)     | https://pan.baidu.com/s/1RNV8A5pFihG1Wt5tgBZzfw | w0r3 |
| Java10新特性            | https://pan.baidu.com/s/1Ol0bfXSNNxGzqRBJuaGI4A | qcfh |
| Java11新特性(尚硅谷)    | https://pan.baidu.com/s/1w2DB_UnpQc5K3hdLoduzJA | 1hqm |
| Java12/13新特性(尚硅谷) | https://pan.baidu.com/s/1k8Y3RgQxk_RVmsk81AZkDw | tkfo |

# JCP/JEP/JSR

- JCP: [Java Community Process](https://www.jcp.org/en/home/index)
  * Java社区进程
  * JCP成立于1998年, 由社会各界Java组成的社区, 规划和领导Java的发展 
- JEP: [JDK Enhancement Proposals](http://openjdk.java.net/jeps/0) 
  * jdk改进提案, 非正式的规范
  * OpenJDK社区搜集增强jdk提案的过程
- JSR: [Java Specification Requests](https://www.jcp.org/en/jsr/platform)
  * java规范请求, 标准化技术规范的正式请求
  * 由JCP成员向委员会提交的Java发展提案, 经过一系列流程后, 如果通过最终会体现在未来的Java中

## 参与规范

1. 注释: 按照javadoc规范写, 以便后续生成javadoc文档
1. package-info.java
    * 记录需要整理的新特性
1. 包名: newXX_englishFeatureName
1. 入口: _EnglishFeatureName
1. 参考: 如果有参考网页和文档,请注明
    
    * 比如: @see <a href="https://www.cnblogs.com/qcblog/p/7670159.html">关于java的自动拆装箱若干细节问题</a>
1. 测试: 请使用Junit4的@Test

## 合作者(加入的先后顺序)
- [hepengju](https://github.com/hepengju)
- [wangguorong](https://github.com/dalianpai)
- [chennan](https://github.com/949035443)
