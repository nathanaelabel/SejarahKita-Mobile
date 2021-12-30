package com.uc.sejarahkita_mobile.view.LeaderboardView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.uc.sejarahkita_mobile.R;
import com.uc.sejarahkita_mobile.helper.TimeUtils;
import com.uc.sejarahkita_mobile.model.Leaderboard;

import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.CardViewViewHolder> {
    private Context context;
    private List<Leaderboard.Leaderboards> leaderboardList;

    public LeaderboardAdapter(Context context) {
        this.context = context;
    }

    public List<Leaderboard.Leaderboards> getLeaderboardList() {
        return leaderboardList;
    }

    public void setLeaderboardList(List<Leaderboard.Leaderboards> leaderboardList) {
        this.leaderboardList = leaderboardList;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_playing_history, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        final Leaderboard.Leaderboards results = getLeaderboardList().get(position);
        holder.txt_nomor_detail_leaderboard_fragment.setText(String.valueOf(results.getId_leaderboard()));
        String createdAt = TimeUtils.getNewDateFormat("yyyy-MM-dd'T'HH:mm:ss.'000000Z'", "dd/MM/yyyy", results.getCreated_at());
        holder.txt_username_detail_leaderboard_fragment.setText(createdAt);
//        holder.txt_ranked_point_terkini_profile_fragment.setText(String.valueOf(results.getSkor()));
    }

    @Override
    public int getItemCount() {
        return leaderboardList.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView txt_nomor_detail_leaderboard_fragment, txt_username_detail_leaderboard_fragment, txt_ranked_point_terkini_profile_fragment;
        CardView cardView;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_nomor_detail_leaderboard_fragment = itemView.findViewById(R.id.txt_nomor_detail_leaderboard_fragment);
            txt_username_detail_leaderboard_fragment = itemView.findViewById(R.id.txt_username_detail_leaderboard_fragment);
            txt_ranked_point_terkini_profile_fragment = itemView.findViewById(R.id.txt_ranked_point_terkini_profile_fragment);
            cardView = itemView.findViewById(R.id.cv_playing_history_fragment);
        }
    }
}
