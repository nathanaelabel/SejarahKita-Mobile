package com.uc.sejarahkita_mobile.view.GameView.ScoreResult;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.uc.sejarahkita_mobile.R;
import com.uc.sejarahkita_mobile.helper.SharedPreferenceHelper;
import com.uc.sejarahkita_mobile.model.body.PlayingHistoryBody;
import com.uc.sejarahkita_mobile.model.response.PlayingHistoryResponse;
import com.uc.sejarahkita_mobile.view.GameView.PlayingGame.GameViewModel;
import com.uc.sejarahkita_mobile.view.ProfileView.PlayingHistory.PlayingHistoryViewModel;

public class ScoreResultFragment extends Fragment {
    private static final String TAG = "ScoreResult";
    TextView lbl_title_game_ended_layout, lbl_total_skor_game_ended_layout, btn_lihat_leaderboard_game_ended_layout;
    Button btn_main_lagi_game_ended_layout;

    private PlayingHistoryViewModel playingHistoryViewModel;
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

        PlayingHistoryBody body = new PlayingHistoryBody();
        body.setIdStudent(helper.getId());
        body.setIdLevel(gameType + "");
        body.setSkor(skor + "");
        body.setRankedPoint(skor + "");
        Log.d(TAG, "onViewCreated: " + body.toString());

        lbl_title_game_ended_layout = view.findViewById(R.id.lbl_title_game_ended_layout);
        lbl_total_skor_game_ended_layout = view.findViewById(R.id.lbl_total_skor_game_ended_layout);
        btn_lihat_leaderboard_game_ended_layout = view.findViewById(R.id.btn_lihat_leaderboard_game_ended_layout);
        btn_main_lagi_game_ended_layout = view.findViewById(R.id.btn_main_lagi_game_ended_layout);

        playingHistoryViewModel = new ViewModelProvider(getActivity()).get(PlayingHistoryViewModel.class);
        playingHistoryViewModel.init(helper.getAccessToken());
        playingHistoryViewModel.getSubmitScore(body);
        playingHistoryViewModel.getResultSubmitScore().observe(getActivity(), new Observer<PlayingHistoryResponse>() {
            @Override
            public void onChanged(PlayingHistoryResponse playingHistoryResponse) {
                if (playingHistoryResponse.isStatus()) {
                    Toast.makeText(requireActivity(), "Data Sukses Disimpan", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireActivity(), "Data Gagal Disimpan", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
