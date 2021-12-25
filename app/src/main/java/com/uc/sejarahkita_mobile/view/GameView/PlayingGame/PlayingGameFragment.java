package com.uc.sejarahkita_mobile.view.GameView.PlayingGame;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.textfield.TextInputLayout;
import com.uc.sejarahkita_mobile.R;
import com.uc.sejarahkita_mobile.helper.PlayingGameListener;
import com.uc.sejarahkita_mobile.helper.SharedPreferenceHelper;
import com.uc.sejarahkita_mobile.model.Question;

import java.util.Random;

public class PlayingGameFragment extends Fragment {
    Button btn_exit_playing_game_fragment, btn_jawab_playing_game_fragment, btn_lanjut_show_answer_layout;
    LinearLayout linearLayout_nyawa_ranked_playing_game_fragment;
    RatingBar rb_nyawa_ranked_playing_game_fragment;
    TextView btn_show_answer_casual_playing_game_fragment, lbl_pertanyaan_playing_game_fragment, lbl_anagram_playing_game_fragment, lbl_jawaban_show_answer_casual_layout;
    TextInputLayout til_jawaban_playing_game_fragment;
    EditText et_jawaban_playing_game_fragment;
    ConstraintLayout bottomSheetLayout;
    ImageView btn_close_show_answer_casual_layout;
    BottomSheetBehavior bottomSheetBehavior;

    Question.QuestionItem questionItem;
    int gameType;
    int life;
    int page;
    String answer;
    private GameViewModel gameViewModel;
    private SharedPreferenceHelper helper;
    PlayingGameListener playingGameListener;

    public void setPlayingGameListener(PlayingGameListener playingGameListener) {
        this.playingGameListener = playingGameListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playing_game, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_exit_playing_game_fragment = view.findViewById(R.id.btn_exit_playing_game_fragment);
        btn_jawab_playing_game_fragment = view.findViewById(R.id.btn_jawab_playing_game_fragment);
        linearLayout_nyawa_ranked_playing_game_fragment = view.findViewById(R.id.linearLayout_nyawa_ranked_playing_game_fragment);
        rb_nyawa_ranked_playing_game_fragment = view.findViewById(R.id.rb_nyawa_ranked_playing_game_fragment);
        btn_show_answer_casual_playing_game_fragment = view.findViewById(R.id.btn_show_answer_casual_playing_game_fragment);
        lbl_pertanyaan_playing_game_fragment = view.findViewById(R.id.lbl_pertanyaan_playing_game_fragment);
        lbl_anagram_playing_game_fragment = view.findViewById(R.id.lbl_anagram_playing_game_fragment);
        lbl_jawaban_show_answer_casual_layout = view.findViewById(R.id.lbl_jawaban_show_answer_casual_layout);
        til_jawaban_playing_game_fragment = view.findViewById(R.id.til_jawaban_playing_game_fragment);
        et_jawaban_playing_game_fragment = view.findViewById(R.id.et_jawaban_playing_game_fragment);
        btn_close_show_answer_casual_layout = view.findViewById(R.id.btn_close_show_answer_casual_layout);
        btn_lanjut_show_answer_layout = view.findViewById(R.id.btn_lanjut_show_answer_layout);
        bottomSheetLayout = view.findViewById(R.id.bottomSheetLayout);

        questionItem = getArguments().getParcelable("questionItem");
        gameType = getArguments().getInt("gameType");
        page = getArguments().getInt("page");
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);

        showQuestionItem();

        //* Mengatur jumlah awal Nyawa pada Ranked Mode
        if (gameType == 2) {
            life = 5;
            rb_nyawa_ranked_playing_game_fragment.setNumStars(5);
        } else if (gameType == 3) {
            life = 3;
            rb_nyawa_ranked_playing_game_fragment.setNumStars(3);
        }

        //* Untuk Exit Game
        btn_exit_playing_game_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NavDirections action = PlayingGameFragmentDirections.actionPlayingGameFragmentToGameFragment();
//                Navigation.findNavController(view).navigate(action);
                playingGameListener.onExitClicked();
            }
        });

        //* Tampilkan Button 'Show Answer' / Nyawa berdasarkan Mode
        //? Jika Casual, sembunyikan Nyawa + tampilkan Button 'Show Answer'
        if (gameType == 1) {
            btn_show_answer_casual_playing_game_fragment.setVisibility(View.VISIBLE);
            rb_nyawa_ranked_playing_game_fragment.setVisibility(View.GONE);
            //? Jika Ranked, sembunyikan Button 'Show Answer' + tampilkan Nyawa
        } else {
            btn_show_answer_casual_playing_game_fragment.setVisibility(View.GONE);
            rb_nyawa_ranked_playing_game_fragment.setVisibility(View.VISIBLE);
        }

        //* Ketika Button 'Show Answer' diklik, maka tampilkan Modal Bottom Sheet
        btn_show_answer_casual_playing_game_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(bottomSheetBehavior.STATE_COLLAPSED);
                } else {
                    bottomSheetBehavior.setState(bottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });

        //* Menutup Modal Bottom Sheet melalui Button Silang
        btn_close_show_answer_casual_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(bottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        //* Menutup Modal Bottom Sheet melalui Button 'Lanjut Bermain'
        btn_lanjut_show_answer_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(bottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        //* States dari Modal Bottom Sheet pada kondisi sembunyi saat tidak diklik + ketika terbuka + tertutup (swipeable)
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_EXPANDED:
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        //* Untuk mengirim jawaban sesuai input User
        btn_jawab_playing_game_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                if (answer.equalsIgnoreCase(et_jawaban_playing_game_fragment.getText().toString())) {
                    playingGameListener.onSubmitClicked(page + 1);
                } else {
                    calculateAnswer(life);
                }
            }
        });
    }

    //* Menampilkan Bank Soal pada User
    public void showQuestionItem() {
        lbl_pertanyaan_playing_game_fragment.setText(questionItem.getPertanyaan_kalimat());
        lbl_anagram_playing_game_fragment.setText(getAnagram(questionItem.getKunci_jawaban()));
        answer = questionItem.getKunci_jawaban();
    }

    //* Melakukan randomize kolom "kunci_jawaban" menjadi Anagram
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

    //* Mengurangi jumlah Nyawa setiap salah menjawab
    public void calculateAnswer(int nyawa) {
        life = nyawa - 1;
        rb_nyawa_ranked_playing_game_fragment.setNumStars(life);
    }

    //* Menutup keyboard setelah Button 'Jawab' diklik
    public void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }
}