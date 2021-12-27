package com.hepengju.java13.new02_textblock;

import org.junit.Assert;
import org.junit.Test;

/**
 * 文本块(预览)
 *
 * <pre>
 *     在JDK 12中引入了Raw String Literals特性，但在发布之前就放弃了。这个JEP与引入多行字符串文字（text block）
 * 在意义上是类似的。这条新特性跟 Kotlin 里的文本块是类似的。
 *
 * 现实问题
 *     在Java中，通常需要使用String类型表达HTML，XML，SQL或JSON等格式的字符串，在进行字符串赋值时需要进行
 * 转义和连接操作，然后才能编译该代码，这种表达方式难以阅读并且难以维护。
 *     文本块就是指多行字符串，例如一段格式化后的xml、json等。而有了文本块以后，用户不需要转义，Java能自动搞
 * 定。因此，文本块将提高Java程序的可读性和可写性。
 *
 * 目标
 *   - 简化跨越多行的字符串，避免对换行等特殊字符进行转义，简化编写Java程序。
 *   - 增强Java程序中字符串的可读性。
 *
 * 具体使用
 *  1. 基本使用
 *   - 文本块是Java语言中的一种新文字。它可以用来表示任何字符串，并且提供更大的表现力和更少的复杂性。
 *   - 文本块由零个或多个字符组成，由开始和结束分隔符括起来。
 *      * 开始分隔符是由三个双引号字符（"""），后面可以跟零个或多个空格，最终以行终止符结束。文本块内容以开始分隔符的行终止符后的第一个字符开始。
 *      * 结束分隔符也是由三个双引号字符（"""）表示，文本块内容以结束分隔符的第一个双引号之前的最后一个字符结束。
 *      * 文本块中的内容可以直接使用"
 *      * 文本块中的内容可以直接包括行终止符。允许在文本块中使用 \n，但不是必需的。
 *  2. 编译器在编译时会删除掉这些多余的空格。
 *  3. 转义字符: 允许开发人员使用 \n，\f 和\r 来进行字符串的垂直格式化，使用 \b和 \t进行水平格式化。
 *  4. 文本块连接: 可以在任何可以使用字符串的地方使用文本块
 * </pre>
 * @author hepengju
 */
public class _TextBlock {

    /**
     * 常用使用场景1: html片段
     */
    @Test
    public void testHtml(){
        String html =
                """
                <html>
                    <body>
                        <p>Hello, 尚硅谷</p>
                    </body>
                </html>
                """;
        System.out.println("***");
        System.out.println(html);
        System.out.println("***");
    }

    /**
     * 常用使用场景2: sql语句
     */
    @Test
    public void testSql(){
        String sql =
                """
                SELECT employee_id,last_name,salary,department_id
                  FROM employees
                 WHERE department_id in (40,50,60)
                 ORDER BY department_id ASC
                 """;
        System.out.println("###");
        System.out.println(sql);
        System.out.println("###");
    }

    /**
     * 基础测试
     */
    @Test
    public void testBase(){
        // 最后一行后面有换行符, 结束分隔符之前的空白字符会被编译器处理掉
        String str01 =
                """
                line1
                line2
                line3
                """;
        String str02 = "line1\nline2\nline3\n";
        Assert.assertEquals(str01, str02);

        // 如果不想要最后一行的换行符, 可以将结束分隔符写在如下位置
        String str03 =
                """
                line1
                line2
                line3""";
        String str04 = "line1\nline2\nline3";
        Assert.assertEquals(str03, str04);
    }

    /**
     * 编译器移除空白测试
     */
    @Test
    public void testRemoveBlank(){
        String str01 =
                """
                str01
                """;

        String str02 =
                """
                str02
              """;

        String str03 =
                """
                str03
""";

        String str04 =
                """
                str04
                  """;
        System.out.println(str01);
        System.out.println(str02);
        System.out.println(str03);
        System.out.println(str04);
    }

    /**
     * 文本块中的双引号和转义字符
     */
    @Test
    public void testQuoteAndEscape(){
        String str = """
            I'm hepengju.\t"This is quote in text block"
            """;
        System.out.println(str);
    }

    /**
     * 文本块拼接
     */
    @Test
    public void testConcat(){
        String str = "hepengju " +
                """
                This is text block
                """;
        System.out.println(str);
    }

    /**
     * 文本块中的变量处理
     */
    @Test
    public void testVarInTextBlock(){
        String type = "Person";

        // 这样拼接可读性较差
        String str1 = """
                      public void print(""" + type + """
                        o) {
                        System.out.println(Objects.toString(o));
                      }
                      """;

        // 建议方式1: String的replace方法
        String str2 = """
                      public void print($type o) {
                        System.out.println(Objects.toString(o));
                      }
                      """.replace("$type", type);

        // 建议方式2: String的format方法
        String str3 = String.format("""
                      public void print(%s o) {
                        System.out.println(Objects.toString(o));
                      }
                      """, type);

        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
    }
}
