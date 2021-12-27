package com.hepengju.java05.new06_generic;

import java.util.Comparator;

/**
 *  学生比较器
 * @author hepengju
 *
 */
public class StudentComparator implements Comparator<Student>{
	@Override
	public int compare(Student o1, Student o2) {
		return o1.getName().compareTo(o2.getName()) == 0 ? o1.getAge().compareTo(o2.getAge()) : o1.getName().compareTo(o2.getName());
	}
}