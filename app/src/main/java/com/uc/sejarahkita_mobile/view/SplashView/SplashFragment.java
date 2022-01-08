package com.uc.sejarahkita_mobile.view.SplashView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.uc.sejarahkita_mobile.R;
import com.uc.sejarahkita_mobile.helper.SharedPreferenceHelper;

public class SplashFragment extends Fragment {
    LinearLayout linearLayout_splash_screen_fragment;
    TextView txt_app_version_splash_screen_fragment;

    private static int splashtime = 3000;
    private static final String TAG = "SplashFragment";

    Animation bottomAnimation;

    public SplashFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferenceHelper helper = SharedPreferenceHelper.getInstance(requireActivity());

        bottomAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_animation);

        linearLayout_splash_screen_fragment = view.findViewById(R.id.linearLayout_splash_screen_fragment);
        txt_app_version_splash_screen_fragment = view.findViewById(R.id.txt_app_version_splash_screen_fragment);

        linearLayout_splash_screen_fragment.setAnimation(bottomAnimation);
        txt_app_version_splash_screen_fragment.setAnimation(bottomAnimation);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            NavDirections action;
            if (helper.getAccessToken().isEmpty()) {
                action = SplashFragmentDirections.actionSplashFragmentToLoginFragment();
            } else {
                action = SplashFragmentDirections.actionSplashFragmentToGameFragment();
            }
            Navigation.findNavController(view).navigate(action);
        }, splashtime);
    }
}