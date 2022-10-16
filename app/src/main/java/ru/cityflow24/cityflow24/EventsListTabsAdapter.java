package ru.cityflow24.cityflow24;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class EventsListTabsAdapter extends FragmentPagerAdapter {

    String[] titles = new String[]{"Сегодня", "Завтра", "Все"};

    public EventsListTabsAdapter(FragmentManager fm) {
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
