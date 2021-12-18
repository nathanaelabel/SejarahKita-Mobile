package com.uc.sejarahkita_mobile.view.GameView.Countdown;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.uc.sejarahkita_mobile.R;
import com.uc.sejarahkita_mobile.helper.GameType;

public class CountdownFragment extends Fragment {

    TextView lbl_mode_game_loading_fragment, lbl_countdown_game_loading_fragment;
    Button btn_game_loading_fragment;

    public CountdownFragment() {
    }

    public static CountdownFragment newInstance() {
        CountdownFragment fragment = new CountdownFragment();
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
        return inflater.inflate(R.layout.fragment_countdown, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lbl_mode_game_loading_fragment = view.findViewById(R.id.lbl_mode_game_loading_fragment);
        lbl_countdown_game_loading_fragment = view.findViewById(R.id.lbl_countdown_game_loading_fragment);
        btn_game_loading_fragment = view.findViewById(R.id.btn_game_loading_fragment);

        int gameType = getArguments().getInt("GameTypeArgument");
        if (gameType == GameType.CASUAL) {
            lbl_mode_game_loading_fragment.setText("Casual Match");
        } else if (gameType == GameType.EASY) {
            lbl_mode_game_loading_fragment.setText("Easy Match");
        } else {
            lbl_mode_game_loading_fragment.setText("Hard Match");
        }

        initAction();
    }

    public void initAction() {
        btn_game_loading_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });
    }
}