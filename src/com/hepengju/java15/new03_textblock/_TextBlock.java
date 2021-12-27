package com.hepengju.java15.new03_textblock;

import org.junit.Test;

/**
 * 文本块(正式发布)
 *
 * @see com.hepengju.java13.new02_textblock._TextBlock
 */
public class _TextBlock {

    /**
     * 新增默认的\, 输出为单行结果
     */
    @Test public void testToOneLine() {
        String sql =
                """
                SELECT employee_id,last_name,salary,department_id \
                  FROM employees \
                 WHERE department_id in (40,50,60) \
                 ORDER BY department_id ASC\
                 """;
        System.out.println("###");
        // SELECT employee_id,last_name,salary,department_id   FROM employees  WHERE department_id in (40,50,60)  ORDER BY department_id ASC
        System.out.println(sql);
        System.out.println("###");
    }

}
