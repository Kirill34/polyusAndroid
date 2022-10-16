package ru.cityflow24.cityflow24;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class UserEventListTabsAdapter extends FragmentPagerAdapter {

    String[] titles = new String[]{"Текущие", "В планах", "История"};

    public UserEventListTabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int i) {
        return new EventsFragment();
    }

    @Override
    public int getCount() {
        return titles.length;
    }
}
