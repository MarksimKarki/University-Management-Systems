package com.ums.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ums.models.Batch;
import com.ums.models.Department;
import com.ums.models.Semester;
import com.ums.models.Student;
import com.ums.models.Subject;
import com.ums.models.University;
import com.ums.models.Year;

public class AdminServices {

	University university;
	Scanner scanner = new Scanner(System.in);

	public AdminServices(University university) {
		this.university = university;
	}

	public void manageAllRecords() {
		while (true) {
			System.out.println("\n===== Manage All Records =====");
			System.out.println("1. Add Department");
			System.out.println("2. Remove Department");
			System.out.println("3. Add Student");
			System.out.println("4. Remove Student");
			System.out.println("5. Add Subject");
			System.out.println("6. Remove Subject");
			System.out.println("7. Back to Main Menu");

			System.out.println("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); 

			switch (choice) {
			case 1:
				addDepartment();
				break;
			case 2:
				displayDepartments();
				System.out.println("Enter the code to remove department:");
				String code = scanner.next();
				removeDepartment(code);
				break;
			case 3:
				addStudent();
				break;
			case 4:
				removeStudent();
				break;
			case 5:
				addSubject();
				break;
			case 6:
				removeSubject();
				break;
			case 7:
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private void addDepartment() {
		System.out.println("Enter the department code: ");
		String departmentCode = scanner.nextLine();

		System.out.println("Enter the department name: ");
		String departmentName = scanner.nextLine();
		
		Year year1 = new Year();
		year1.setYear(1);
		
		Semester semester1 = new Semester();
		semester1.setSemesterNumber(1);
		
		Subject subject1 = new Subject();
		subject1.setSubjectCode("SUB00"+departmentCode);
		subject1.setSubjectName("Machine learning");
		subject1.setCredits(4);
		
		ArrayList<Subject> sem1 = new ArrayList<Subject>();
		sem1.add(subject1);
		semester1.setSubjects(sem1);
		
		ArrayList<Semester> year=new ArrayList<Semester>();
		year.add(semester1);
		year1.setSemester(new ArrayList<>(year));
		
		ArrayList<Year> dep1year =new ArrayList<>();
		dep1year.add(year1);
		
		 Department department = new Department(departmentCode,departmentName,dep1year);
		
		 university.getBatches().get(0).getDepartment().add(department);

		System.out.println("Department added successfully.");
	}

	private void removeDepartment(String departmentcode) {
		List<Batch> batches = university.getBatches();
		for (Batch batch : batches) {
			List<Department> departments = batch.getDepartment();
			for (Department department : departments) {
				if (department.getDepartmentCode().equals(departmentcode)) {
					ArrayList<Department> departments1=batch.getDepartment();
					departments1.remove(department);
					batch.setDepartment(departments1);
				}
			}
		}
	}

	private void addStudent() {
		
		System.out.println("Enter the batch Number:");
		int batchno=scanner.nextInt();

		System.out.println("Enter the student ID: ");
		String studentID = scanner.next();

		System.out.println("Enter the student name: ");
		String studentName = scanner.next();
		
		System.out.println("Enter the department ");
		String departmentstudent = scanner.next();
		
		System.out.println("Enter the yearofstudy ");
		int yearstudent = scanner.nextInt();
		
		Student student=new Student(university,batchno, studentID,studentName,departmentstudent,yearstudent,yearstudent);
		for (Batch batch : university.getBatches()) {
			if (batch.getBatch()==(batchno)) {
				List<Department> departments = batch.getDepartment();
				for (Department department : departments) {
					if (department.getDepartmentCode().equals(departmentstudent)) {
						List<Year> years = department.getYears();
						for (Year yearofdepartment : years) {
							if (yearofdepartment.getYear() == yearstudent) {
								ArrayList<Student> students=(ArrayList<Student>) yearofdepartment.getStudents();
								students.add(student);
								yearofdepartment.setStudents(students);
							}
						}
					}
				}
			}
		}
		System.out.println(student);
		System.out.println("Student added successfully.");
	}

	private void removeStudent() {
		System.out.println("Enter student ID: ");
		String id = scanner.next();
		for (Batch batch : university.getBatches()) {
			for (Department department : batch.getDepartment()) {
				for (Year year : department.getYears()) {
					for (Student studentofYear : year.getStudents()) {
						if (studentofYear.getStudentId().equals(id)) {
							ArrayList<Student> student=(ArrayList<Student>) year.getStudents();
							student.remove(studentofYear);
							year.setStudents(student);
						}
					}
				}
			}
		}
	}

	private void addSubject() {
		System.out.println("Select a department: ");
		displayDepartments();
		int departmentIndex = scanner.nextInt();
		scanner.nextLine();
		
		Department selectedDepartment = university.getBatches().get(0).getDepartment().get(departmentIndex - 1);

		System.out.println("Select a year: ");
		displayYears(selectedDepartment);
		int yearIndex = scanner.nextInt();
		scanner.nextLine();

		Year selectedYear = selectedDepartment.getYears().get(yearIndex - 1);

		System.out.println("Enter the subject code: ");
		String subjectCode = scanner.nextLine();

		System.out.println("Enter the subject name: ");
		String subjectName = scanner.nextLine();

		System.out.println("Enter the credits: ");
		int credits = scanner.nextInt();
		scanner.nextLine();

		Subject subject = new Subject(subjectCode, subjectName, credits);
		selectedYear.getSemester().get(0).getSubjects().add(subject);

		System.out.println("Subject added successfully.");
	}

	private void removeSubject() {

		System.out.println("Enter the subject code: ");
		String subjectCode = scanner.nextLine();

		for (Batch batch : university.getBatches()) {
			for (Department department : batch.getDepartment()) {
				for (Year year : department.getYears()) {
					for(Semester semester:year.getSemester()){
						for(Subject subject:semester.getSubjects()){
							if(subject.getSubjectCode().equals(subjectCode)){
								ArrayList<Subject> subjects=(ArrayList<Subject>)semester.getSubjects();
								subjects.remove(subject);
								semester.setSubjects(subjects);
								break;
							}
						}
					}
				}
			}
		}
	}

	private void displayDepartments() {
		List<Department> departments = university.getBatches().get(0).getDepartment();
		for (int i = 0; i < departments.size(); i++) {
			System.out.println((i + 1) + ". " + departments.get(i).getDepartmentName());
		}
	}

	private void displayYears(Department department) {
		List<Year> years = department.getYears();
		for (int i = 0; i < years.size(); i++) {
			System.out.println((i + 1) + ". Year " + years.get(i).getYear());
		}
	}
}
