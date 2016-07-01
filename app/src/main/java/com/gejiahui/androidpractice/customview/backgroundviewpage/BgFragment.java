package com.gejiahui.androidpractice.customview.backgroundviewpage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gejiahui.androidpractice.FragmentAdapter;
import com.gejiahui.androidpractice.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gejiahui on 2016/6/30.
 */
public class BgFragment extends Fragment {

    @Bind(R.id.view_page)
    BackGroundViewPage mViewPage;

    List<Fragment> fragmentList = new ArrayList<Fragment>();
    FragmentAdapter mAdapter;
    FragmentManager fm;
    public BgFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_background,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        init();
        super.onViewCreated(view, savedInstanceState);
    }

    private void init(){
        fm = getActivity().getSupportFragmentManager();
        fragmentList.add(new SimpleFragment());
        fragmentList.add(new SimpleFragment());
        fragmentList.add(new SimpleFragment());

        mAdapter = new FragmentAdapter(fm,fragmentList);

         mViewPage.setAdapter(mAdapter);
        mViewPage.setCurrentItem(1);
    }




}
