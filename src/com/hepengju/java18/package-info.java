/**
 * Java18的新特性
 *
 * <pre>
 *      400: UTF-8 by Default
 *          指定 UTF-8 作为标准 Java API 的默认字符集。通过此更改，依赖于默认字符集的 API 将在所有实现、操作系统、区域设置和配置中保持一致。
 *      408: Simple Web Server
 *          引入一个简单的 Web 服务器。提供一个命令行工具，来启动一个只提供静态文件的最小网络服务器，它没有 CGI 或类似 servlet 的功能可用。该工具用于原型设计、临时编码和测试目的，尤其是在教学环境中。
 *      413: Code Snippets in Java API Documentation
 *          支持在 Java API 文档中加入代码片段。为 JavaDoc 的 Standard Doclet 引入一个 @snippet 标记，以简化 API 文档中嵌入示例源代码的难度。
 *      416: Reimplement Core Reflection with Method Handles
 *          用方法句柄重新实现核心反射。在 java.lang.invoke 的方法句柄之上，重构  java.lang.reflect 的方法、构造函数和字段，使用方法句柄处理反射的底层机制将减少 java.lang.reflect 和 java.lang.invoke 两者的 API 维护和开发成本。
 *      417: Vector API (Third Incubator)
 *          Vector API（第三孵化器）。引入一个 API 来表达向量计算，这些计算在运行时可以编译为支持的 CPU 架构上的最佳向量指令，从而实现优于等效标量计算的性能。
 *      418: Internet-Address Resolution SPI
 *          互联网地址解析 SPI。定义用于主机名和地址解析的服务提供者接口 (SPI)，以便java.net.InetAddress可以使用平台内置解析器以外的解析器。
 *      419: Foreign Function & Memory API (Second Incubator)
 *          外部函数和内存 API（第二孵化器）。引入了一个新 API, Java 程序可以通过它与 Java 运行时之外的代码和数据进行互操作。通过有效地调用外部函数(即 JVM 外的代码)，并安全地访问外部内存(即不由 JVM 管理的内存)，外部函数和内存 API 使 Java 程序能够调用本机库并处理本机数据，而不具有 JNI 的脆弱性和危险。
 *      420: Pattern Matching for switch (Second Preview)
 *          switch 模式匹配表达式。使用 switch 表达式和语句的模式匹配以及对模式语言的扩展来增强 Java 编程语言。将模式匹配扩展到 switch允许针对多个模式测试表达式，每个模式都有特定的操作，可以简洁安全地表达复杂的面向数据的查询。
 *      421: Deprecate Finalization for Removal
 *          弃用 Finalization 功能。Java 1.0 中引入的 Finalization 旨在帮助避免资源泄漏问题，然而这个功能存在延迟不可预测、行为不受约束，以及线程无法指定等缺陷，导致其安全性、性能、可靠性和可维护性方面都存在问题，因此将其弃用，用户可选择迁移到其他资源管理技术，例如try-with-resources 语句和清洁器。
 * </pre>
 *
 * @see <a href="http://openjdk.java.net/projects/jdk/18/">OpenJDK18</a>
 * @see <a href="https://www.oracle.com/java/technologies/javase/18-relnote-issues.html">OracleJDK18</a>
 *
 * @author hepengju
 */
package com.hepengju.java18;