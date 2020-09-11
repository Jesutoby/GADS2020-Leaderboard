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

public class LearnAdapter extends RecyclerView.Adapter <LearnAdapter.CustomViewHolder> {
    private List<RetroLearn> dataList;
    private Context context;

//    private final LinkedList<String> mWordList;

    private LayoutInflater mInflater;

    public LearnAdapter(Context context,
                        List<RetroLearn> dataList) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.dataList = dataList;
    }
    class CustomViewHolder extends RecyclerView.ViewHolder {
//        public final View mView;

        TextView txtName;
        TextView txtHours;
        private ImageView badge;

//        public final TextView wordItemView;
        final LearnAdapter mAdapter;
        public CustomViewHolder(@NonNull View itemView, LearnAdapter adapter) {
            super(itemView);
            txtName = itemView.findViewById(R.id.learn_name);
            txtHours = itemView.findViewById(R.id.learn_hours);
            badge = itemView.findViewById(R.id.learn_image);
            this.mAdapter = adapter;
        }
    }
    @NonNull
    @Override
    public LearnAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.learn_item,
                parent, false);
        return new CustomViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnAdapter.CustomViewHolder holder, int position) {
        holder.txtName.setText(dataList.get(position).getName());
        int hours = dataList.get(position).getHours();
        String country = dataList.get(position).getCountry();
        String badge = dataList.get(position).getBadgeUrl();
        holder.txtHours.setText(new StringBuilder().append(hours).append(" learning hours, ").append(country).toString());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getBadgeUrl())
                .placeholder((R.drawable.top_learner))
                .error(R.drawable.top_learner)
                .into(holder.badge);

        /*String mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mCurrent);*/
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
