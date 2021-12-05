package com.uc.sejarahkita_mobile.retrofit;

import com.google.gson.JsonObject;
import com.uc.sejarahkita_mobile.model.RegisterResponse;
import com.uc.sejarahkita_mobile.model.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiEndPoints {
    @POST("login")
    @FormUrlEncoded
    Call<TokenResponse> login(@Field("email") String email, @Field("password") String password);

    @POST("register")
    @FormUrlEncoded
    Call<RegisterResponse> register(@Field("email") String email,
                                    @Field("password") String password,
                                    @Field("password_confirmation") String password_confirmation,
                                    @Field("username") String username, @Field("name") String name,
                                    @Field("school") String school, @Field("city") String city,
                                    @Field("birthyear") String birthyear
    );

    @POST("logout")
    Call<JsonObject> logout();
}