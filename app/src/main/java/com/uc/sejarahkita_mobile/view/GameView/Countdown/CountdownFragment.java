package com.uc.sejarahkita_mobile.view.GameView.Countdown;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.uc.sejarahkita_mobile.R;
import com.uc.sejarahkita_mobile.helper.GameType;

public class CountdownFragment extends Fragment {

    TextView lbl_match_game_loading_fragment, lbl_countdown_game_loading_fragment;
    Button btn_cancel_game_loading_fragment;
    CountDownTimer countDownTimer;
    int gameType;

    public CountdownFragment() {
    }

    public static CountdownFragment newInstance() {
        CountdownFragment fragment = new CountdownFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_countdown, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lbl_match_game_loading_fragment = view.findViewById(R.id.lbl_match_game_loading_fragment);
        lbl_countdown_game_loading_fragment = view.findViewById(R.id.lbl_countdown_game_loading_fragment);
        btn_cancel_game_loading_fragment = view.findViewById(R.id.btn_cancel_game_loading_fragment);

        //* Menampilkan nama Match sesuai kolom "id_level" berdasarkan tombol yang diklik User
        gameType = getArguments().getInt("GameTypeArgument");
        if (gameType == GameType.CASUAL) {
            lbl_match_game_loading_fragment.setText("Casual Match");
        } else if (gameType == GameType.EASY) {
            lbl_match_game_loading_fragment.setText("Easy Match");
        } else {
            lbl_match_game_loading_fragment.setText("Hard Match");
        }

        initAction();
        startCountdownTimer();
    }

    //* Mengarahkan ke Match sesuai kolom "id_level" berdasarkan tombol yang diklik User
    public void goToPlayingGame(View view, int gameType) {
        Bundle bundle = new Bundle();
        bundle.putInt("GameTypeArgument", gameType);
        Navigation.findNavController(view).navigate(R.id.action_countdownFragment_to_playingGameBaseFragment, bundle);
    }

    //* Mengembalikan ke GameFragment ketika Button 'Cancel' diklik
    public void initAction() {
        btn_cancel_game_loading_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });
    }

    //* Mengatur durasi Countdown agar tampil selama 3 detik
    public void startCountdownTimer() {
        countDownTimer = new CountDownTimer(3000, 1000) {
            //? Menghitung mundur detik dari 3 hingga menjadi 1
            @Override
            public void onTick(long l) {
                String seconds = String.valueOf((l / 1000) + 1);
                lbl_countdown_game_loading_fragment.setText(seconds);
            }

            //? Mengarahkan ke Match yang dipilih User setelah Countdown selesai
            @Override
            public void onFinish() {
                goToPlayingGame(lbl_countdown_game_loading_fragment, gameType);
            }
        }.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        countDownTimer.cancel();
    }

    @Override
    public void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }

    @Override
    public void onStop() {
        super.onStop();
        countDownTimer.cancel();
    }
}