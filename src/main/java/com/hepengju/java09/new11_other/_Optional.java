package com.hepengju.java09.new11_other;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * Optional类中提供了转换为Stream的方法：stream()
 * 
 * @author WGR
 *
 */
public class _Optional {
    
   /**
    * 对stream()进行测试
    */
    @Test
    public void testOptional() {

        List<String> list = new ArrayList<>();
        list.add("Tom");
        list.add("Jerry");
        list.add("Tim");

        Optional<List<String>> optional = Optional.ofNullable(list);

        Stream<String> stream = optional.stream().flatMap(x -> x.stream());

        stream.forEach(System.out::println); // Tom Jerry Tim


    }

}
