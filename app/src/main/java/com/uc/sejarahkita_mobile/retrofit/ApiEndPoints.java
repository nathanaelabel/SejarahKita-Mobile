package com.uc.sejarahkita_mobile.retrofit;

import com.google.gson.JsonObject;
import com.uc.sejarahkita_mobile.model.PlayingHistory;
import com.uc.sejarahkita_mobile.model.Question;
import com.uc.sejarahkita_mobile.model.RegisterResponse;
import com.uc.sejarahkita_mobile.model.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiEndPoints {
    //* Register
    @POST("register")
    @FormUrlEncoded
    Call<RegisterResponse> register(@Field("email") String email,
                                    @Field("password") String password,
                                    @Field("password_confirmation") String password_confirmation,
                                    @Field("username") String username, @Field("name") String name,
                                    @Field("school") String school, @Field("city") String city,
                                    @Field("birthyear") String birthyear
    );

    //* Login
    @POST("login")
    @FormUrlEncoded
    Call<TokenResponse> login(@Field("email") String email, @Field("password") String password);

    //* Logout
    @POST("logout")
    Call<JsonObject> logout();

    //* Game
    @GET("questions")
    Call<Question> question(@Query("id_level") int id_level);

    @POST("check-answer")
    @FormUrlEncoded
    Call<JsonObject> checkAnswer(@Field("id") String id, @Field("input_jawaban") String input_jawaban);

    //* Leaderboard


    //* Playing History
    @GET("playinghistories")
    Call<PlayingHistory> playingHistory();
}