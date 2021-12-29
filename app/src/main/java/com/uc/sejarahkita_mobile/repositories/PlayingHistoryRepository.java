package com.uc.sejarahkita_mobile.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.uc.sejarahkita_mobile.model.PlayingHistory;
import com.uc.sejarahkita_mobile.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayingHistoryRepository {
    private static PlayingHistoryRepository playingHistoryRepository;
    private RetrofitService apiService;
    private static final String TAG = "PlayingHistoryRepo";

    private PlayingHistoryRepository(String token) {
        Log.d(TAG, "token: " + token);
        apiService = RetrofitService.getInstance(token);
    }

    public static PlayingHistoryRepository getInstance(String token) {
        if (playingHistoryRepository == null) {
            playingHistoryRepository = new PlayingHistoryRepository(token);
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

    public MutableLiveData<PlayingHistory> getPlayingHistories() {
        final MutableLiveData<PlayingHistory> listPlayingHistories = new MutableLiveData<>();

        apiService.playingHistory().enqueue(new Callback<PlayingHistory>() {
            @Override
            public void onResponse(Call<PlayingHistory> call, Response<PlayingHistory> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse" + response.body());
                        listPlayingHistories.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<PlayingHistory> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listPlayingHistories;
    }
}