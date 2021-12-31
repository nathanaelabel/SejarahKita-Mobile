package com.uc.sejarahkita_mobile.view.GameView.ScoreResult;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.uc.sejarahkita_mobile.R;
import com.uc.sejarahkita_mobile.helper.SharedPreferenceHelper;

public class ScoreResultFragment extends Fragment {
    TextView lbl_title_game_ended_layout, lbl_total_skor_game_ended_layout, btn_lihat_leaderboard_game_ended_layout;
    Button btn_main_lagi_game_ended_layout;

    private SharedPreferenceHelper helper;

    int gameType;
    int skor = 0;
    Boolean isGameOver;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score_result, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        helper = SharedPreferenceHelper.getInstance(requireActivity());
        gameType = getArguments().getInt("GameTypeArgument");
        skor = getArguments().getInt("skor");
        isGameOver = getArguments().getBoolean("isGameOver");

        Toast.makeText(requireActivity(), gameType + "_" + skor + "_" + helper.getId(), Toast.LENGTH_SHORT).show();
        lbl_title_game_ended_layout = view.findViewById(R.id.lbl_title_game_ended_layout);
        lbl_total_skor_game_ended_layout = view.findViewById(R.id.lbl_total_skor_game_ended_layout);
        btn_lihat_leaderboard_game_ended_layout = view.findViewById(R.id.btn_lihat_leaderboard_game_ended_layout);
        btn_main_lagi_game_ended_layout = view.findViewById(R.id.btn_main_lagi_game_ended_layout);

        if (isGameOver) {
            lbl_title_game_ended_layout.setText("Game Over");
        } else {
            lbl_title_game_ended_layout.setText("Permainan Selesai");
        }

        lbl_total_skor_game_ended_layout.setText(String.valueOf(skor));

        btn_main_lagi_game_ended_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = ScoreResultFragmentDirections.actionScoreResultFragmentToGameFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });

        btn_lihat_leaderboard_game_ended_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = ScoreResultFragmentDirections.actionScoreResultFragmentToLeaderboardFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });
    }
}
