package com.ums.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.ums.models.*;

public class UniversityServices implements Services{
	University university;
	Student student;

	public UniversityServices(University university) {
		this.university = university;
	}

	public void integrateStudentExam(String id) {
		int flag=0;
		for (Batch batch : university.getBatches()) {
			for (Department department : batch.getDepartment()) {
				for (Year year : department.getYears()) {
					for (Student studentofYear : year.getStudents()) {
						if (studentofYear.getStudentId().equals(id)) {
							flag=1;
							this.student = studentofYear;
							calculateGrades(studentofYear);
							calculateRanks(year);
							student.addHistory();
							System.out.println("Integrated ");
							System.out.println(student);
						}
						if(flag==1)
						{
							break;
						}
					}
					if(flag==1)
					{
						break;
					}
										
				}
				if(flag==1)
				{
					break;
				}
			}
			if(flag==1)
			{
				break;
			}
		}
	}

	private static void calculateGrades(Student student) {
		List<Subject> subjects = student.getSubjects();
		
		double totalCredits = 0;
		double totalGradePoints = 0;

		for (Subject subject : subjects) {
			int studentGrade = getStudentGrade(student, subject);
			totalCredits += subject.getCredits();
			totalGradePoints += studentGrade * subject.getCredits();
		}

		double gpa = totalGradePoints / totalCredits;

		student.setGpa(gpa);
	}

	private static int getStudentGrade(Student student, Subject subjectofStudent) {
		List<Subject> subjectList = student.getSubjects();
		for (Subject subject : subjectList) {
			if (subject.getSubjectCode() == subjectofStudent.getSubjectCode()) {
				int score = student.getSubjectMarks(subject);
				if (score >= 90 && score <= 100) {
					return 10;
				} else if (score >= 80 && score < 90) {
					return 9;
				} else if (score >= 70 && score < 80) {
					return 8;
				} else if (score >= 60 && score < 70) {
					return 7;
				} else if (score >= 0 && score < 60) {
					return 6;
				} else {
					return 0;
				}
			}
		}
		return 0;
	}

	private static void calculateRanks(Year year) {
		List<Student> students = year.getStudents();

		Collections.sort(students, Comparator.comparingDouble(Student::getGpa).reversed());

		for (int i = 0; i < students.size(); i++) {
			students.get(i).setRank(i + 1);
		}

	}

	public void displayStudentInfo(String id) {
		//integrateStudentExam(id);
		for (Batch batch : university.getBatches())
			for (Department department : batch.getDepartment()) {
				for (Year year : department.getYears()) {
					for (Student student : year.getStudents()) {
						if (student.getStudentId().equals(id)) {
							integrateStudentExam(student.getStudentId());
							System.out.println("Id: " + student.getStudentId());
							System.out.println("Deprtment: " + student.getDepartmentCode());
							System.out.println("Name: " + student.getName());
							System.out.println("Year: " + student.getYearofstudy());
							System.out.println("Semester: " + student.getSemesterNumber());
							if (isFailed(student)) {
								System.out.println("Student is failed. Gpa is :" + student.getGpa());
							} else {
								System.out.println("GPA: " + student.getGpa());
								System.out.println("Rank: " + student.getRank());
							}
							System.out.println(student.getSubjectmarks());
							System.out.println("======================================");
							return;
						}
					}
				}
			}
	}

	private static boolean isFailed(Student student) {
		List<Subject> subjects = student.getSubjects();
		for (Subject subject : subjects) {
			if (student.getSubjectMarks(subject) < 50) {
				return true;
			}
		}
		return false;
	}
}
