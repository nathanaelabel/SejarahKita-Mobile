package com.uc.sejarahkita_mobile.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.uc.sejarahkita_mobile.model.Question;
import com.uc.sejarahkita_mobile.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameRepository {
    private static GameRepository gameRepository;
    private RetrofitService apiService;
    private static final String TAG = "QuestionRepository";

    private GameRepository(String token) {
        Log.d(TAG, "token: " + token);
        apiService = RetrofitService.getInstance(token);
    }

    public static GameRepository getInstance(String token) {
        if (gameRepository == null) {
            gameRepository = new GameRepository(token);
        }

        return gameRepository;
    }

    public synchronized void resetInstance() {
        if (gameRepository != null) {
            gameRepository = null;
        }
    }

    public MutableLiveData<Question> getQuestion(int id_level) {
        MutableLiveData<Question> question = new MutableLiveData<>();
        apiService.question(id_level).enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        question.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        return question;
    }
}
