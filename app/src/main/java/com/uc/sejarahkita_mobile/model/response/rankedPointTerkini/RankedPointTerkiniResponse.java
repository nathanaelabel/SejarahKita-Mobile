package com.uc.sejarahkita_mobile.model.response.rankedPointTerkini;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RankedPointTerkiniResponse{

	@SerializedName("hard")
	private List<HardItem> hard;

	@SerializedName("easy")
	private List<EasyItem> easy;

	public List<HardItem> getHard(){
		return hard;
	}

	public List<EasyItem> getEasy(){
		return easy;
	}
}