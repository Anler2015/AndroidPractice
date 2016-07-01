package com.gejiahui.androidpractice.customview.backgroundviewpage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gejiahui.androidpractice.R;

/**
 * Created by gejiahui on 2016/6/30.
 */
public class SimpleFragment extends Fragment{

    public SimpleFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_mainactivity_list,container,false);
        return view;
    }
}
