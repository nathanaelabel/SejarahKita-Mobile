package com.uc.sejarahkita_mobile.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.uc.sejarahkita_mobile.model.Leaderboard;
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

    public MutableLiveData<Leaderboard> getLeaderboards() {
        final MutableLiveData<Leaderboard> listLeaderboards = new MutableLiveData<>();

        apiService.leaderboard().enqueue(new Callback<Leaderboard>() {
            @Override
            public void onResponse(Call<Leaderboard> call, Response<Leaderboard> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse" + response.body());
                        listLeaderboards.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Leaderboard> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listLeaderboards;
    }
}
