package com.ums.models;

import java.util.ArrayList;

public class Semester {
	private int semesterNumber;
	private ArrayList<Subject> subjects;

	public Semester() {
		subjects = new ArrayList<>();
	}

	public int getSemesterNumber() {
		return semesterNumber;
	}

	public void setSemesterNumber(int semesterNumber) {
		this.semesterNumber = semesterNumber;
	}

	public ArrayList<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(ArrayList<Subject> subjects) {
		this.subjects = subjects;
	}

	@Override
	public String toString() {
		return "Semester [semesterNumber=" + semesterNumber + ", subjects=" + subjects + "]";
	}
	
	

}
