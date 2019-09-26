# java-new-features
Java新特性 5，6，7，8，9，10，11，12

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

## 新特性目标(为什么需要不断升级)
- 速度更快
- 代码更少
- 更加安全

## 项目目标
1. 列举Java5-13版本的关键新特性
2. 对每个新特性进行全面的阐述与代码示例
3. 在doc文件夹中生成xmind,ppt,pdf,javadoc等离线参考文档

## Oracle官网新特性
- [Java05新特性](https://docs.oracle.com/javase/1.5.0/docs/relnotes/features.html)
- [Java06新特性](https://www.oracle.com/technetwork/java/javase/features-141434.html)
- [Java07新特性](https://www.oracle.com/technetwork/java/javase/jdk7-relnotes-418459.html)
- [Java08新特性](https://www.oracle.com/technetwork/java/javase/8-whats-new-2157071.html)
- [Java09新特性](https://docs.oracle.com/javase/9/whatsnew/toc.htm#JSNEW-GUID-C23AFD78-C777-460B-8ACE-58BE5EA681F6)
- [Java10新特性](https://www.oracle.com/technetwork/java/javase/10-relnote-issues-4108729.html#NewFeature)
- [Java11新特性](https://www.oracle.com/technetwork/java/javase/11-relnote-issues-5012449.html#NewFeature)
- [Java12新特性](https://www.oracle.com/technetwork/java/javase/12-relnote-issues-5211422.html#NewFeature)
- [Java13新特性](https://www.oracle.com/technetwork/java/javase/13all-relnotes-5461743.html)

![图片展示](https://github.com/hepengju/java-new-features/blob/master/src/main/resources/Java%E6%96%B0%E7%89%B9%E6%80%A7.png)

## 视频教程整理
- Java8新特性(尚硅谷): 链接:https://pan.baidu.com/s/1tQEdb5RGH17CLe-Bj6plfQ  密码:eylh
- Java8新特性高级: 链接:https://pan.baidu.com/s/1U4WGdxGkq1D1w_wHplMKvA  密码:1yb0
- Java 9新特性(尚硅谷): 链接:https://pan.baidu.com/s/1RNV8A5pFihG1Wt5tgBZzfw  密码:w0r3
- Java10新特性: 链接:https://pan.baidu.com/s/1Ol0bfXSNNxGzqRBJuaGI4A  密码:qcfh
- Java11新特性(尚硅谷): 链接:https://pan.baidu.com/s/1w2DB_UnpQc5K3hdLoduzJA  密码:1hqm 
- Java12/13新特性(尚硅谷): 链接:https://pan.baidu.com/s/1k8Y3RgQxk_RVmsk81AZkDw  密码:tkfo

## 参与规范
1. 注释: 按照javadoc规范写, 以便后续生成javadoc文档
1. package-info.java
    * 记录需要整理的新特性
1. 包名: newXX_englishFeatureName
1. 入口: _EnglishFeatureName
1. 参考: 如果有参考网页和文档,请注明
    * 比如: @see <a href="https://www.cnblogs.com/qcblog/p/7670159.html">关于java的自动拆装箱若干细节问题</a>
1. 测试: 请使用Junit4的@Test
1. 其他:
    * 建议缩进转空格 Tab --> spaces 
    ```
    Preferences --> General --> Text Editors --> 勾选 Insert spaces for tabs
    Preferences --> Java --> Code Styles --> Formatter --> Edit... --> Indentation --> 下拉框选择 Spaces only
    ```
 
## 合作者(加入的先后顺序)
- [hepengju](https://github.com/hepengju)
- [wangguorong](https://github.com/dalianpai)
- [chennan](https://github.com/949035443)
