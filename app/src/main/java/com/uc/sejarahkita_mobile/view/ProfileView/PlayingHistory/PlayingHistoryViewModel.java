package com.uc.sejarahkita_mobile.view.ProfileView.PlayingHistory;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.uc.sejarahkita_mobile.model.PlayingHistory;
import com.uc.sejarahkita_mobile.model.body.PlayingHistoryBody;
import com.uc.sejarahkita_mobile.model.response.PlayingHistoryResponse;
import com.uc.sejarahkita_mobile.repositories.PlayingHistoryRepository;

public class PlayingHistoryViewModel extends AndroidViewModel {
    private PlayingHistoryRepository playingHistoryRepository;
    private static final String TAG = "PlayingHistoryViewModel";

    public PlayingHistoryViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token) {
        Log.d(TAG, "init: " + token);
        playingHistoryRepository = PlayingHistoryRepository.getInstance(token);
    }

    //* Playing History
    private MutableLiveData<PlayingHistory> resultPlayingHistories = new MutableLiveData<>();

    public void getPlayingHistories(String id) {
        resultPlayingHistories = playingHistoryRepository.getPlayingHistories(id);
    }

    public LiveData<PlayingHistory> getResultPlayingHistories() {
        return resultPlayingHistories;
    }

    //* Score Result
    private MutableLiveData<PlayingHistoryResponse> resultSubmitScore = new MutableLiveData<>();

    public void getSubmitScore(PlayingHistoryBody body) {
        resultSubmitScore = playingHistoryRepository.getSubmitScore(body);
    }

    public LiveData<PlayingHistoryResponse> getResultSubmitScore() {
        return resultSubmitScore;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        playingHistoryRepository.resetInstance();
    }
}