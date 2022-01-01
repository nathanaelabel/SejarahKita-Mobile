package com.uc.sejarahkita_mobile.model.response;

import com.google.gson.annotations.SerializedName;

public class PlayingHistoryResponse{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("status")
	private boolean status;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}