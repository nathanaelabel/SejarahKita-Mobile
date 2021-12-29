package com.uc.sejarahkita_mobile.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

public class Leaderboard implements Parcelable {

    private List<Leaderboards> leaderboards;

    protected Leaderboard(Parcel in) {
    }

    public static final Creator<Leaderboard> CREATOR = new Creator<Leaderboard>() {
        @Override
        public Leaderboard createFromParcel(Parcel in) {
            return new Leaderboard(in);
        }

        @Override
        public Leaderboard[] newArray(int size) {
            return new Leaderboard[size];
        }
    };

    public static Leaderboard objectFromData(String str) {

        return new Gson().fromJson(str, Leaderboard.class);
    }

    public List<Leaderboards> getLeaderboards() {
        return leaderboards;
    }

    public void setLeaderboards(List<Leaderboards> leaderboards) {
        this.leaderboards = leaderboards;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public static class Leaderboards {
        private int id_leaderboard;
        private int ranked_point;
        private String created_at;
        private String updated_at;
        private int id_student;

        public static Leaderboards objectFromData(String str) {

            return new Gson().fromJson(str, Leaderboards.class);
        }

        public int getId_leaderboard() {
            return id_leaderboard;
        }

        public void setId_leaderboard(int id_leaderboard) {
            this.id_leaderboard = id_leaderboard;
        }

        public int getRanked_point() {
            return ranked_point;
        }

        public void setRanked_point(int ranked_point) {
            this.ranked_point = ranked_point;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public int getId_student() {
            return id_student;
        }

        public void setId_student(int id_student) {
            this.id_student = id_student;
        }
    }
}
