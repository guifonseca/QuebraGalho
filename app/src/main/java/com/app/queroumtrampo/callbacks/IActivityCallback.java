package com.app.queroumtrampo.callbacks;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;

public interface IActivityCallback {

    void setScreenTitle(String title);

    void setScreenSubtitle(String title);

    void startFragment(int fragmentID, Fragment fragment);
}
