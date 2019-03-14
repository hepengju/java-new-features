package com.hepengju.java05.new06_generic;
/**
 *  人
 * @author hepengju
 *
 */
class Person{
	private String name;
	public Person(String name) {this.name = name;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}
	
}