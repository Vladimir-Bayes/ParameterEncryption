package com.terabits.domain;


/** 
 * @author 作者Vladimir E-mail: gyang.shines@gmail.com
 * @version 创建时间：2017年12月21日 下午9:27:05 
 * 类说明 
 */
public class Person {


	private String ID;
	private String Name;
	private String Tel;
	private String Sex;
	private int Age;
	private double Money;
	/**
	 * 
	 */
	public Person() {
		super();

	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getTel() {
		return Tel;
	}
	public void setTel(String tel) {
		Tel = tel;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}

	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public double getMoney() {
		return Money;
	}
	public void setMoney(double money) {
		Money = money;
	}
	@Override
	public String toString() {
		return "Person [ID=" + ID + ", Name=" + Name + ", Tel=" + Tel + ", Sex=" + Sex + ", Age=" + Age + ", Money="
				+ Money + "]";
	}

}
