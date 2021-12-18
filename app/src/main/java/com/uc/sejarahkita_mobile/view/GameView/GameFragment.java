package com.uc.sejarahkita_mobile.view.GameView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.uc.sejarahkita_mobile.R;
import com.uc.sejarahkita_mobile.helper.GameType;

public class GameFragment extends Fragment {

    CardView cv_casual_game_fragment, cv_easy_game_fragment, cv_hard_game_fragment;

    public GameFragment() {
    }

    public static GameFragment newInstance() {
        GameFragment fragment = new GameFragment();
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
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cv_casual_game_fragment = view.findViewById(R.id.cv_casual_game_fragment);
        cv_easy_game_fragment = view.findViewById(R.id.cv_easy_game_fragment);
        cv_hard_game_fragment = view.findViewById(R.id.cv_hard_game_fragment);

        initAction();
    }

    public void goToCountdown(View view, int gameType) {
        NavDirections actions = GameFragmentDirections.actionGameFragmentToCountdownFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("GameTypeArgument", gameType);
        Navigation.findNavController(view).navigate(R.id.action_gameFragment_to_countdownFragment, bundle);
    }

    public void initAction() {
        cv_casual_game_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCountdown(view, GameType.CASUAL);
            }
        });

        cv_easy_game_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCountdown(view, GameType.EASY);
            }
        });

        cv_hard_game_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCountdown(view, GameType.HARD);
            }
        });
    }
}