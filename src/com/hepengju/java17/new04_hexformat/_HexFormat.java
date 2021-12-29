package com.hepengju.java17.new04_hexformat;

import org.junit.Test;

import java.util.Arrays;
import java.util.HexFormat;

/**
 * 十六进制格式化工具类
 *
 * @see HexFormat
 */
public class _HexFormat {

    @Test
    public void testToFromHexDigits() {
        HexFormat hex = HexFormat.of();
        byte b = 127;
        String byteStr = hex.toHexDigits(b);              // toHexDigits
        byte byteVal = (byte) hex.fromHexDigits(byteStr); // fromHexDigits
        assert (byteStr.equals("7f"));
        assert (b == byteVal);
    }

    @Test
    public void testFormatParseHex() {
        HexFormat commaFormat = HexFormat.ofDelimiter(", ").withPrefix("#");
        byte[] bytes = {0, 1, 2, 3, 124, 125, 126, 127};
        // #00, #01, #02, #03, #7c, #7d, #7e, #7f    <== HexFormat.ofDelimiter(", ").withPrefix("#")
        // 00:01:02:03:7C:7D:7E:7F                   <== HexFormat.ofDelimiter(":").withUpperCase()
        String str = commaFormat.formatHex(bytes);   // formatHex
        byte[] parsed = commaFormat.parseHex(str);   // parseHex
        assert (Arrays.equals(bytes, parsed));
        System.out.println(str);
    }
}
