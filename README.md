![图片展示](./resources/Java%E6%96%B0%E7%89%B9%E6%80%A7.png)

# java-new-features
Java新特性 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17

## 项目目标
1. 列举Java5-17版本的关键新特性
2. 对每个新特性进行全面的阐述与代码示例

## 新特性目标(为什么需要不断升级)
- 速度更快
- 代码更少
- 更加安全

## 官网新特性
- [OracleJDK](https://www.oracle.com/java/technologies/javase/jdk-relnotes-index.html)
- [OpenJDK ](http://openjdk.java.net/projects/jdk/)

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
