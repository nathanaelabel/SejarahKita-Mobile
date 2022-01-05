package com.uc.sejarahkita_mobile.model.response;

import com.google.gson.annotations.SerializedName;

public class StudentsItem{

	@SerializedName("id")
	private int id;

	@SerializedName("username")
	private String username;

	public int getId(){
		return id;
	}

	public String getUsername(){
		return username;
	}
}