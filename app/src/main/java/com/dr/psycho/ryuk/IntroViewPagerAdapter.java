package com.dr.psycho.ryuk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class IntroViewPagerAdapter extends PagerAdapter {

    Context mContext;
    List<ScreenItem> mListScreen;
    ConstraintLayout constraintLayout;

    public IntroViewPagerAdapter(Context mContext, List<ScreenItem> mListScreen) {
        this.mContext = mContext;
        this.mListScreen = mListScreen;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.layout_screen,null);

        ImageView imgSlide = layoutScreen.findViewById(R.id.intro_img);
        TextView title = layoutScreen.findViewById(R.id.intro_title);
        TextView description = layoutScreen.findViewById(R.id.intro_description);
        constraintLayout = layoutScreen.findViewById(R.id.introScreenLayout);

        title.setText(mListScreen.get(position).getTitle());
        description.setText(mListScreen.get(position).getDescription());
        imgSlide.setImageResource(mListScreen.get(position).getScreenImg());

        if (position == 0) {
            layoutScreen.setBackgroundColor(mContext.getColor(R.color.click_to_go));
            title.setTextColor(mContext.getColor(R.color.white_50));
            description.setTextColor(mContext.getColor(R.color.white_50));
        }
        if (position == 1){
            layoutScreen.setBackgroundColor(mContext.getColor(R.color.funny_tool));
            title.setTextColor(mContext.getColor(R.color.white_50));
            description.setTextColor(mContext.getColor(R.color.white_50));
        }
        if (position == 2){
            layoutScreen.setBackgroundColor(mContext.getColor(R.color.white));
            title.setTextColor(mContext.getColor(R.color.black_200));
            description.setTextColor(mContext.getColor(R.color.black_200));
        }

        container.addView(layoutScreen);

        return layoutScreen;
    }

    @Override
    public int getCount() {
        return mListScreen.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View)object) ;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == object;
    }
}
