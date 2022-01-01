package com.uc.sejarahkita_mobile.view.LeaderboardView.DetailLeaderboard;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.uc.sejarahkita_mobile.model.Leaderboard;
import com.uc.sejarahkita_mobile.model.response.LeaderboardResponse;
import com.uc.sejarahkita_mobile.repositories.LeaderboardRepository;

public class DetailLeaderboardViewModel extends AndroidViewModel{
    private LeaderboardRepository leaderboardRepository;
    private static final String TAG = "LeaderboardViewModel";

    public DetailLeaderboardViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token) {
        Log.d(TAG, "init: " + token);
        leaderboardRepository = LeaderboardRepository.getInstance(token);
    }

    private MutableLiveData<Leaderboard> resultLeaderboards = new MutableLiveData<>();

    public void getLeaderboards() {
        resultLeaderboards = leaderboardRepository.getLeaderboards();
    }

    public LiveData<Leaderboard> getResultLeaderboards() {
        return resultLeaderboards;
    }

    //* Leaderboard - Easy
    private MutableLiveData<LeaderboardResponse> resultLeaderboardEasy = new MutableLiveData<>();

    public void getLeaderboardEasy() {
        resultLeaderboardEasy = leaderboardRepository.getLeaderboardEasy();
    }

    public LiveData<LeaderboardResponse> getResultLeaderboardEasy() {
        return resultLeaderboardEasy;
    }

    //* Leaderboard - Hard
    private MutableLiveData<LeaderboardResponse> resultLeaderboardHard = new MutableLiveData<>();

    public void getLeaderboardHard() {
        resultLeaderboardHard = leaderboardRepository.getLeaderboardHard();
    }

    public LiveData<LeaderboardResponse> getResultLeaderboardHard() {
        return resultLeaderboardHard;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        leaderboardRepository.resetInstance();
    }
}
