package com.hepengju.java09.new11_other;

import org.junit.Test;

/**
 * UnderScore(下划线)使用的限制
 * 
 * @author WGR
 *
 */
public class _UnderScore {
    
    /**
     * java 9中规定“_”不再可以单独命名标识符了，如果使用，则会报错
     */
    @Test
    public void testUnderScore(){
      //String _ = "北京";
      //System.out.println(_);
    }

}
