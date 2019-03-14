package com.hepengju.java05.new06_generic;
/**
 * 学生
 * @author hepengju
 *
 */
public class Student extends Person{
	private Integer age;
	public Student(String name, Integer age) {
		super(name);
		this.age = age;
	}
	public Integer getAge() {return age;}
	public void setAge(Integer age) {this.age = age;}
	@Override
	public String toString() {
		return "Student [age=" + age + ", getName()=" + getName() + "]";
	}
	
}