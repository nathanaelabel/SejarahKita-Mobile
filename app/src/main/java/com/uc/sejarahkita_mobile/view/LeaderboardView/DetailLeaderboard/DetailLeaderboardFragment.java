package com.uc.sejarahkita_mobile.view.LeaderboardView.DetailLeaderboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.uc.sejarahkita_mobile.model.Leaderboard;
import com.uc.sejarahkita_mobile.model.response.LeaderboardResponse;
import com.uc.sejarahkita_mobile.model.response.LeaderboardsItem;
import com.uc.sejarahkita_mobile.view.LeaderboardView.LeaderboardAdapter;

import java.util.ArrayList;
import java.util.List;

public class DetailLeaderboardFragment extends Fragment {
    Toolbar toolbar_detail_leaderboard;
    private DetailLeaderboardViewModel detailLeaderboardView;
    private LeaderboardAdapter leaderboardAdapter;
    private RecyclerView rv_detail_leaderboard_fragment;
    private SharedPreferenceHelper helper;

    List<LeaderboardsItem> results = new ArrayList<>();
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
        toolbar_detail_leaderboard = view.findViewById(R.id.toolbar_detail_leaderboard);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar_detail_leaderboard);
        toolbar_detail_leaderboard.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = DetailLeaderboardFragmentDirections.actionDetailLeaderboardFragmentToLeaderboardFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    private Observer<LeaderboardResponse> showLeaderboard = new Observer<LeaderboardResponse>() {
        @Override
        public void onChanged(LeaderboardResponse leaderboardResponse) {
            results = leaderboardResponse.getLeaderboards();
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