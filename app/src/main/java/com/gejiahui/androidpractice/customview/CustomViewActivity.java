package com.gejiahui.androidpractice.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.gejiahui.androidpractice.R;
import com.gejiahui.androidpractice.customview.backgroundviewpage.BgFragment;
import com.gejiahui.androidpractice.customview.bottomsheet.BottomSheetFragment;

/**
 * Created by gejiahui on 2016/6/29.
 */
public class CustomViewActivity extends AppCompatActivity {


    FragmentManager fm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customview);

        fm = getSupportFragmentManager();
        BottomSheetFragment fragment = new BottomSheetFragment();
        changeCustomView(fragment);
    }


    private void changeCustomView(Fragment fragment){
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.content,fragment);
        fragmentTransaction.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_custom_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.bg_view_page) {
            BgFragment bgFragment = new BgFragment();
            changeCustomView(bgFragment);
            return true;
        }

        if (id == R.id.bottom_sheet) {
            BottomSheetFragment fragment = new BottomSheetFragment();
            changeCustomView(fragment);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
