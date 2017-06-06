package com.bitirme.model;

public enum UserType {
	Human(true,"H"),
	Bot(false,"B");
	private final boolean type;
	private final String csvType;
	private UserType(boolean type,String csvType){
		this.type = type;
		this.csvType = csvType;
	}
	public String getCsvType(){
		return csvType;
	}
	public boolean getBoolean(){
		return type;
	}
}
