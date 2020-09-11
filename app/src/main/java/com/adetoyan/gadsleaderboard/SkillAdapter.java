package com.adetoyan.gadsleaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SkillAdapter extends RecyclerView.Adapter <SkillAdapter.CustomViewHolder> {
    private List<RetroSkill> dataList;
    private Context context;

//    private final LinkedList<String> mWordList;

    private LayoutInflater mInflater;

    public SkillAdapter(Context context,
                        List<RetroSkill> dataList) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.dataList = dataList;
    }
    class CustomViewHolder extends RecyclerView.ViewHolder {
//        public final View mView;

        TextView txtName;
        TextView txtScore;
        private ImageView badge;

        //        public final TextView wordItemView;
        final SkillAdapter mAdapter;
        public CustomViewHolder(@NonNull View itemView, SkillAdapter adapter) {
            super(itemView);
            txtName = itemView.findViewById(R.id.skill_name);
            txtScore = itemView.findViewById(R.id.skill_score);
            badge = itemView.findViewById(R.id.skill_image);
            this.mAdapter = adapter;
        }
    }
    @NonNull
    @Override
    public SkillAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.skill_item,
                parent, false);
        return new CustomViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillAdapter.CustomViewHolder holder, int position) {
        holder.txtName.setText(dataList.get(position).getName());
        int score = dataList.get(position).getScore();
        String country = dataList.get(position).getCountry();
        String badge = dataList.get(position).getBadgeUrl();
        holder.txtScore.setText(new StringBuilder().append(score).append(" skill IQ Score, ").append(country).toString());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getBadgeUrl())
                .placeholder((R.drawable.skill_iq))
                .error(R.drawable.skill_iq)
                .into(holder.badge);

        /*String mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mCurrent);*/
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
