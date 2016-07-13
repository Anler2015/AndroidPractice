package com.gejiahui.androidpractice.animationrecyclerview;

/**
 * Created by gejiahui on 2016/7/13.
 */
public class ItemBean {

    String name;
    boolean checked;

    public ItemBean(String name) {
        this.name = name;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }
}
