package com.ums.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import java.util.Random;

public class Student {
	University university;
	private int batchNumber;
	private String studentId;
	private String name;
	private String departmentCode;
	private int semesterNumber;
	private int yearofstudy;
	private double gpa;
	private int rank;
	ArrayList<Subject> subjects;
	HashMap<Subject, Integer> subjectmarks = new HashMap<>();
	ArrayList<HashMap<Subject, Integer>> semesterHistory;

	public Student(University university) {
		this.university = university;
	}
	
	

	public void addSubject() {
		List<Batch> batches = university.getBatches();
		for (Batch batch : batches) {
			if (batch.getBatch()==(batchNumber)) {
				List<Department> departments = batch.getDepartment();
				for (Department department : departments) {
					if (department.getDepartmentCode().equals(departmentCode)) {
						List<Year> years = department.getYears();
						for (Year yearofdepartment : years) {
							if (yearofdepartment.getYear() == yearofstudy) {
								List<Semester> semesters = yearofdepartment.getSemester();
								for (Semester semester : semesters) {
									if (semester.getSemesterNumber() == semesterNumber) {
										this.subjects = semester.getSubjects();
									}
									
								}
							}
						}
					}
				}
			}
		}
	}

	public Student(University university,int batchno, String studentId, String name, String department, int year, int semesterNumber) {
		this.studentId = studentId;
		this.batchNumber = batchno;
		this.name = name;
		this.departmentCode = department;
		this.yearofstudy = year;
		this.semesterNumber = semesterNumber;
		this.university = university;
		subjects = new ArrayList<>();
		semesterHistory = new ArrayList<>();
		this.university = university;
		addSubject();
		putSubjectMarks();
	}
	

	public void putSubjectMarks() {
		//Random random = new Random();
		for (Subject subject : subjects) {
			subjectmarks.put(subject,80);
		}
		
	}

	public int getSubjectMarks(Subject studentSubject) {
		for (Subject subject : subjects) {
			if (subject.getSubjectCode() == studentSubject.getSubjectCode()) {
				return subjectmarks.get(subject);
			}
		}
		return 0;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public ArrayList<Subject> getSubjects() {
		return subjects;
	}

	public HashMap<Subject, Integer> getSubjectmarks() {
		return subjectmarks;
	}

	public int getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(int batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public int getSemesterNumber() {
		return semesterNumber;
	}

	public void setSemesterNumber(int semesterNumber) {
		this.semesterNumber = semesterNumber;
	}

	public int getYearofstudy() {
		return yearofstudy;
	}

	public void setYearofstudy(int yearofstudy) {
		this.yearofstudy = yearofstudy;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void addHistory() {
		semesterHistory.add(subjectmarks);
	}
	
	

	@Override
	public String toString() {
		return "Student [ batchNumber=" + batchNumber + ", studentId=" + studentId
				+ ", name=" + name + ", departmentCode=" + departmentCode + ", semesterNumber=" + semesterNumber
				+ ", yearofstudy=" + yearofstudy + ", gpa=" + gpa + ", rank=" + rank + ", subjects=" + subjects
				+ ", subjectmarks=" + subjectmarks + ", semesterHistory=" + semesterHistory + "]\n";
	}
	
	
}
