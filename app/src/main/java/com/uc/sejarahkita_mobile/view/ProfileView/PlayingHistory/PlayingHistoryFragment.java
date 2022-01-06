package com.uc.sejarahkita_mobile.view.ProfileView.PlayingHistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uc.sejarahkita_mobile.R;
import com.uc.sejarahkita_mobile.helper.SharedPreferenceHelper;
import com.uc.sejarahkita_mobile.model.PlayingHistory;

import java.util.ArrayList;
import java.util.List;

public class PlayingHistoryFragment extends Fragment {
    Toolbar toolbar_playing_history_fragment;

    private PlayingHistoryViewModel playingHistoryViewModel;
    private PlayingHistoryAdapter playingHistoryAdapter;
    private RecyclerView rv_playing_history_fragment;
    private LinearLayout llEmpty;
    private SharedPreferenceHelper helper;

    public PlayingHistoryFragment() {
    }

    public static PlayingHistoryFragment newInstance(String param1, String param2) {
        PlayingHistoryFragment fragment = new PlayingHistoryFragment();
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
        return inflater.inflate(R.layout.fragment_playing_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_playing_history_fragment = view.findViewById(R.id.rv_playing_history_fragment);
        helper = SharedPreferenceHelper.getInstance(requireActivity());
        playingHistoryViewModel = new ViewModelProvider(getActivity()).get(PlayingHistoryViewModel.class);
        playingHistoryViewModel.init(helper.getAccessToken());
        rv_playing_history_fragment.setVisibility(View.GONE);
        llEmpty = view.findViewById(R.id.ll_empty);
        llEmpty.setVisibility(View.VISIBLE);

        playingHistoryViewModel.getPlayingHistories(helper.getId());
        playingHistoryViewModel.getResultPlayingHistories().observe(getActivity(), showPlayingHistory);
        toolbar_playing_history_fragment = view.findViewById(R.id.toolbar_playing_history_fragment);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar_playing_history_fragment);
        toolbar_playing_history_fragment.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = PlayingHistoryFragmentDirections.actionPlayingHistoryFragmentToProfileFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    List<PlayingHistory.Playinghistories> results = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;

    private Observer<PlayingHistory> showPlayingHistory = new Observer<PlayingHistory>() {
        @Override
        public void onChanged(PlayingHistory playingHistory) {
            results = playingHistory.getPlayinghistories();
            if (results.size() > 0) {
                llEmpty.setVisibility(View.GONE);
                rv_playing_history_fragment.setVisibility(View.VISIBLE);
                linearLayoutManager = new LinearLayoutManager(getActivity());
                rv_playing_history_fragment.setLayoutManager(linearLayoutManager);
                playingHistoryAdapter = new PlayingHistoryAdapter(getActivity());
                playingHistoryAdapter.setPlayingHistoryList(results);
                rv_playing_history_fragment.setAdapter(playingHistoryAdapter);
            }
        }
    };

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().getViewModelStore().clear();
    }
}