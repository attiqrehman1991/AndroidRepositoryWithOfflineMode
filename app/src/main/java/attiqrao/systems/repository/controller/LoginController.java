/*
 * Copyright (c) 2017.
 * Sample Project Architecture for Repository Model in Android.
 * Developed by Attiq Ur Rehman
 * Senior Software Engineer
 * Systems Limited Lahore
 */

package attiqrao.systems.repository.controller;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import attiqrao.systems.repository.dao.LoginDAB;
import attiqrao.systems.repository.model.ParentObject;

/**
 * Created by Attiq ur Rehman on 3/7/2017.
 * Senior Software Engineer at Systems Ltd
 * attiq.ur.rehman1991@gmail.com
 */

public class LoginController extends MainController {
    public static final int ACTION_SAVE = 1;
    Handler handler;
    private LoginDAB loginDAB;

    @Override
    public void updateResponse(Object object) {
        Message message = handler.obtainMessage();
        message.what = ACTION_SAVE;
        message.obj = object;
        handler.sendMessage(message);
    }

    public void processLogin(int type, final Object data, final Handler handler, boolean isInternet, Context context, IntentFilter intentFilter) {
        if (type == ACTION_SAVE) {
            if (data instanceof ParentObject) {
                LoginController.this.handler = handler;
                ParentObject parentObject = (ParentObject) data;
                loginDAB = new LoginDAB();
                context.registerReceiver(broadcastReceiver, intentFilter);
                loginDAB.saveData(parentObject, LoginController.this, isInternet, context, intentFilter);
            }
        }
    }


    public void unRegisterReceiver(Context context) {
        loginDAB.unregisterReceiver(context);
    }
}
