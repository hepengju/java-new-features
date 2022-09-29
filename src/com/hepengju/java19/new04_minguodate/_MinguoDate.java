package com.hepengju.java19.new04_minguodate;

import org.junit.Test;

import java.time.chrono.*;
import java.time.temporal.IsoFields;

import static java.lang.System.out;

/**
 * Support for IsoFields in JapaneseDate/MinguoDate/ThaiBuddhistDate (JDK-8279185)
 *
 * <pre>
 *  IsoChronology             公历
 *  MinguoChronology          民国历
 *  JapaneseChronology        日本历
 *  ThaiBuddhistChronology    泰国历
 *  HijrahChronology          伊斯兰历法、回历
 *  </pre>
 */
public class _MinguoDate {

    @Test
    public void testMinguoDate() {
        out.println(IsoChronology.INSTANCE.dateNow());          // 2022-09-29
        out.println(MinguoChronology.INSTANCE.dateNow());       // Minguo ROC 111-09-29
        out.println(JapaneseChronology.INSTANCE.dateNow());     // Japanese Reiwa(令和) 4-09-29
        out.println(HijrahChronology.INSTANCE.dateNow());       // Hijrah-umalqura AH 1444-03-03
        out.println(ThaiBuddhistChronology.INSTANCE.dateNow()); // ThaiBuddhist BE 2565-09-29

        // 输出第几个季度（此处为新特性）
        out.println(JapaneseDate.now().getLong(IsoFields.QUARTER_OF_YEAR));
    }
}
