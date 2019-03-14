package com.hepengju.java07.new07_twr;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

/**
 * 
 * TWR(try-with-resource)语句
 * 
 * @author hepengju
 *
 */
public class _TWR {
    
    // JDBC的四大连接参数
    // private String className = "com.mysql.jdbc.Driver";
    private String url       = "jdbc:mysql://192.168.180.100:3306/oss6?useSSL=false";
    private String username  = "root";
    private String password  = "root";
    
    /**
     * 放在try的小括号里面可以自动关闭, 并且更加安全
     */
    @Test public void testTWR01() {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement("select * from z010_role");
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
     * catch 可以捕获多个异常
     */
    @Test public void testTWR02() {
        Class<?> clazz = this.getClass();
        try {
            Method method = clazz.getDeclaredMethod("show");
            Object obj = method.invoke(this);
            System.out.println(obj);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    
    public void show() {
        System.out.println("I'm show method");
    }
}
