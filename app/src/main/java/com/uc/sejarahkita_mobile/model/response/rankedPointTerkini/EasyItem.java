package com.uc.sejarahkita_mobile.model.response.rankedPointTerkini;

import com.google.gson.annotations.SerializedName;

public class EasyItem{

	@SerializedName("ranked_point")
	private String rankedPoint;

	public String getRankedPoint(){
		return rankedPoint;
	}
}