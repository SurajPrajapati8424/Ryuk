package com.dr.psycho.ryuk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.dr.psycho.ryuk.Interface.FlitersListFragmentListener;
import com.dr.psycho.ryuk.R;
import com.zomato.photofilters.utils.ThumbnailItem;

import java.util.List;

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailAdapter.MyViewHolder> {

    private List<ThumbnailItem> thumbnailItems;
    private FlitersListFragmentListener listener;
    private Context context;
    private int selectedIndex = 0;

    //ALT + INS only THREE
    public ThumbnailAdapter(List<ThumbnailItem> thumbnailItems, FlitersListFragmentListener listener, Context context) {
        this.thumbnailItems = thumbnailItems;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.thumbnails_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ThumbnailItem thumbnailItem = thumbnailItems.get(position);

        holder.thumbnail.setImageBitmap(thumbnailItem.image);
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFilterSelected(thumbnailItem.filter);
                selectedIndex = position;
                notifyDataSetChanged();
            }
        });
        holder.filter_name.setText(thumbnailItem.filterName);

        if (selectedIndex == position)
            holder.filter_name.setTextColor(ContextCompat.getColor(context, R.color.selected_filter));
        else
            holder.filter_name.setTextColor(ContextCompat.getColor(context, R.color.normal_filter));
    }

    @Override
    public int getItemCount() {
        return thumbnailItems.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        ImageView thumbnail;
        TextView filter_name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = (ImageView)itemView.findViewById(R.id.thumbnail);
            filter_name = (TextView)itemView.findViewById(R.id.filter_name);
        }
    }
}
