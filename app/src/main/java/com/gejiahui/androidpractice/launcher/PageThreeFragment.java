package com.gejiahui.androidpractice.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gejiahui.androidpractice.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gejiahui on 2016/5/27.
 */
public class PageThreeFragment extends LauncherBaseFragment {

    @Bind(R.id.imgView_immediate_experience)
    ImageView imgStart;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_three,container,false);
        ButterKnife.bind(this,view);
        imgStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return view;
    }

    @Override
    public void startAnimation() {
        startAimation();
    }

    @Override
    public void stopAnimation() {

    }


    private void startAimation(){
;
        imgStart.animate().scaleX(1.2f).scaleY(1.2f).setDuration(500).withEndAction(new Runnable() {
            @Override
            public void run() {
                imgStart.animate().scaleX(1f).scaleY(1f).setDuration(500).start();
            }
        }).start();
    }
}
