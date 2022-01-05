package com.uc.sejarahkita_mobile.model.response;

import com.google.gson.annotations.SerializedName;

public class LeaderboardsItem {

    @SerializedName("ranked_point")
    private String rankedPoint;

    @SerializedName("id_level")
    private int idLevel;

    @SerializedName("id_student")
    private int idStudent;

    public String getRankedPoint() {
        return rankedPoint;
    }

    public int getIdLevel() {
        return idLevel;
    }

    public int getIdStudent() {
        return idStudent;
    }
}