package com.uc.sejarahkita_mobile.model.body;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class PlayingHistoryBody {

    @SerializedName("ranked_point")
    private String rankedPoint;

    @SerializedName("id_level")
    private String idLevel;

    @SerializedName("skor")
    private String skor;

    @SerializedName("id_student")
    private String idStudent;

    public void setRankedPoint(String rankedPoint) {
        this.rankedPoint = rankedPoint;
    }

    public String getRankedPoint() {
        return rankedPoint;
    }

    public void setIdLevel(String idLevel) {
        this.idLevel = idLevel;
    }

    public String getIdLevel() {
        return idLevel;
    }

    public void setSkor(String skor) {
        this.skor = skor;
    }

    public String getSkor() {
        return skor;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public String getIdStudent() {
        return idStudent;
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this, PlayingHistoryBody.class);
    }
}