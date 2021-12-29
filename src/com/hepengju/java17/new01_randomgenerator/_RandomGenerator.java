package com.hepengju.java17.new01_randomgenerator;

import org.junit.Test;

import java.util.Arrays;
import java.util.random.RandomGenerator;

/**
 * 增强的伪随机数生成器 RandomGenerator，适应基于stream操作的程序
 */
public class _RandomGenerator {

    @Test
    public void testRandomGenerator() {
        // L32X64MixRandom
        var random = RandomGenerator.getDefault();

        System.out.println(random.nextInt(10));
        System.out.println(random.nextDouble(10));
        System.out.println(random.nextGaussian());
        System.out.println(Arrays.toString(random.ints(5).toArray())); // stream流

        // L64X128MixRandom
        RandomGenerator random2 = RandomGenerator.of("L64X128MixRandom");
        System.out.println(Arrays.toString(random2.ints(5).toArray()));
    }
}