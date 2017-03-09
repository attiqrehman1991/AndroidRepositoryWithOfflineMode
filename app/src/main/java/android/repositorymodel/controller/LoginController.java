/*
 * Copyright (c) 2017.
 * Sample Project Architecture for Repository Model in Android.
 * Developed by Attiq Ur Rehman
 * Senior Software Engineer
 * Systems Limited Lahore
 */

package android.repositorymodel.controller;

import android.os.Handler;
import android.os.Message;
import android.repositorymodel.dao.LoginDAB;
import android.repositorymodel.model.ParentObject;
import android.repositorymodel.model.User;

import org.json.JSONObject;

import io.realm.Realm;

/**
 * Created by Attiq ur Rehman on 3/7/2017.
 * Senior Software Engineer at Systems Ltd
 * attiq.ur.rehman1991@gmail.com
 */

public class LoginController extends MainController {
    public static final int ACTION_SAVE = 1;
    Handler handler;

    @Override
    public void updateResponse(Object object) {
        Message message = handler.obtainMessage();
        message.what = ACTION_SAVE;
        message.obj= object;
        handler.sendMessage(message);
    }

    public void processLogin(int type, final Object data, final Handler handler, boolean isInternet) {
        if (type == ACTION_SAVE) {
            if (data instanceof ParentObject) {
                LoginController.this.handler = handler;
                ParentObject parentObject = (ParentObject) data;
                LoginDAB loginDAB = new LoginDAB();
                loginDAB.saveData(parentObject, LoginController.this, isInternet);
            }
        }
    }
}
