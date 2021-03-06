package com.uc.sejarahkita_mobile.view.ProfileView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.uc.sejarahkita_mobile.R;
import com.uc.sejarahkita_mobile.helper.SharedPreferenceHelper;
import com.uc.sejarahkita_mobile.helper.TimeUtils;
import com.uc.sejarahkita_mobile.model.Profile;
import com.uc.sejarahkita_mobile.model.response.rankedPointTerkini.EasyItem;
import com.uc.sejarahkita_mobile.model.response.rankedPointTerkini.HardItem;
import com.uc.sejarahkita_mobile.view.LeaderboardView.LeaderboardViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {
    Button btn_playing_history_profile_fragment, btn_logout_profile_fragment;
    TextView lbl_register_since_profile_fragment, lbl_username_profile_fragment, lbl_name_profile_fragment, lbl_email_profile_fragment,
            lbl_school_profile_fragment, lbl_city_profile_fragment, lbl_birthyear_profile_fragment,
            lbl_easy_ranked_point_profile_fragment, lbl_hard_ranked_point_profile_fragment;
    Profile.Students students;

    private ProfileViewModel profileViewModel;
    private LeaderboardViewModel leaderboardViewModel;
    private SharedPreferenceHelper helper;
    private static final String TAG = "ProfileFragment";

    List<EasyItem> easyItem = new ArrayList<>();
    List<HardItem> hardItem = new ArrayList<>();

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        profileViewModel = new ViewModelProvider(getActivity()).get(ProfileViewModel.class);
        leaderboardViewModel = new ViewModelProvider(getActivity()).get(LeaderboardViewModel.class);

        profileViewModel.init(helper.getAccessToken());
        leaderboardViewModel.init(helper.getAccessToken());

        lbl_register_since_profile_fragment = view.findViewById(R.id.lbl_register_since_profile_fragment);
        lbl_username_profile_fragment = view.findViewById(R.id.lbl_username_profile_fragment);
        lbl_name_profile_fragment = view.findViewById(R.id.lbl_name_profile_fragment);
        lbl_email_profile_fragment = view.findViewById(R.id.lbl_email_profile_fragment);
        lbl_school_profile_fragment = view.findViewById(R.id.lbl_school_profile_fragment);
        lbl_city_profile_fragment = view.findViewById(R.id.lbl_city_profile_fragment);
        lbl_birthyear_profile_fragment = view.findViewById(R.id.lbl_birthyear_profile_fragment);
        lbl_easy_ranked_point_profile_fragment = view.findViewById(R.id.lbl_easy_ranked_point_profile_fragment);
        lbl_hard_ranked_point_profile_fragment = view.findViewById(R.id.lbl_hard_ranked_point_profile_fragment);
        btn_playing_history_profile_fragment = view.findViewById(R.id.btn_playing_history_profile_fragment);
        btn_logout_profile_fragment = view.findViewById(R.id.btn_logout_profile_fragment);

        profileViewModel.getProfile(helper.getId());
        profileViewModel.getResultProfiles().observe(getActivity(), showProfile);

        leaderboardViewModel.setTerkini(helper.getId());
        leaderboardViewModel.getTerkini().observe(getActivity(), response -> {
            easyItem = response.getEasy();
            hardItem = response.getHard();

            if (!response.getEasy().isEmpty()) {
                lbl_easy_ranked_point_profile_fragment.setText(String.valueOf(easyItem.get(0).getRankedPoint()) + " RP");
            } else {
                lbl_easy_ranked_point_profile_fragment.setText("0 RP");
            }

            if (!response.getHard().isEmpty()) {
                lbl_hard_ranked_point_profile_fragment.setText(String.valueOf(hardItem.get(0).getRankedPoint()) + " RP");
            } else {
                lbl_hard_ranked_point_profile_fragment.setText("0 RP");
            }
        });

        btn_playing_history_profile_fragment.setOnClickListener(view12 -> {
            NavDirections action = ProfileFragmentDirections.actionProfileFragmentToPlayingHistoryFragment();
            Navigation.findNavController(view12).navigate(action);
        });

        btn_logout_profile_fragment.setOnClickListener(view1 -> {
            profileViewModel.logout().observe(requireActivity(), s -> {
                if (!s.isEmpty()) {
                    helper.clearPref();
                    NavDirections actions = ProfileFragmentDirections.actionProfileFragmentToLoginFragment();
                    Navigation.findNavController(view1).navigate(actions);
                    Toast.makeText(requireActivity(), s, Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private Observer<Profile> showProfile = new Observer<Profile>() {
        @Override
        public void onChanged(Profile profile) {
            if (!profile.getStudents().isEmpty()) {
                students = profile.getStudents().get(0);
                lbl_username_profile_fragment.setText(students.getUsername());
                lbl_name_profile_fragment.setText(students.getName());
                lbl_email_profile_fragment.setText(students.getEmail());
                lbl_school_profile_fragment.setText(students.getSchool());
                lbl_city_profile_fragment.setText(students.getCity());
                lbl_birthyear_profile_fragment.setText(String.valueOf(students.getBirthyear()));
                String registerSince = TimeUtils.getNewDateFormat("yyyy-MM-dd'T'HH:mm:ss.'000000Z'",
                        "'Register: 'dd/MM/yyyy", students.getCreated_at());
                lbl_register_since_profile_fragment.setText(registerSince);
            }
        }
    };

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().getViewModelStore().clear();
    }
}