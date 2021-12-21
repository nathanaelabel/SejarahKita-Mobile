package com.uc.sejarahkita_mobile.model;

import com.google.gson.Gson;

import java.util.List;

public class Question {
    private List<QuestionItem> questions;

    public static Question objectFromData(String str) {

        return new Gson().fromJson(str, Question.class);
    }

    public List<QuestionItem> getQuestionItem() {
        return questions;
    }

    public void setQuestionItem(List<QuestionItem> questions) {
        this.questions = questions;
    }

    public static class QuestionItem {
        private int id_question;
        private String pertanyaan_kalimat;
        private String pertanyaan_path_gambar;
        private String kunci_jawaban;
        private String created_at;
        private String updated_at;
        private int id_level;

        public static QuestionItem objectFromData(String str) {

            return new Gson().fromJson(str, QuestionItem.class);
        }

        public int getId_question() {
            return id_question;
        }

        public void setId_question(int id_question) {
            this.id_question = id_question;
        }

        public String getPertanyaan_kalimat() {
            return pertanyaan_kalimat;
        }

        public void setPertanyaan_kalimat(String pertanyaan_kalimat) {
            this.pertanyaan_kalimat = pertanyaan_kalimat;
        }

        public String getPertanyaan_path_gambar() {
            return pertanyaan_path_gambar;
        }

        public void setPertanyaan_path_gambar(String pertanyaan_path_gambar) {
            this.pertanyaan_path_gambar = pertanyaan_path_gambar;
        }

        public String getKunci_jawaban() {
            return kunci_jawaban;
        }

        public void setKunci_jawaban(String kunci_jawaban) {
            this.kunci_jawaban = kunci_jawaban;
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

        public int getId_level() {
            return id_level;
        }

        public void setId_level(int id_level) {
            this.id_level = id_level;
        }
    }
}