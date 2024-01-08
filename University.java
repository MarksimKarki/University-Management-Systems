package com.ums.models;

import java.util.ArrayList;

public class University {
	private String universityName = "SKCET";
	ArrayList<Batch> batches=new ArrayList<>();
	private Admin admin = new Admin();
	private User user=new User();
	
	public User getUser() {
		return user;
	}
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public ArrayList<Batch> getBatches() {
		return batches;
	}

	public void setBatches(ArrayList<Batch> batches) {
		this.batches = batches;
	}

	@Override
	public String toString() {
		return "University [universityName=" + universityName + ", batches=" + batches + ", admin=" + admin + "]";
	}
	
	
	
}
