package com.uc.sejarahkita_mobile.view.RegisterView;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.uc.sejarahkita_mobile.model.RegisterResponse;
import com.uc.sejarahkita_mobile.repositories.AuthRepository;

public class RegisterViewModel extends AndroidViewModel {
    private AuthRepository authRepository;
    private static final String TAG = "RegisterViewModel";

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        authRepository = AuthRepository.getInstance();
    }

    public MutableLiveData<RegisterResponse> register(String name, String email, String password, String password_confirmation) {
        return authRepository.register(name, email, password, password_confirmation);
    }
}