package com.uc.sejarahkita_mobile.view.GameView.PlayingGame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.uc.sejarahkita_mobile.R;
import com.uc.sejarahkita_mobile.helper.SharedPreferenceHelper;
import com.uc.sejarahkita_mobile.model.Question;

public class PlayingGameBaseFragment extends Fragment {
    int gameType;
    private GameViewModel gameViewModel;
    private SharedPreferenceHelper helper;

    public PlayingGameBaseFragment() {
    }

    public static PlayingGameBaseFragment newInstance(String param1, String param2) {
        PlayingGameBaseFragment fragment = new PlayingGameBaseFragment();
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
        return inflater.inflate(R.layout.fragment_playing_game_base, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gameType = getArguments().getInt("GameTypeArgument");
        gameViewModel = new ViewModelProvider(getActivity()).get(GameViewModel.class);
        gameViewModel.init(helper.getAccessToken());
        gameViewModel.getQuestion(gameType);
        gameViewModel.getResultQuestion().observe(getActivity(), showQuestion);
    }

    private Observer<Question> showQuestion= new Observer<Question>() {
        @Override
        public void onChanged(Question question) {
//            lbl_pertanyaan_playing_game_fragment.setText(question.getPertanyaan_kalimat());
        }
    };
}