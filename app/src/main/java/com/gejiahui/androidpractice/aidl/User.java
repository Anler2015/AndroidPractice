package com.gejiahui.androidpractice.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gejiahui on 2016/5/16.
 */
public class User implements Parcelable {
    String name;
    int age;
    boolean isMale;

    public User(String name, int age, boolean isMale) {
        this.name = name;
        this.age = age;
        this.isMale = isMale;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeInt(isMale ? 1 : 0);
    }

    public static final Creator<User> CREATOR = new Creator<User>(){
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };


    private User(Parcel source){
        name = source.readString();
        age = source.readInt();
        isMale = source.readInt() == 1;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isMale() {
        return isMale;
    }
}
