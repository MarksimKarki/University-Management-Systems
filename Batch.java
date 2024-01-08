package com.ums.models;

import java.util.ArrayList;

public class Batch {
	private int batch;
	ArrayList<Department> department;

	public Batch() {
		department=new ArrayList<>();
	}

	public int getBatch() {
		return batch;
	}

	public void setBatch(int batch) {
		this.batch = batch;
	}

	public ArrayList<Department> getDepartment() {
		return department;
	}

	public void setDepartment(ArrayList<Department> department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Batch [batch=" + batch + ", department=" + department + "]";
	}
	

}
