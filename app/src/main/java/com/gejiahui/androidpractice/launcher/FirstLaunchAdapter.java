package com.gejiahui.androidpractice.launcher;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by gejiahui on 2016/5/26.
 */
public class FirstLaunchAdapter extends FragmentStatePagerAdapter {
    private List<LauncherBaseFragment> list;

    public FirstLaunchAdapter(FragmentManager fm, List<LauncherBaseFragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
