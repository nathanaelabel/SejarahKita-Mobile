package com.uc.sejarahkita_mobile.view.LeaderboardView.DetailLeaderboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uc.sejarahkita_mobile.R;
import com.uc.sejarahkita_mobile.helper.SharedPreferenceHelper;
import com.uc.sejarahkita_mobile.model.Leaderboard;
import com.uc.sejarahkita_mobile.view.LeaderboardView.LeaderboardAdapter;

import java.util.ArrayList;
import java.util.List;

public class DetailLeaderboardFragment extends Fragment {

    private DetailLeaderboardViewModel detailLeaderboardView;
    private LeaderboardAdapter leaderboardAdapter;
    private RecyclerView rv_detail_leaderboard_fragment;
    private SharedPreferenceHelper helper;

    List<Leaderboard.Leaderboards> results = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;

    public DetailLeaderboardFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_leaderboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_detail_leaderboard_fragment = view.findViewById(R.id.rv_detail_leaderboard_fragment);
        helper = SharedPreferenceHelper.getInstance(requireActivity());
        detailLeaderboardView = new ViewModelProvider(getActivity()).get(DetailLeaderboardViewModel.class);
        detailLeaderboardView.init(helper.getAccessToken());
        detailLeaderboardView.getLeaderboards();
        detailLeaderboardView.getResultLeaderboards().observe(getActivity(), showLeaderboard);
    }

    private Observer<Leaderboard> showLeaderboard = new Observer<Leaderboard>() {
        @Override
        public void onChanged(Leaderboard leaderboard) {
            results = leaderboard.getLeaderboards();
            linearLayoutManager = new LinearLayoutManager(getActivity());
            rv_detail_leaderboard_fragment.setLayoutManager(linearLayoutManager);
            leaderboardAdapter = new LeaderboardAdapter(getActivity());
            leaderboardAdapter.setLeaderboardList(results);
            rv_detail_leaderboard_fragment.setAdapter(leaderboardAdapter);
        }
    };

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().getViewModelStore().clear();
    }
}