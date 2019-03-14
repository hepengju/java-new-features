package com.hepengju.java05.new05_enum;

/**
 * 工作日枚举: 构造方法里面有参数
 * 
 * @author hepengju
 *
 */
public enum WorkdayEnum {
	MON(1), TUE(2), WED(3), THU(4), FRI(5), SAT(6), SUN(7);
	
	private int value;
	
	//此处只能是private, 否则报错: Illegal modifier for the enum constructor; only private is permitted.
	private WorkdayEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
}
