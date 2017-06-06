package com.bitirme.core;

public class Ogrenci {
	int studentNumber;
	String name;
	String surname;
	public Ogrenci(int studentNumber, String name, String surname) {
		super();
		this.studentNumber = studentNumber;
		this.name = name;
		this.surname = surname;
	}
	public int getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
}
