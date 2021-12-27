package com.hepengju.java08.new11_base64;

import java.util.Base64;

/**
 * 简易的Base64加密和解密功能
 *
 * @author he_pe 2018-01-11
 */
public class Base64Util {

	// 加密
	public static String encode(String src) {
		return new String(Base64.getEncoder().encode(src.getBytes()));
	}
	
	// 解密
	public static String decode(String src) {
		return new String(Base64.getDecoder().decode(src.getBytes()));
	}
}
