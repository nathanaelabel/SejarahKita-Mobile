package com.uc.sejarahkita_mobile.view.LeaderboardView.DetailLeaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.uc.sejarahkita_mobile.R;
import com.uc.sejarahkita_mobile.model.response.LeaderboardsItem;
import com.uc.sejarahkita_mobile.model.response.StudentsItem;

import java.util.ArrayList;
import java.util.List;

public class DetailLeaderboardAdapter extends RecyclerView.Adapter<DetailLeaderboardAdapter.LeaderboardViewHolder> {
    private Context context;
    private List<LeaderboardsItem> leaderboardList;
    private List<StudentsItem> students;

    public void setStudents(List<StudentsItem> students) {
        this.students = students;
    }

    public DetailLeaderboardAdapter(Context context) {
        this.context = context;
    }

    public List<LeaderboardsItem> getLeaderboardList() {
        return leaderboardList;
    }

    public void setLeaderboardList(List<LeaderboardsItem> leaderboardList) {
        List<LeaderboardsItem> header = new ArrayList<>();
//        header.add(new Leaderboard.Leaderboards());
        header.addAll(leaderboardList);
        this.leaderboardList = header;
    }

    @NonNull
    @Override
    public LeaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_detail_leaderboard, parent, false);
        return new LeaderboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardViewHolder holder, int position) {

        if (position % 2 == 0) {
            holder.linearLayoutCompat.setBackgroundColor(context.getResources().getColor(R.color.table_tbody_even));
        } else {
            holder.linearLayoutCompat.setBackgroundColor(context.getResources().getColor(R.color.table_tbody_odd));
        }
        holder.txt_nomor_detail_leaderboard_fragment.setTextColor(context.getResources().getColor(R.color.textPrimary));
        holder.txt_username_detail_leaderboard_fragment.setTextColor(context.getResources().getColor(R.color.textPrimary));
        holder.txt_ranked_point_detail_leaderboard_fragment.setTextColor(context.getResources().getColor(R.color.textPrimary));
        final LeaderboardsItem results = getLeaderboardList().get(position);
        holder.txt_nomor_detail_leaderboard_fragment.setText(String.valueOf(position + 1));
        holder.txt_ranked_point_detail_leaderboard_fragment.setText(String.valueOf(results.getRankedPoint()));
        for (int i = 0; i < students.size(); i++) {
            StudentsItem current = students.get(i);
            if (current.getId() == results.getIdStudent()) {
                holder.txt_username_detail_leaderboard_fragment.setText(current.getUsername() + "");
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return leaderboardList.size();
    }

    public class LeaderboardViewHolder extends RecyclerView.ViewHolder {
        TextView txt_nomor_detail_leaderboard_fragment, txt_username_detail_leaderboard_fragment, txt_ranked_point_detail_leaderboard_fragment;
        LinearLayoutCompat linearLayoutCompat;

        public LeaderboardViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_nomor_detail_leaderboard_fragment = itemView.findViewById(R.id.txt_nomor_detail_leaderboard_fragment);
            txt_username_detail_leaderboard_fragment = itemView.findViewById(R.id.txt_username_detail_leaderboard_fragment);
            txt_ranked_point_detail_leaderboard_fragment = itemView.findViewById(R.id.txt_ranked_point_detail_leaderboard_fragment);
            linearLayoutCompat = itemView.findViewById(R.id.linearLayoutCompat);
        }
    }
}
