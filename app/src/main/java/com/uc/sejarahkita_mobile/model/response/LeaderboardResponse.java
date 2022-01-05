package com.uc.sejarahkita_mobile.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LeaderboardResponse{

	@SerializedName("students")
	private List<StudentsItem> students;

	@SerializedName("leaderboards")
	private List<LeaderboardsItem> leaderboards;

	public List<StudentsItem> getStudents(){
		return students;
	}

	public List<LeaderboardsItem> getLeaderboards(){
		return leaderboards;
	}
}