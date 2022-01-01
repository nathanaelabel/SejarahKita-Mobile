package com.uc.sejarahkita_mobile.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LeaderboardResponse{

	@SerializedName("leaderboards")
	private List<LeaderboardsItem> leaderboards;

	public List<LeaderboardsItem> getLeaderboards(){
		return leaderboards;
	}
}