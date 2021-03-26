package com.dr.psycho.ryuk.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dr.psycho.ryuk.R;

import java.util.ArrayList;
import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder> {

    Context context;
    List<Integer> colorList;
    ColorAdapterListener listener;

    public ColorAdapter(Context context, ColorAdapterListener listener) {
        this.context = context;
        this.colorList = genColorList();
        this.listener = listener;
    }

    @NonNull
    @Override
    public ColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.color_item,parent,false);
        return new ColorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorViewHolder holder, int position) {
        holder.color_section.setCardBackgroundColor(colorList.get(position));
    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }

    public class ColorViewHolder extends RecyclerView.ViewHolder{

        public CardView color_section;

        public ColorViewHolder(@NonNull View itemView) {
            super(itemView);

            color_section = (CardView) itemView.findViewById(R.id.color_section);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onColorSelected(colorList.get(getAdapterPosition()));
                }
            });
        }
    }

    private List<Integer> genColorList() {
        List<Integer> colorList = new ArrayList<>();

        colorList.add(Color.parseColor("#1a1a30"));
        colorList.add(Color.parseColor("#01011a"));
        colorList.add(Color.parseColor("#4080ff"));
        colorList.add(Color.parseColor("#f2e9cd"));
        colorList.add(Color.parseColor("#cdd6f2"));
        colorList.add(Color.parseColor("#9ba95b"));
        colorList.add(Color.parseColor("#b8c8e9"));

        colorList.add(Color.parseColor("#b8e1e9"));
        colorList.add(Color.parseColor("#c1b8e9"));
        colorList.add(Color.parseColor("#b8c8e9"));
        colorList.add(Color.parseColor("#9ba95b"));
        colorList.add(Color.parseColor("#f2b727"));
        colorList.add(Color.parseColor("#136978"));
        colorList.add(Color.parseColor("#01011a"));

        colorList.add(Color.parseColor("#00529c"));
        colorList.add(Color.parseColor("#123123"));
        colorList.add(Color.parseColor("#008080"));
        colorList.add(Color.parseColor("#eeae02"));
        colorList.add(Color.parseColor("#ffdab9"));
        colorList.add(Color.parseColor("#f00000"));
        colorList.add(Color.parseColor("#ff8040"));
        return  colorList;
    }

    public  interface ColorAdapterListener{
        void onColorSelected(int color);
    }
}
