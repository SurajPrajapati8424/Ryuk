package com.dr.psycho.ryuk;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class introActivity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        ArrayList<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Click To Go","Start Editing From Fresh Clicked Image. Start Editing From Fresh Clicked Image \n Start Editing From Fresh Clicked Image",R.drawable.scooty));
        mList.add(new ScreenItem("Quick Edit","Get a Image apply Filter's on it. \n Get a Image apply Filter's on it. Get a Image apply Filter's on it.",R.drawable.office));
        mList.add(new ScreenItem("Funny Tool","Add funny images on your Friends face. Add funny images on your Friends face. Add funny images on your Friends face",R.drawable.food));

        // setup viewpager
        screenPager = findViewById(R.id.screenViewPager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);

    }

}