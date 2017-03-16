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
import android.os.Handler;
import android.os.Message;
import attiqrao.systems.repository.controller.LoginController;
import attiqrao.systems.repository.model.User;
import attiqrao.systems.repository.view.IView;

public class Presenter implements IPresenter {
    public final static String LOGIN_SUCCESSFUL = "LOGIN_SUCCESSFUL";
    static IView iView;
    private static Presenter presenter;
    private LoginController loginController;

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
    public void onClick(String username, String message, boolean isNetAvailable, Context context, IntentFilter intentFilter) {
//        iModel.createMessage(type, message);
        loginController = new LoginController();
        final UIHandler handler = new UIHandler();
        User user = new User();
        user.setUsername(username);
        user.setPassword(message);
        loginController.processLogin(LoginController.ACTION_SAVE, user, handler, isNetAvailable, context, intentFilter);
    }

    @Override
    public void unRegisterReceiver(Context context) {
        loginController.unRegisterReceiver(context);
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
