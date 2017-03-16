/*
 * Copyright (c) 2017.
 * Sample Project Architecture for Repository Model in Android.
 * Developed by Attiq Ur Rehman
 * Senior Software Engineer
 * Systems Limited Lahore
 */

package attiqrao.systems.repository.factory.loginfactory;

import android.content.Context;
import android.content.Intent;

import org.json.JSONObject;

import attiqrao.systems.repository.dao.LoginDAB;
import attiqrao.systems.repository.model.ParentObject;

public abstract class LoginStore {
    protected Context context;

    public abstract void save(ParentObject parentObject, Context context);

    protected void generateBroadCast(JSONObject jsonObject) {
        Intent intent = new Intent();
        intent.setAction(LoginDAB.LOGIN_SUCCESSFUL);
        intent.putExtra("json", jsonObject.toString());
        context.sendBroadcast(intent);
    }
}
