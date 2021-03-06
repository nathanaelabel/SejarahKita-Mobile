package com.uc.sejarahkita_mobile.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.uc.sejarahkita_mobile.model.response.LeaderboardResponse;
import com.uc.sejarahkita_mobile.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderboardRepository {
    private static LeaderboardRepository playingHistoryRepository;
    private RetrofitService apiService;
    private static final String TAG = "LeaderboardRepo";

    private LeaderboardRepository(String token) {
        Log.d(TAG, "token: " + token);
        apiService = RetrofitService.getInstance(token);
    }

    public static LeaderboardRepository getInstance(String token) {
        if (playingHistoryRepository == null) {
            playingHistoryRepository = new LeaderboardRepository(token);
        }
        return playingHistoryRepository;
    }

    public synchronized void resetInstance() {
        if (playingHistoryRepository != null) {
            playingHistoryRepository = null;
        } else {
            playingHistoryRepository = null;
        }
    }

    public MutableLiveData<LeaderboardResponse> getLeaderboards() {
        final MutableLiveData<LeaderboardResponse> listLeaderboards = new MutableLiveData<>();

        apiService.leaderboardEasy().enqueue(new Callback<LeaderboardResponse>() {
            @Override
            public void onResponse(Call<LeaderboardResponse> call, Response<LeaderboardResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse" + response.body());
                        listLeaderboards.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<LeaderboardResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listLeaderboards;
    }

    //* Leaderboard - Easy
    public MutableLiveData<LeaderboardResponse> getLeaderboardEasy() {
        final MutableLiveData<LeaderboardResponse> listLeaderboardEasy = new MutableLiveData<>();

        apiService.leaderboardEasy().enqueue(new Callback<LeaderboardResponse>() {
            @Override
            public void onResponse(Call<LeaderboardResponse> call, Response<LeaderboardResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse" + response.body());
                        listLeaderboardEasy.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<LeaderboardResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listLeaderboardEasy;
    }

    //* Leaderboard - Hard
    public MutableLiveData<LeaderboardResponse> getLeaderboardHard() {
        final MutableLiveData<LeaderboardResponse> listLeaderboardHard = new MutableLiveData<>();

        apiService.leaderboardHard().enqueue(new Callback<LeaderboardResponse>() {
            @Override
            public void onResponse(Call<LeaderboardResponse> call, Response<LeaderboardResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse" + response.body());
                        listLeaderboardHard.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<LeaderboardResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listLeaderboardHard;
    }
}
