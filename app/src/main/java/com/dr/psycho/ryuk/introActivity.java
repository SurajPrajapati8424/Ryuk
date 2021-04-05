package com.dr.psycho.ryuk;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class introActivity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabLayout;
    Button btnNext;
    int position = 0;
    Button btnGetStarted;
    Animation btnAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // make the activity on full screen
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);
        // hide the action bar
//        getSupportActionBar().hide();


        // ini views
        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_getStart);
        tabLayout = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);

        ArrayList<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Click To Go","Start Editing From Fresh Clicked Image. Start Editing From Fresh Clicked Image \n Start Editing From Fresh Clicked Image",R.drawable.scooty));
        mList.add(new ScreenItem("Quick Edit","Get a Image apply Filter's on it. \n Get a Image apply Filter's on it. Get a Image apply Filter's on it.",R.drawable.office));
        mList.add(new ScreenItem("Funny Tool","Add funny images on your Friends face. Add funny images on your Friends face. Add funny images on your Friends face",R.drawable.food));

        // setup viewpager
        screenPager = findViewById(R.id.screenViewPager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);

        // setup tabLayout with viewPager
        tabLayout.setupWithViewPager(screenPager);

        // next button Listener

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = screenPager.getCurrentItem();
                if ( position < mList.size()){
                    position ++;
                    screenPager.setCurrentItem(position);


                }
                if (position == mList.size()-1){  // when we reach to the last screen

                    // TODO : show the GETSTARTED Button and  hide the indicator and the next button

                    loadLastScreen();
                }


            }
        });
        // tab-layout add  change Listener
        //TODO : Also wanted to run swipe and next button work as same on "On-Boarding Screen"
//  This Fix the ( if Next button get to End then only "Get Started" appears NOT with swipe ) For Swipe this fixed BUG !!!!

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size()-1){
                    loadLastScreen();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void loadLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.INVISIBLE);
        // TODO : ADD an Animation the getstarted

        // setup animation

        btnGetStarted.setAnimation(btnAnim);

    }

}