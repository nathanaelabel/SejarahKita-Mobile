package com.uc.sejarahkita_mobile.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

public class RegisterResponse implements Parcelable {
    private String email;
    private String password;
    private String password_confirmation;
    private String username;
    private String name;
    private String school;
    private String city;
    private String birthyear;

    protected RegisterResponse(Parcel in) {
        email = in.readString();
        password = in.readString();
        password_confirmation = in.readString();
        username = in.readString();
        name = in.readString();
        school = in.readString();
        city = in.readString();
        birthyear = in.readString();
    }

    public static final Creator<RegisterResponse> CREATOR = new Creator<RegisterResponse>() {
        @Override
        public RegisterResponse createFromParcel(Parcel in) {
            return new RegisterResponse(in);
        }

        @Override
        public RegisterResponse[] newArray(int size) {
            return new RegisterResponse[size];
        }
    };

    public static RegisterResponse objectFromData(String str) {

        return new Gson().fromJson(str, RegisterResponse.class);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(String birthyear) {
        this.birthyear = birthyear;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(password_confirmation);
        parcel.writeString(username);
        parcel.writeString(name);
        parcel.writeString(school);
        parcel.writeString(city);
        parcel.writeString(birthyear);
    }
}