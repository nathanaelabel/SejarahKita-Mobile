package com.uc.sejarahkita_mobile.view.GameView.PlayingGame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.uc.sejarahkita_mobile.R;
import com.uc.sejarahkita_mobile.helper.GameType;
import com.uc.sejarahkita_mobile.helper.PlayingGameListener;
import com.uc.sejarahkita_mobile.helper.SharedPreferenceHelper;
import com.uc.sejarahkita_mobile.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayingGameBaseFragment extends Fragment implements PlayingGameListener {
    FrameLayout playingGameBaseFragmentContainer;
    CardView cv_playing_game_base_fragment;
    ProgressBar progBar_loading_playing_game_base_fragment;
    TextView txt_title_playing_game_base_fragment;

    int gameType;
    int life = 0;
    int page = 0;
    int skor = 0;
    private GameViewModel gameViewModel;
    private SharedPreferenceHelper helper;
    List<Question.QuestionItem> casualQuestions;
    List<Question.QuestionItem> easyQuestions;
    List<Question.QuestionItem> hardQuestions;

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
        cv_playing_game_base_fragment = view.findViewById(R.id.cv_playing_game_base_fragment);
        progBar_loading_playing_game_base_fragment = view.findViewById(R.id.progBar_loading_playing_game_base_fragment);
        txt_title_playing_game_base_fragment = view.findViewById(R.id.txt_title_playing_game_base_fragment);

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
            List<Question.QuestionItem> questions = question.getQuestionItem();

            switch (gameType) {
                case GameType.CASUAL:
                    casualQuestions = getFilteredQuestions(questions, gameType);
                    goToNextQuestion(casualQuestions.get(page), gameType, life);
                    break;
                case GameType.EASY:
                    life = 5;
                    easyQuestions = getFilteredQuestions(questions, gameType);
                    goToNextQuestion(easyQuestions.get(page), gameType, life);
                    break;
                case GameType.HARD:
                    life = 3;
                    hardQuestions = getFilteredQuestions(questions, gameType);
                    goToNextQuestion(hardQuestions.get(page), gameType, life);
                    break;
            }
//            List<Question.QuestionItem> questionsNew = null;

//            for (int i = 0; i < questions.size(); i++) {
//                if (questions.get(i).getId_level() == 1) {
//                    assert questionsNew != null;
//                    questionsNew.add(questions.get(i));
//                }
//            }
//            Log.i("onChanged: ", String.valueOf(questionsNew.size()));
        }
    };

    public void goToNextQuestion(Question.QuestionItem questionItem, int gameType, int life) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("questionItem", questionItem);
        bundle.putInt("gameType", gameType);
        bundle.putInt("page", page);
        bundle.putInt("LifeArgument", life);
        bundle.putInt("skor", skor);
        PlayingGameFragment fragment = new PlayingGameFragment();
        fragment.setArguments(bundle);
        fragment.setPlayingGameListener(this);
        replaceFragment(fragment);
        cv_playing_game_base_fragment.setVisibility(View.GONE);
        progBar_loading_playing_game_base_fragment.setVisibility(View.GONE);
        txt_title_playing_game_base_fragment.setVisibility(View.GONE);
    }

    public List<Question.QuestionItem> getFilteredQuestions(List<Question.QuestionItem> questions, int gameType) {
        List<Question.QuestionItem> filteredQuestions = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < questions.size(); i++) {
            int randomIndex = random.nextInt(questions.size());
            Question.QuestionItem questionItem = questions.get(randomIndex);
//            if (questionItem.getId_level() == gameType && !questionItem.getPertanyaan_path_gambar().equals("-")) {
                if (gameType == GameType.CASUAL && filteredQuestions.size() == 9)
                    break;
                filteredQuestions.add(questionItem);
            }
//        }
        return filteredQuestions;
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
    public void onSubmitClicked(int page, int life, int skor) {
        this.page = page;
        this.life = life;
        this.skor = skor;

        switch (gameType) {
            case GameType.CASUAL:
                goToNextPage(casualQuestions, gameType);
                break;
            case GameType.EASY:
                goToNextPage(easyQuestions, gameType);
                break;
            case GameType.HARD:
                goToNextPage(hardQuestions, gameType);
                break;
        }
    }

    public void goToNextPage(List<Question.QuestionItem> questions, int gameType) {
        if (page == questions.size()) {
            goToScorePage(false);
        } else {
            goToNextQuestion(questions.get(page), gameType, life);
        }
    }

    public void goToScorePage(Boolean isGameOver) {
        Bundle bundle = new Bundle();
        bundle.putInt("GameTypeArgument", gameType);
        bundle.putInt("skor", skor);
        bundle.putBoolean("isGameOver", isGameOver);
        Navigation.findNavController(playingGameBaseFragmentContainer).navigate(R.id.action_playingGameBaseFragment_to_scoreResultFragment, bundle);
    }

    @Override
    public void onGameEnded() {
        goToScorePage(true);
    }
}