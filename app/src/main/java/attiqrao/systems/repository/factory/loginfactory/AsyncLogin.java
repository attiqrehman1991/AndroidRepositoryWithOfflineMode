/*
 * Copyright (c) 2017.
 * Sample Project Architecture for Repository Model in Android.
 * Developed by Attiq Ur Rehman
 * Senior Software Engineer
 * Systems Limited Lahore
 */

package attiqrao.systems.repository.factory.loginfactory;

import android.content.Context;
import android.os.AsyncTask;
import attiqrao.systems.repository.dao.ParentDAB;
import attiqrao.systems.repository.model.ParentObject;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Attiq ur Rehman on 3/7/2017.
 * Senior Software Engineer at Systems Ltd
 * attiq.ur.rehman1991@gmail.com
 */

public class AsyncLogin extends LoginStore {
    private ParentDAB parentDAB;

    public AsyncLogin(ParentDAB parentDAB) {
        this.parentDAB = parentDAB;
    }

    @Override
    public void save(ParentObject parentObject, Context context) {
        new LongOperation().execute("");
        this.context = context;
    }

    private class LongOperation extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("username", "a_username");
                jsonObject.put("password", "a_password");
            } catch (JSONException e) {
                Thread.interrupted();
            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            if (parentDAB != null)
                parentDAB.updateDAB(jsonObject);
            else
                generateBroadCast(jsonObject);
        }
    }
}
