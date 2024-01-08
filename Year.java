package com.ums.models;

import java.util.ArrayList;
import java.util.List;

public class Year {

	ArrayList<Student> students;
	private int year;
	ArrayList<Semester> semester;
	
	public Year() {
		students = new ArrayList<>();
		semester=new ArrayList<>();
	}
	
	public ArrayList<Semester> getSemester() {
		return semester;
	}

	public void setSemester(ArrayList<Semester> semester) {
		this.semester = semester;
	}

	public Year(ArrayList<Student> students, int year) {
		this.students = students;
		this.year = year;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Year [students=" + students + ", year=" + year + ", semester=" + semester + "]";
	}
	
	

}
