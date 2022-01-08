package com.uc.sejarahkita_mobile.view.LeaderboardView;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.uc.sejarahkita_mobile.model.response.LeaderboardResponse;
import com.uc.sejarahkita_mobile.model.response.rankedPointTerkini.RankedPointTerkiniResponse;
import com.uc.sejarahkita_mobile.repositories.LeaderboardRepository;
import com.uc.sejarahkita_mobile.repositories.PlayingHistoryRepository;

public class LeaderboardViewModel extends AndroidViewModel {
    private LeaderboardRepository leaderboardRepository;
    private PlayingHistoryRepository playingHistoryRepository;
    private static final String TAG = "LeaderboardViewModel";

    public LeaderboardViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token) {
        Log.d(TAG, "init: " + token);
        leaderboardRepository = LeaderboardRepository.getInstance(token);
        playingHistoryRepository = PlayingHistoryRepository.getInstance(token);
    }

    private MutableLiveData<LeaderboardResponse> resultLeaderboards = new MutableLiveData<>();

    public void getLeaderboards() {
        resultLeaderboards = leaderboardRepository.getLeaderboardEasy();
    }

    public LiveData<LeaderboardResponse> getResultLeaderboards() {
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

    //* Ranked Point Terkini
    private MutableLiveData<RankedPointTerkiniResponse> terkini = new MutableLiveData<>();

    public void setTerkini(String id) {
        terkini = playingHistoryRepository.getRankedPointTerkini(id);
    }

    public LiveData<RankedPointTerkiniResponse> getTerkini() {
        return terkini;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        leaderboardRepository.resetInstance();
    }
}