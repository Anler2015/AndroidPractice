// IUserManager.aidl
package com.gejiahui.androidpractice.aidl;

// Declare any non-default types here with import statements
import com.gejiahui.androidpractice.aidl.User;
interface IUserManager {

    List<User> getUserList();
    void addUser(in User user);
}
