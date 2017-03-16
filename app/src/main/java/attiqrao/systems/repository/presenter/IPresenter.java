/*
 * Copyright (c) 2017.
 * Sample Project Architecture for Repository Model in Android.
 * Developed by Attiq Ur Rehman
 * Senior Software Engineer
 * Systems Limited Lahore
 */

package attiqrao.systems.repository.presenter;

import android.content.Context;
import android.content.IntentFilter;
import attiqrao.systems.repository.view.IView;

public interface IPresenter {

    void setView(IView iView);

    void onClick(String username, String message, boolean isNetAvailable, Context context, IntentFilter intentFilter);

    void unRegisterReceiver(Context context);
}
