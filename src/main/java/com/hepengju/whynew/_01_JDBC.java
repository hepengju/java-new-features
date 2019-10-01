package com.hepengju.whynew;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

/**
 * JDBC写法来演示Java新特性的改进
 *
 * <pre>
 *     1. 功能演示
 *     2. 原始写法: 先定义流变量, try中赋值, finally关闭
 *     3. jdk6引入jdbc4.0后: Class.forName可以省略
 *     4. jdk7引入TWR语句: 自动关闭流
 *     5. jdk9升级TWR语句: 自动关闭流的变量定义可以写在外面
 *     6. jdk10引入var关键字: 变量的声明更加简单
 * </pre>
 * 
 * @author hepengju
 *
 */
public class _01_JDBC {

    // JDBC的四大连接参数
    private String className = "com.mysql.jdbc.Driver";
    private String jdbcUrl   = "jdbc:mysql://mysql57.hepengju.cn:3306/java-new-features?useSSL=false";
    private String username  = "java123";
    private String password  = "java123";

    // 测试的查询语句
    private String TEST_SQL = "SELECT * FROM person LIMIT 5";

    /**
     * 简单测试
     * 
     * <pre>
     *  1. 加载驱动类
     *  2. 获取连接
     *  3. 准备语句
     *  4. 得到结果
     * </pre>
     */
    @Test public void testJDBC_01() throws ClassNotFoundException, SQLException {
        Class.forName(className);
        Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
        PreparedStatement pstmt = conn.prepareStatement(TEST_SQL);
        ResultSet rst = pstmt.executeQuery();
        int columnCount = rst.getMetaData().getColumnCount();
        while (rst.next()) {
            for (int i = 1; i <= columnCount; i++) {
                Object obj = rst.getObject(i);
                System.out.print(obj + "\t");
            }
            System.out.println();
        }
    }
    
    /**
     * 原始的标准写法: 需要注意关闭流与处理异常 
     */
    @Test public void testJDBC_02() {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        
        try {
            conn = DriverManager.getConnection(jdbcUrl, username, password);
            pstmt = conn.prepareStatement(TEST_SQL);
            rst = pstmt.executeQuery();
            int columnCount = rst.getMetaData().getColumnCount();
            while (rst.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    Object obj = rst.getObject(i);
                    System.out.print(obj + "\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rst != null) {
                try {
                    rst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Java06 JDBC驱动类的自动加载
     * 
     * <pre>
     * 从JDK1.6开始，Oracle就将修改了添加了新的加载JDBC驱动的方式。即JDBC4.0。
     * 在启动项目或是服务时，会判断当前classspath中的所有jar包，并检查META-INF目录下，
     * 是否包含services文件夹，如果包含，就会将里面的配置加载成相应的服务。
     * </pre>
     */
    @Test public void testJDBC_03() {
        // 可以省略 Class.forName(className); 
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        
        try {
            conn = DriverManager.getConnection(jdbcUrl, username, password);
            pstmt = conn.prepareStatement(TEST_SQL);
            rst = pstmt.executeQuery();
            int columnCount = rst.getMetaData().getColumnCount();
            while (rst.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    Object obj = rst.getObject(i);
                    System.out.print(obj + "\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rst != null) {
                try {
                    rst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Java7的TWR语句改进
     * 
     * <br> 实现AutoCloseable接口的可以放在try后面的小括号里面自动关闭
     */
    @Test public void testJDBC_04() {
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement pstmt = conn.prepareStatement(TEST_SQL);
             ResultSet rst = pstmt.executeQuery()) {
            int columnCount = rst.getMetaData().getColumnCount();
            while (rst.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    Object obj = rst.getObject(i);
                    System.out.print(obj + "\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
    
    /**
     * Java9的TWR语句再次改进
     * 
     * <br> 适用场景: 已经有资源了, 需要在内部关闭
     */
    @Test public void testJDBC_05(){
        // 对于JDBC中的这种写法不安全,在某些情况下有用
        try {
            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
            PreparedStatement pstmt = conn.prepareStatement(TEST_SQL);
            ResultSet rst = pstmt.executeQuery();
            try (conn; pstmt; rst) {
                int columnCount = rst.getMetaData().getColumnCount();
                while (rst.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        Object obj = rst.getObject(i);
                        System.out.print(obj + "\t");
                    }
                    System.out.println();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Java10中的var变量
     */
    @Test public void testJDBC_06() {
        try (var conn  = DriverManager.getConnection(jdbcUrl, username, password);
             var pstmt = conn.prepareStatement(TEST_SQL);
             var rst   = pstmt.executeQuery()) {
            int columnCount = rst.getMetaData().getColumnCount();
            while (rst.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    Object obj = rst.getObject(i);
                    System.out.print(obj + "\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
