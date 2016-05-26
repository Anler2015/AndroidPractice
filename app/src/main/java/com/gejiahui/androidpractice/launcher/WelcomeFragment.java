package com.gejiahui.androidpractice.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gejiahui.androidpractice.R;

/**
 * Created by gejiahui on 2016/5/26.
 */
public class WelcomeFragment extends LauncherBaseFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome,container,false);

        return view;
    }

    @Override
    public void startAnimation() {

    }

    @Override
    public void stopAnimation() {

    }
}
