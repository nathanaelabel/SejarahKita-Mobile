package com.uc.sejarahkita_mobile.view.GameView.PlayingGame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;
import com.uc.sejarahkita_mobile.R;

public class PlayingGameFragment extends Fragment {

    Button btn_quit_playing_game_fragment, btn_jawab_playing_game_fragment;
    LinearLayout linearLayout_nyawa_ranked_playing_game_fragment;
    TextView btn_show_answer_casual_playing_game_fragment, lbl_pertanyaan_playing_game_fragment, lbl_anagram_playing_game_fragment;
    TextInputLayout til_jawaban_playing_game_fragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playing_game, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_quit_playing_game_fragment = view.findViewById(R.id.btn_quit_playing_game_fragment);
        btn_jawab_playing_game_fragment = view.findViewById(R.id.btn_jawab_playing_game_fragment);
        linearLayout_nyawa_ranked_playing_game_fragment = view.findViewById(R.id.linearLayout_nyawa_ranked_playing_game_fragment);
        btn_show_answer_casual_playing_game_fragment = view.findViewById(R.id.btn_show_answer_casual_playing_game_fragment);
        lbl_pertanyaan_playing_game_fragment = view.findViewById(R.id.lbl_pertanyaan_playing_game_fragment);
        lbl_anagram_playing_game_fragment = view.findViewById(R.id.lbl_anagram_playing_game_fragment);
        til_jawaban_playing_game_fragment = view.findViewById(R.id.til_jawaban_playing_game_fragment);
    }


}
