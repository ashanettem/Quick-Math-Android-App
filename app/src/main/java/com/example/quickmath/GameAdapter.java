package com.example.quickmath;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class GameAdapter extends FirestoreRecyclerAdapter<Game, GameAdapter.GameHolder> {

    public GameAdapter(@NonNull FirestoreRecyclerOptions<Game> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull GameHolder holder, int position, @NonNull Game model) {
        holder.tvChild.setText(model.getChild());
        holder.tvType.setText(model.getType());
        holder.tvScore.setText(String.valueOf(model.getScore()));
        holder.tvCount.setText(String.valueOf(model.getNumOfQuestions()));
    }

    @NonNull
    @Override
    public GameHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_result, parent, false);
        return new GameHolder(v);
    }

    public class GameHolder extends RecyclerView.ViewHolder {
        TextView tvChild;
        TextView tvType;
        TextView tvScore;
        TextView tvCount;

        TextView tvType1;
        TextView tvScore1;
        TextView tvCount1;

        public GameHolder(View itemView){
            super(itemView);
            tvChild = itemView.findViewById(R.id.tvChildRes);
            tvType = itemView.findViewById(R.id.tvGameTypeRes);
            tvScore = itemView.findViewById(R.id.tvScoreRes);
            tvCount = itemView.findViewById(R.id.tvQuestionCountRes);
            tvType1 = itemView.findViewById(R.id.tvGameTypeRes1);
            tvScore1 = itemView.findViewById(R.id.tvScoreRes1);
            tvCount1 = itemView.findViewById(R.id.tvQuestionCountRes1);
        }


    }
}
