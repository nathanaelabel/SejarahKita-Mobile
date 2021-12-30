package com.uc.sejarahkita_mobile.view.LeaderboardView;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.uc.sejarahkita_mobile.model.Leaderboard;
import com.uc.sejarahkita_mobile.repositories.LeaderboardRepository;

public class LeaderboardViewModel extends AndroidViewModel {
    private LeaderboardRepository playingHistoryRepository;
    private static final String TAG = "LeaderboardViewModel";

    public LeaderboardViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token) {
        Log.d(TAG, "init: " + token);
        playingHistoryRepository = LeaderboardRepository.getInstance(token);
    }

    private MutableLiveData<Leaderboard> resultPlayingHistories = new MutableLiveData<>();

    public void getPlayingHistories() {
        resultPlayingHistories = playingHistoryRepository.getLeaderboards();
    }

    public LiveData<Leaderboard> getResultPlayingHistories() {
        return resultPlayingHistories;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        playingHistoryRepository.resetInstance();
    }
}
