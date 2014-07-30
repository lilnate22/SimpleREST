package com.nate.contracts;


public class Person {
	public String name;
	public String age;
	public String dob;
	public String color;
	
	public Person () {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public String toString()
	{
		return new StringBuffer("Name: ").append(this.name).append("Age: ")
				.append(this.age).append("DOB: ").append(this.dob).append("Color: ")
				.append(this.color).toString();
	}

}
