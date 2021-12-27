package com.hepengju.java05.new06_generic;

import java.util.Comparator;

/**
 * 人比较器
 * @author hepengju
 *
 */
public class PersonComparator implements Comparator<Person>{
	@Override
	public int compare(Person o1, Person o2) {
		return o1.getName().compareTo(o2.getName());
	}
}