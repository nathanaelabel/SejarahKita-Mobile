package com.uc.sejarahkita_mobile.view.ProfileView.PlayingHistory;

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
import com.uc.sejarahkita_mobile.model.PlayingHistory;

import java.util.List;

public class PlayingHistoryAdapter extends RecyclerView.Adapter<PlayingHistoryAdapter.CardViewViewHolder> {
    private Context context;
    private List<PlayingHistory.Playinghistories> playingHistoryList;

    public PlayingHistoryAdapter(Context context) {
        this.context = context;
    }

    public List<PlayingHistory.Playinghistories> getPlayingHistoryList() {
        return playingHistoryList;
    }

    public void setPlayingHistoryList(List<PlayingHistory.Playinghistories> playingHistoryList) {
        this.playingHistoryList = playingHistoryList;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_playing_history, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        final PlayingHistory.Playinghistories results = getPlayingHistoryList().get(position);
        holder.lbl_id_level_playing_history.setText(String.valueOf(results.getId_playing_history()));
        String createdAt = TimeUtils.getNewDateFormat("yyyy-MM-dd'T'HH:mm:ss.'000000Z'", "dd/MM/yyyy", results.getCreated_at());
        holder.lbl_waktu_bermain_playing_history_fragment.setText(createdAt);
        holder.lbl_skor_playing_history_fragment.setText(String.valueOf(results.getSkor()));
    }

    @Override
    public int getItemCount() {
        return playingHistoryList.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView lbl_id_level_playing_history, lbl_waktu_bermain_playing_history_fragment, lbl_skor_playing_history_fragment;
        CardView cardView;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            lbl_id_level_playing_history = itemView.findViewById(R.id.lbl_id_level_playing_history);
            lbl_waktu_bermain_playing_history_fragment = itemView.findViewById(R.id.lbl_waktu_bermain_playing_history_fragment);
            lbl_skor_playing_history_fragment = itemView.findViewById(R.id.lbl_skor_playing_history_fragment);
            cardView = itemView.findViewById(R.id.cv_playing_history_fragment);
        }
    }
}
