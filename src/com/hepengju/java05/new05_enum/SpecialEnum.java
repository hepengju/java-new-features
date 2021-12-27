package com.hepengju.java05.new05_enum;

/**
 * 
 * 特殊枚举: 枚举里面有抽象方法
 * 
 * @author hepengju
 *
 */
public enum SpecialEnum {
	ONE{

		@Override
		void say() {
			System.out.println("one");
		}
		
	},TWO{

		@Override
		void say() {
			System.out.println("two");
		}
	};
	
	//抽象方法
	abstract void say();
	
}
