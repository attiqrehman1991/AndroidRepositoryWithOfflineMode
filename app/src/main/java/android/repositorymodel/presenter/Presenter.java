/*
 * Copyright (c) 2017.
 * Sample Project Architecture for Repository Model in Android.
 * Developed by Attiq Ur Rehman
 * Senior Software Engineer
 * Systems Limited Lahore
 */

package android.repositorymodel.presenter;

import android.os.Handler;
import android.os.Message;
import android.repositorymodel.controller.LoginController;
import android.repositorymodel.model.User;
import android.repositorymodel.view.IView;

public class Presenter implements IPresenter {
    static IView iView;
    private static Presenter presenter;

    public static Presenter getInstance() {
        if (presenter == null) {
            presenter = new Presenter();
        }
        return presenter;
    }

    @Override
    public void setView(IView iView) {
        this.iView = iView;
    }

    @Override
    public void onClick(String username, String message, boolean isNetAvailable) {
//        iModel.createMessage(type, message);
        LoginController loginController = new LoginController();
        final UIHandler handler = new UIHandler();
        User user = new User();
        user.setUsername(username);
        user.setPassword(message);
        loginController.processLogin(LoginController.ACTION_SAVE, user, handler, isNetAvailable);
    }

    static class UIHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LoginController.ACTION_SAVE:
                    User user = (User) msg.obj;
                    iView.loginResponse(user.getUsername() + " --- " + user.getPassword());
                    break;
            }
        }
    }

}
