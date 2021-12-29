package com.uc.sejarahkita_mobile.view.GameView.PlayingGame;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.uc.sejarahkita_mobile.R;
import com.uc.sejarahkita_mobile.helper.Const;
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
    ImageView img_pertanyaan_path_gambar_playing_game_fragment, btn_close_show_answer_casual_layout;
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
        img_pertanyaan_path_gambar_playing_game_fragment = view.findViewById(R.id.img_pertanyaan_path_gambar_playing_game_fragment);
        btn_close_show_answer_casual_layout = view.findViewById(R.id.btn_close_show_answer_casual_layout);
        btn_lanjut_show_answer_layout = view.findViewById(R.id.btn_lanjut_show_answer_layout);
        bottomSheetLayout = view.findViewById(R.id.bottomSheetLayout);

        questionItem = getArguments().getParcelable("questionItem");
        gameType = getArguments().getInt("gameType");
        life = getArguments().getInt("LifeArgument");
        page = getArguments().getInt("page");
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);

        gameViewModel = new ViewModelProvider(getActivity()).get(GameViewModel.class);
        helper = SharedPreferenceHelper.getInstance(requireActivity());
        gameViewModel.init(helper.getAccessToken());

        showQuestionItem();

        //* Mengatur jumlah awal Nyawa pada Ranked Mode
        rb_nyawa_ranked_playing_game_fragment.setNumStars(life);

        //* Untuk Exit Game
        btn_exit_playing_game_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    lbl_jawaban_show_answer_casual_layout.setText(questionItem.getKunci_jawaban());
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
                gameViewModel.getCheckAnswer(String.valueOf(questionItem.getId_question()),
                        et_jawaban_playing_game_fragment.getText().toString());
                gameViewModel.getResultCheckAnswer().observe(getActivity(), showCheckAnswer);
                //? User diwajibkan mengisi EditText pada TextInputLayout terlebih dahulu sebelum klik Button 'Jawab'
                if (et_jawaban_playing_game_fragment.getText().toString().equals("")) {
                    //? Tampilkan validasi peringatan input kosongnya
                    til_jawaban_playing_game_fragment.setError("Silakan isi jawaban terlebih dahulu.");
                    hideKeyboard();
                } else {
                    //? Cek kecocokan input jawaban terhadap kolom "kunci_jawaban" dengan mengabaikan uppercase & lowercase
                    if (answer.equalsIgnoreCase(et_jawaban_playing_game_fragment.getText().toString())) {
                        playingGameListener.onSubmitClicked(page + 1, life);
                        hideKeyboard();
                    } else {
                        calculateAnswer(life);
                    }
                }
            }
        });
    }

    //* Menampilkan Bank Soal pada User
    public void showQuestionItem() {
        lbl_pertanyaan_playing_game_fragment.setText(questionItem.getPertanyaan_kalimat());
        lbl_anagram_playing_game_fragment.setText(getAnagram(questionItem.getKunci_jawaban()));
        answer = questionItem.getKunci_jawaban();
        if (!questionItem.getPertanyaan_path_gambar().equals("-")) {
            String URL = Const.BASE_PERTANYAAN_PATH_GAMBAR_URL + questionItem.getPertanyaan_path_gambar();
            Picasso.get().load(URL).into(img_pertanyaan_path_gambar_playing_game_fragment, new Callback() {
                @Override
                public void onSuccess() {
                    Log.e("TAG", "Success");
                }

                @Override
                public void onError(Exception e) {
                    Log.e("TAG", "onLoadFailed: " + e.getMessage());
                }
            });

//            Glide.with(requireContext()).load(URL)
//                    .listener(new RequestListener<Drawable>() {
//                        @Override
//                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                            Log.e("TAG", "onLoadFailed: " + e.getMessage());
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                            return false;
//                        }
//                    })
//                    .into(img_pertanyaan_path_gambar_playing_game_fragment);
            img_pertanyaan_path_gambar_playing_game_fragment.setVisibility(View.VISIBLE);
        } else {
            img_pertanyaan_path_gambar_playing_game_fragment.setVisibility(View.GONE);
        }
        Log.i("showQuestionItem: ", questionItem.getKunci_jawaban());
    }

    //* Melakukan randomize kolom "kunci_jawaban" menjadi Anagram
    public String getAnagram(String word) {
        if (word != null && !word.isEmpty()) {
            char a[] = word.toCharArray();

            Random random = new Random();

            for (int i = 0; i < a.length; i++) {
                int j = random.nextInt(a.length);
                char temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
            return new String(a);
        }
        //? Melakukan randomize lagi untuk mencegah hasil Anagram tidak menjadi sama dengan isi kolom "kunci_jawaban"
        if (word.equals(answer)) {
            getAnagram(word);
        }
        return word;
    }

    //* Mengurangi jumlah Nyawa setiap salah menjawab
    public void calculateAnswer(int nyawa) {
        life = nyawa - 1;
        //? Jika Nyawa habis, maka Game Over
        if (life == 0) {
            playingGameListener.onGameEnded();
        } else {
            playingGameListener.onSubmitClicked(page + 1, life);
        }
    }

    //* Menutup keyboard setelah Button 'Jawab' diklik
    public void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null)
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private Observer<String> showCheckAnswer = new Observer<String>() {
        @Override
        public void onChanged(String string) {
            //? User diwajibkan mengisi EditText pada TextInputLayout terlebih dahulu sebelum klik Button 'Jawab'
            if (et_jawaban_playing_game_fragment.getText().toString().equals("")) {
                //? Tampilkan validasi peringatan input kosongnya
                til_jawaban_playing_game_fragment.setError("Silakan isi jawaban terlebih dahulu.");
                hideKeyboard();
            } else {
                //? Cek kecocokan input jawaban terhadap kolom "kunci_jawaban" dengan mengabaikan uppercase & lowercase
                if (answer.equalsIgnoreCase(et_jawaban_playing_game_fragment.getText().toString())) {
                    playingGameListener.onSubmitClicked(page + 1, life);
                    hideKeyboard();
                } else {
                    calculateAnswer(life);
                }
            }
        }
    };
}