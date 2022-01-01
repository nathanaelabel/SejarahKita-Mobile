package com.uc.sejarahkita_mobile.model.response;

import com.google.gson.annotations.SerializedName;

public class LeaderboardsItem{

	@SerializedName("ranked_point")
	private int rankedPoint;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("id_level")
	private int idLevel;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id_leaderboard")
	private int idLeaderboard;

	@SerializedName("id_student")
	private int idStudent;

	public int getRankedPoint(){
		return rankedPoint;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public int getIdLevel(){
		return idLevel;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getIdLeaderboard(){
		return idLeaderboard;
	}

	public int getIdStudent(){
		return idStudent;
	}
}