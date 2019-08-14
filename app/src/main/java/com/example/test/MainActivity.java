package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    //widget
    private TabLayout mTab;
    ViewPagerAdapter adapter;
    private ViewPager pager;

    private int[] tabIcons = {
            R.drawable.open_book,
            R.drawable.megaphone,
            R.drawable.gamepad_controller
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        initWidget
        initWidget();

    }

    private void initWidget() {
        mTab = findViewById(R.id.tabsLayout);
        pager = findViewById(R.id.viewpager);
        adapter =new ViewPagerAdapter(getSupportFragmentManager());
//
        pager.setAdapter(adapter);
        mTab.setupWithViewPager(pager);
        setupTabIcons();

    }

    private void setupTabIcons() {
        mTab.getTabAt(0).setIcon(tabIcons[0]);
        mTab.getTabAt(1).setIcon(tabIcons[1]);
        mTab.getTabAt(2).setIcon(tabIcons[2]);

    }
}
