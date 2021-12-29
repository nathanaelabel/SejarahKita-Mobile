package com.uc.sejarahkita_mobile.model;

import com.google.gson.Gson;

import java.util.List;

public class PlayingHistory {
    private List<Playinghistories> playinghistories;

    public static PlayingHistory objectFromData(String str) {

        return new Gson().fromJson(str, PlayingHistory.class);
    }

    public List<Playinghistories> getPlayinghistories() {
        return playinghistories;
    }

    public void setPlayinghistories(List<Playinghistories> playinghistories) {
        this.playinghistories = playinghistories;
    }

    public static class Playinghistories {
        private int id_playing_history;
        private int id_student;
        private int id_level;
        private int skor;
        private String created_at;
        private String updated_at;

        public static Playinghistories objectFromData(String str) {

            return new Gson().fromJson(str, Playinghistories.class);
        }

        public int getId_playing_history() {
            return id_playing_history;
        }

        public void setId_playing_history(int id_playing_history) {
            this.id_playing_history = id_playing_history;
        }

        public int getId_student() {
            return id_student;
        }

        public void setId_student(int id_student) {
            this.id_student = id_student;
        }

        public int getId_level() {
            return id_level;
        }

        public void setId_level(int id_level) {
            this.id_level = id_level;
        }

        public int getSkor() {
            return skor;
        }

        public void setSkor(int skor) {
            this.skor = skor;
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
    }
}
