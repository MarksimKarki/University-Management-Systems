package com.ums.application;

import java.util.ArrayList;
import java.util.Scanner;

import com.ums.models.Batch;
import com.ums.models.Department;
import com.ums.models.Semester;
import com.ums.models.Student;
import com.ums.models.Subject;
import com.ums.models.University;
import com.ums.models.Year;
import com.ums.services.AdminServices;
import com.ums.services.UniversityServices;

public class UniversityManagementApplication {
	static University university = initializeUniversity();
	static int adminornot = 0;
	static int userornot=0;

	public static void main(String args[]) {
		if (performLogin()) {
			displayMenu(university);
			System.exit(0);
		} else {
			System.out.print("Login failed");
			System.exit(0);
		}
	}

	private static boolean performLogin() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("choose 1 for admin or 2 for user");
		char choice = scanner.next().charAt(0);
		switch (choice) {
		case '1':
			System.out.println("Enter username: ");
			String adminname = scanner.next();
			System.out.println("Enter password: ");
			String adminpassword = scanner.next();
			adminornot = 1;
			if (adminname.equals(university.getAdmin().getName())
					&& adminpassword.equals(university.getAdmin().getPassword())) {
				return true;
			}
			return false;
		case '2':
			System.out.println("Enter username: ");
			String username = scanner.next();
			System.out.println("Enter password: ");
			String userpassword = scanner.next();
			userornot = 1;
			if (username.equals(university.getUser().getName())
					&& userpassword.equals(university.getUser().getPassword())) {
				return true;
			}
			return false;
		default:
			System.out.println("Invalid choice:");
			System.exit(0);
		}
		return false;
	}

	private static void displayMenu(University university) {
		Scanner scanner = new Scanner(System.in);
		UniversityServices universityServices = new UniversityServices(university);
		AdminServices adminServices = new AdminServices(university);
		boolean flag = true;
		while (flag) {
			System.out.println("\n===== University Management System =====");
			System.out.println("1. Manage All Records");
			System.out.println("2. Integrate All Records of Exams");
			System.out.println("3. Display All Student Information");
			System.out.println("4. Exit");

			System.out.println("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				if (adminornot == 1) {
					adminServices.manageAllRecords();
				} else {
					System.out.println("You are not a admin");
				}
				break;
			case 2:
				if(userornot==1 || adminornot==1) {
				System.out.println("Integrating all records of Exams students");
				System.out.println("Enter student ID:");
				String id = scanner.next();
				universityServices.integrateStudentExam(id);
				}
				else {
					System.out.print("You are not a user");
				}
				break;
			case 3:
				System.out.println("Enter student ID:");
				String studentid = scanner.next();
				universityServices.displayStudentInfo(studentid);
				break;
			case 4:
				flag = false;
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private static University initializeUniversity() {
		University skcetUniversity = new University();
		Batch batch1 = new Batch();
		batch1.setBatch(1);
		Batch batch2 = new Batch();
		batch2.setBatch(2);

		Department department1 = new Department();
		department1.setDepartmentCode("DEP001");
		department1.setDepartmentName("CSE");
		Department department2 = new Department();
		department2.setDepartmentCode("DEP002");
		department2.setDepartmentName("it");

		// Create Years
		Year year1 = new Year();
		year1.setYear(1);
		Year year2 = new Year();
		year2.setYear(2);
		Year year3 = new Year();
		year3.setYear(3);
		Year year4 = new Year();
		year4.setYear(4);

		// Create Semesters
		Semester semester1 = new Semester();
		semester1.setSemesterNumber(1);
		Semester semester2 = new Semester();
		semester2.setSemesterNumber(2);
		Semester semester3 = new Semester();
		semester3.setSemesterNumber(3);
		Semester semester4 = new Semester();
		semester4.setSemesterNumber(4);

		// Create Subjects
		Subject subject1 = new Subject();
		subject1.setSubjectCode("SUB001");
		subject1.setSubjectName("Mathematics");
		subject1.setCredits(3);

		Subject subject2 = new Subject();
		subject2.setSubjectCode("SUB002");
		subject2.setSubjectName("Physics");
		subject2.setCredits(4);

		Subject subject3 = new Subject();
		subject3.setSubjectCode("SUB003");
		subject3.setSubjectName("Computer Science");
		subject3.setCredits(3);

		Subject subject4 = new Subject();
		subject4.setSubjectCode("SUB004");
		subject4.setSubjectName("English");
		subject4.setCredits(2);
		ArrayList<Subject> sem1 = new ArrayList<Subject>();
		sem1.add(subject1);
		sem1.add(subject2);
		
		ArrayList<Subject> sem2 = new ArrayList<Subject>();
		sem2.add(subject3);
		sem2.add(subject4);

		// Assign Subjects to Semesters
		semester1.setSubjects(sem1);
		semester2.setSubjects(sem2);
		semester3.setSubjects(sem1);
		semester4.setSubjects(sem2);
		
		ArrayList<Semester> year=new ArrayList<Semester>();
		year.add(semester1);
		year1.setSemester(new ArrayList<>(year));
		year.clear();
		year.add(semester2);
		year2.setSemester(new ArrayList<>(year));
		year.clear();
		year.add(semester3);
		year3.setSemester(new ArrayList<>(year));
		year.clear();
		year.add(semester4);
		year4.setSemester(new ArrayList<>(year));

		// Assign Years to Departments
		ArrayList<Year> dep1year =new ArrayList<>();
		dep1year.add(year1);
		dep1year.add(year3);
		ArrayList<Year> dep2year =new ArrayList<>();
		dep2year.add(year2);
		dep2year.add(year4);
		department1.setYears(dep1year);
		department2.setYears(dep2year);
		
		ArrayList<Department> batch=new ArrayList<>();
		batch.add(department1);
		batch1.setDepartment(batch);
		ArrayList<Department> bach2=new ArrayList<>();
		bach2.add(department2);
		batch2.setDepartment(bach2);
		
		ArrayList<Batch> uni=new ArrayList<>();
		uni.add(batch1);
		uni.add(batch2);
		skcetUniversity.setBatches(uni);

		// Random random = new Random();
		for (int i = 1; i <= 15; i++) {
			Student student = new Student(skcetUniversity, (i % 2) == 0 ? 2 : 1, "ID" + i, "Student" + i,
					(i % 2 == 0) ? "DEP002" : "DEP001", get(i),get(i));
			switch(student.getBatchNumber()) {
			case 1:
				if(student.getDepartmentCode().equals("DEP001")) {
					ArrayList<Year> depyear=(ArrayList<Year>) department1.getYears();
					switch(student.getYearofstudy()) {
					case 1:
						ArrayList<Student> studentyear1=new ArrayList<Student>(year1.getStudents());
						studentyear1.add(student);
						year1.setStudents(studentyear1);
						depyear.add(year1);
						break;
					case 3:
						ArrayList<Student> studentyear3=new ArrayList<Student>(year3.getStudents());
						studentyear3.add(student);
						year3.setStudents(studentyear3);
						depyear.add(year3);
						break;
					
					}
					department1.setYears(depyear);
				}
				break;
			case 2:
				ArrayList<Year> department2year=(ArrayList<Year>)department2.getYears();
				switch(student.getYearofstudy()) {
				case 2:
					ArrayList<Student> studentyear2=(ArrayList<Student>) year2.getStudents();
					studentyear2.add(student);
					year2.setStudents(studentyear2);
					department2year.add(year2);
					break;
				case 4:
					ArrayList<Student> studentyear4=new ArrayList<Student>(year4.getStudents());
					studentyear4.add(student);
					year4.setStudents(studentyear4);
					department2year.add(year4);
					break;
				}
				department2.setYears(department2year);
				break;
			}
			//System.out.println(student);
		}
		return skcetUniversity;
	}
	private static int get(int i) {
		switch(i%4) {
			case 0:
				return 4;
			case 1:
				return 3;
			case 2:
				return 2;
			case 3:
				return 1;
		}
		return 0;	
	}

}
