package com.ums.models;

public class Subject {
	private String subjectCode;
	private String subjectName;
	private int credits;

	public Subject(){}
	
	public Subject(String subjectCode, String subjectName, int credits) {
		this.subjectCode = subjectCode;
		this.subjectName = subjectName;
		this.credits = credits;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	@Override
	public String toString() {
		return "Subject [subjectCode=" + subjectCode + ", subjectName=" + subjectName + ", credits=" + credits + "]";
	}


	
}
