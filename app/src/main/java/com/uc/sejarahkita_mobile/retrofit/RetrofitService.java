package com.uc.sejarahkita_mobile.retrofit;

import com.google.gson.JsonObject;
import com.uc.sejarahkita_mobile.helper.Const;
import com.uc.sejarahkita_mobile.model.PlayingHistory;
import com.uc.sejarahkita_mobile.model.Profile;
import com.uc.sejarahkita_mobile.model.Question;
import com.uc.sejarahkita_mobile.model.RegisterResponse;
import com.uc.sejarahkita_mobile.model.TokenResponse;
import com.uc.sejarahkita_mobile.model.body.PlayingHistoryBody;
import com.uc.sejarahkita_mobile.model.response.LeaderboardResponse;
import com.uc.sejarahkita_mobile.model.response.PlayingHistoryResponse;
import com.uc.sejarahkita_mobile.model.response.rankedPointTerkini.RankedPointTerkiniResponse;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private final ApiEndPoints api;
    private static RetrofitService service;
    private static final String TAG = "RetrofitService";

    public RetrofitService(String token) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        if (token.equals("")) {
            client.addInterceptor(chain -> {
                Request request = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .build();
                return chain.proceed(request);
            });
        } else {
            client.addInterceptor(chain -> {
                Request request = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Authorization", token)
                        .build();
                return chain.proceed(request);
            });
        }

        api = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build().create(ApiEndPoints.class);
    }

    public static RetrofitService getInstance(String token) {
        if (service == null) {
            service = new RetrofitService(token);
        } else if (!token.equals("")) {
            service = new RetrofitService(token);
        }
        return service;
    }

    public Call<TokenResponse> login(String email, String password) {
        return api.login(email, password);
    }

    public Call<RegisterResponse> register(String email, String password, String password_confirmation, String username, String name, String school, String city, String birthyear) {
        return api.register(email, password, password_confirmation, username, name, school, city, birthyear);
    }

    public Call<Profile> getProfile(String id) {
        return api.getProfile(id);
    }

    public Call<JsonObject> logout() {
        return api.logout();
    }

    public Call<Question> question(int id_level) {
        return api.question(id_level);
    }

    public Call<JsonObject> checkAnswer(String id, String inputJawaban) {
        return api.checkAnswer(id, inputJawaban);
    }

    public Call<PlayingHistoryResponse> submitScore(PlayingHistoryBody body) {
        return api.submitScore(body);
    }

//    public Call<Leaderboard> leaderboard() {
//        return api.leaderboard();
//    }

    public Call<LeaderboardResponse> leaderboardEasy() {
        return api.leaderboardEasy();
    }

    public Call<LeaderboardResponse> leaderboardHard() {
        return api.leaderboardHard();
    }

    public Call<PlayingHistory> playingHistory(String id) {
        return api.playingHistory(id);
    }

    public Call<RankedPointTerkiniResponse> rankedPointTerkini(String id) {
        return api.rankedPointTerkini(id);
    }
}