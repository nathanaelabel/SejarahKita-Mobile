package com.uc.sejarahkita_mobile.view.GameView.PlayingGame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.uc.sejarahkita_mobile.R;
import com.uc.sejarahkita_mobile.helper.PlayingGameListener;
import com.uc.sejarahkita_mobile.helper.SharedPreferenceHelper;
import com.uc.sejarahkita_mobile.model.Question;

import java.util.List;

public class PlayingGameBaseFragment extends Fragment implements PlayingGameListener {
    FrameLayout playingGameBaseFragmentContainer;
    ProgressBar progBar_loading_playing_game_base_fragment;

    int gameType;
    private GameViewModel gameViewModel;
    private SharedPreferenceHelper helper;
    List<Question.QuestionItem> questions;
    int page = 0;

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

        playingGameBaseFragmentContainer = view.findViewById(R.id.playingGameBaseFragmentContainer);
        progBar_loading_playing_game_base_fragment = view.findViewById(R.id.progBar_loading_playing_game_base_fragment);

        gameType = getArguments().getInt("GameTypeArgument");
        gameViewModel = new ViewModelProvider(getActivity()).get(GameViewModel.class);
        helper = SharedPreferenceHelper.getInstance(requireActivity());
        gameViewModel.init(helper.getAccessToken());
        gameViewModel.getQuestion(gameType);
        gameViewModel.getResultQuestion().observe(getActivity(), showQuestion);
    }

    //* Mendapatkan Bank Soal & mengkategorikannya sesuai kolom "id_level" pada setiap Match
    private Observer<Question> showQuestion = new Observer<Question>() {
        @Override
        public void onChanged(Question question) {
            questions = question.getQuestionItem();
            goToNextQuestion(questions.get(page), gameType);
        }
    };

    public void goToNextQuestion(Question.QuestionItem questionItem, int gameType) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("questionItem", questionItem);
        bundle.putInt("gameType", gameType);
        bundle.putInt("page", page);
        PlayingGameFragment fragment = new PlayingGameFragment();
        fragment.setArguments(bundle);
        fragment.setPlayingGameListener(this);
        replaceFragment(fragment);
        progBar_loading_playing_game_base_fragment.setVisibility(View.GONE);
    }

    public void replaceFragment(Fragment fragment) {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.playingGameBaseFragmentContainer, fragment)
                .commit();
    }

    @Override
    public void onExitClicked() {
        Navigation.findNavController(playingGameBaseFragmentContainer).navigate(R.id.action_playingGameBaseFragment_to_gameFragment);
    }

    @Override
    public void onSubmitClicked(int page) {
        this.page = page;
        goToNextQuestion(questions.get(page), gameType);
    }
}