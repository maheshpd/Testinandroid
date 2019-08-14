package com.example.test;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.test.fragment.BookFragment;
import com.example.test.fragment.GameFragment;
import com.example.test.fragment.MusicFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                BookFragment bookFragment = new BookFragment();
                return bookFragment;
            case 1:
                MusicFragment musicFragment = new MusicFragment();
                return musicFragment;
            case 2:
                GameFragment gameFragment = new GameFragment();
                return gameFragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }
}
