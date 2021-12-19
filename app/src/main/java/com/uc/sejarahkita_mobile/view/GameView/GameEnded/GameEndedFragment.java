package com.uc.sejarahkita_mobile.view.GameView.GameEnded;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.uc.sejarahkita_mobile.R;

public class GameEndedFragment extends Fragment {

    TextView lbl_title_game_ended_layout, lbl_total_skor_game_ended_layout, btn_lihat_leaderboard_game_ended_layout;
    Button btn_main_lagi_game_ended_layout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_ended, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lbl_title_game_ended_layout = view.findViewById(R.id.lbl_title_game_ended_layout);
        lbl_total_skor_game_ended_layout = view.findViewById(R.id.lbl_total_skor_game_ended_layout);
        btn_lihat_leaderboard_game_ended_layout = view.findViewById(R.id.btn_lihat_leaderboard_game_ended_layout);
        btn_main_lagi_game_ended_layout = view.findViewById(R.id.btn_main_lagi_game_ended_layout);

        btn_lihat_leaderboard_game_ended_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = GameEndedFragmentDirections.actionGameEndedFragmentToLeaderboardFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });

//        btn_main_lagi_game_ended_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavDirections action = GameEndedFragmentDirections.actionGameEndedFragmentToLeaderboardFragment();
//                Navigation.findNavController(view).navigate(action);
//            }
//        });
    }
}
