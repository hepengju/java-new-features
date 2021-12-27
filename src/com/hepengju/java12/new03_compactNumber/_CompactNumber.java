package com.hepengju.java12.new03_compactNumber;

import org.junit.Test;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * 压缩数字形式显示
 *
 * @author hepengju
 */
public class _CompactNumber {

    private int num01 = 100;
    private int num02 = 10000;
    private int num03 = 100_0000;
    private int num04 = 1000_0000;
    private int num05 = 10_0000_0000;

    @Test
    public void testCompactNumber(){
        NumberFormat nf01 = NumberFormat.getCompactNumberInstance();
        NumberFormat nf02 = NumberFormat.getCompactNumberInstance(Locale.CHINA, NumberFormat.Style.SHORT);
        NumberFormat nf03 = NumberFormat.getCompactNumberInstance(Locale.CHINA, NumberFormat.Style.LONG);
        NumberFormat nf04 = NumberFormat.getCompactNumberInstance(Locale.US   , NumberFormat.Style.SHORT);
        NumberFormat nf05 = NumberFormat.getCompactNumberInstance(Locale.US   , NumberFormat.Style.LONG);
        NumberFormat nf06 = NumberFormat.getCompactNumberInstance(Locale.JAPAN, NumberFormat.Style.SHORT);
        NumberFormat nf07 = NumberFormat.getCompactNumberInstance(Locale.JAPAN, NumberFormat.Style.LONG);

        // 默认(根系统区域有关)
        out(nf01, num01, num02, num03, num04, num05); // 100	1万	100万	1000万	10亿

        // 中文短, 中文长
        out(nf02, num01, num02, num03, num04, num05); // 100	1万	100万	1000万	10亿
        out(nf03, num01, num02, num03, num04, num05); // 100	1万	100万	1000万	10亿

        // 英文短, 英文长
        out(nf04, num01, num02, num03, num04, num05); // 100	10K	1M	10M	1B
        out(nf05, num01, num02, num03, num04, num05); // 100	10 thousand	1 million	10 million	1 billion

        // 日文短, 日文长
        out(nf06, num01, num02, num03, num04, num05); // 100	1万	100万	1000万	10億
        out(nf07, num01, num02, num03, num04, num05); // 100	1万	100万	1000万	10億
    }

    private void out(NumberFormat nf, int... nums){
        for (int num : nums) {
            System.out.print(nf.format(num) + "\t");
        }
        System.out.println();
    }
}
