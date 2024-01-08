package com.ums.models;

import java.util.ArrayList;

public class Department {
	private String departmentCode;
	private String departmentName;
	ArrayList<Year> years;
	
	public Department(){years=new ArrayList<>();}
	public Department(String departmentCode, String departmentName) {
		this.departmentCode = departmentCode;
		this.departmentName = departmentName;
		years=new ArrayList<>();
	}
	
	public Department(String departmentCode, String departmentName, ArrayList<Year> years) {
		this.departmentCode = departmentCode;
		this.departmentName = departmentName;
		this.years = years;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public ArrayList<Year> getYears() {
		return years;
	}

	public void setYears(ArrayList<Year> list) {
		this.years = list;
	}
	@Override
	public String toString() {
		return "Department [departmentCode=" + departmentCode + ", departmentName=" + departmentName + ", years="
				+ years + "]";
	}

}
