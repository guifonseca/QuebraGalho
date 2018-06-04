package com.app.myapp.callbacks;

import android.support.v4.app.Fragment;

public interface IActivityCallback {

    void setScreenTitle(String title);

    void setScreenSubtitle(String title);

    void startFragment(int fragmentID, Fragment fragment);
}
