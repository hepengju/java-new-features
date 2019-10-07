package com.hepengju.java13.new03_ReImplSocket;

import java.net.SocketImpl;

/**
 * 重新实现旧版套接字API
 *
 * <pre>
 * 现有问题
 *  重新实现了古老的 Socket 接口。现在已有的 java.net.Socket 和 java.net.ServerSocket 以及它们的实现类，都可以回溯到 JDK 1.0 时代了。
 *  - 它们的实现是混合了 Java 和 C 的代码的，维护和调试都很痛苦。
 *  - 实现类还使用了线程栈作为 I/O 的缓冲，导致在某些情况下还需要增加线程栈的大小。
 *  - 支持异步关闭，此操作是通过使用一个本地的数据结构来实现的，这种方式这些年也带来了潜在的不稳定性和
 *  - 跨平台移植问题。该实现还存在几个并发问题，需要彻底解决。
 *  在未来的网络世界，要快速响应，不能阻塞本地方法线程，当前的实现不适合使用了。
 *
 * 新的实现类
 *  全新实现的 NioSocketImpl 来替换JDK1.0的PlainSocketImpl。
 *  - 它便于维护和调试，与 NewI/O (NIO) 使用相同的 JDK 内部结构，因此不需要使用系统本地代码。
 *  - 它与现有的缓冲区缓存机制集成在一起，这样就不需要为 I/O 使用线程栈。
 *  - 它使用 java.util.concurrent 锁，而不是 synchronized 同步方法，增强了并发能力。
 *  - 新的实现是Java 13中的默认实现，但是旧的实现还没有删除，可以通过设置系统属性jdk.net.usePlainSocketImpl来切换到旧版本。
 *
 * @see java.net.SocketImpl
 * </pre>
 *
 * @author hepengju
 */
public class _ReImplSocket { }
