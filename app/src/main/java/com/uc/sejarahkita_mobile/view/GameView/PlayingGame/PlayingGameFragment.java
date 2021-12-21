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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputLayout;
import com.uc.sejarahkita_mobile.R;
import com.uc.sejarahkita_mobile.helper.SharedPreferenceHelper;
import com.uc.sejarahkita_mobile.model.Question;

import java.util.Random;

public class PlayingGameFragment extends Fragment {

    Button btn_quit_playing_game_fragment, btn_jawab_playing_game_fragment;
    LinearLayout linearLayout_nyawa_ranked_playing_game_fragment;
    TextView btn_show_answer_casual_playing_game_fragment, lbl_pertanyaan_playing_game_fragment, lbl_anagram_playing_game_fragment;
    TextInputLayout til_jawaban_playing_game_fragment;
    Question.QuestionItem questionItem;
    int gameType;
    private GameViewModel gameViewModel;
    private SharedPreferenceHelper helper;

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
//        questionItem = getArguments().getParcelable("questionItem");

        gameType = getArguments().getInt("GameTypeArgument");
        gameViewModel = new ViewModelProvider(getActivity()).get(GameViewModel.class);
        helper = SharedPreferenceHelper.getInstance(requireActivity());
        gameViewModel.init(helper.getAccessToken());
        gameViewModel.getQuestion(gameType);
        gameViewModel.getResultQuestion().observe(getActivity(), showQuestion);

        btn_quit_playing_game_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = PlayingGameFragmentDirections.actionPlayingGameFragmentToGameFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    public void showQuestionItem() {
        lbl_pertanyaan_playing_game_fragment.setText(questionItem.getPertanyaan_kalimat());
        lbl_anagram_playing_game_fragment.setText(getAnagram(questionItem.getKunci_jawaban()));
    }

    public String getAnagram(String word) {
        if (word != null && !word.isEmpty()) {
            char a[] = word.toCharArray();

            Random random = new Random();

            for (int i = 0; i < a.length; i++) {
                int j = random.nextInt(a.length);
                char tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            }
            return new String(a);
        }
        return word;
    }

    private Observer<Question> showQuestion = new Observer<Question>() {
        @Override
        public void onChanged(Question question) {
            if (!question.getQuestionItem().isEmpty()) {
                questionItem = question.getQuestionItem().get(0);
                showQuestionItem();
            }
        }
    };
}