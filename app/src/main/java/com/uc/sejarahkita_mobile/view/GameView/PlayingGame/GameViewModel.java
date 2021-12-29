package com.uc.sejarahkita_mobile.view.GameView.PlayingGame;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.uc.sejarahkita_mobile.model.Question;
import com.uc.sejarahkita_mobile.repositories.GameRepository;

public class GameViewModel extends AndroidViewModel {
    private GameRepository gameRepository;
    private static final String TAG = "GameViewModel";

    public GameViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token) {
        Log.d(TAG, "init: " + token);
        gameRepository = GameRepository.getInstance(token);
    }

    //* Question
    private MutableLiveData<Question> resultQuestion = new MutableLiveData<>();

    public void getQuestion(int id_level) {
        resultQuestion = gameRepository.getQuestion(id_level);
    }

    public LiveData<Question> getResultQuestion() {
        return resultQuestion;
    }

    //* Check Answer
    private MutableLiveData<String> resultCheckAnswer = new MutableLiveData<>();

    public void getCheckAnswer(String id, String inputJawaban) {
        resultCheckAnswer = gameRepository.getCheckAnswer(id, inputJawaban);
    }

    public LiveData<String> getResultCheckAnswer() {
        return resultCheckAnswer;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        gameRepository.resetInstance();
    }
}
