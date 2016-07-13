package com.gejiahui.androidpractice.customview.bottomsheet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gejiahui.androidpractice.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by gejiahui on 2016/7/7.
 */
public class BottomSheetFragment extends Fragment {

    @Bind(R.id.show)
    Button show;
    @Bind(R.id.bottom_sheet)
    BottomSheetLayout bottomSheetLayout;
    boolean isShow = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bottom_sheet_layout,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.show)
    public void showSheet(){
        if(isShow){
            bottomSheetLayout.closeSheet();
            isShow = false;
        }else{
            bottomSheetLayout.showSheet();
            isShow = true;
        }

    }


}
