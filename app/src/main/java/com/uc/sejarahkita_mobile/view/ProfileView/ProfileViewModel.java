package com.uc.sejarahkita_mobile.view.ProfileView;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.uc.sejarahkita_mobile.model.Profile;
import com.uc.sejarahkita_mobile.repositories.ProfileRepository;

public class ProfileViewModel extends AndroidViewModel {
    private ProfileRepository profileRepository;
    private static final String TAG = "ProfileViewModel";

    public ProfileViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token) {
        Log.d(TAG, "token: " + token);
        profileRepository = ProfileRepository.getInstance(token);
    }

    //* Begin of ViewModel to get user profile
    private MutableLiveData<Profile> resultProfiles = new MutableLiveData<>();
    public void getProfile(String id) {
        resultProfiles = profileRepository.getProfile(id);
    }
    public LiveData<Profile> getResultProfiles() {
        return resultProfiles;
    }

    //* Begin of ViewModel to logout user
    public LiveData<String> logout() {
        profileRepository.resetInstances();
        return profileRepository.logout();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        profileRepository.resetInstances();
    }
}