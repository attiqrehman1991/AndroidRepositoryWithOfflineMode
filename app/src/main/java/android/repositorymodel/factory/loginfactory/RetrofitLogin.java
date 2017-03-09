/*
 * Copyright (c) 2017.
 * Sample Project Architecture for Repository Model in Android.
 * Developed by Attiq Ur Rehman
 * Senior Software Engineer
 * Systems Limited Lahore
 */

package android.repositorymodel.factory.loginfactory;

import android.repositorymodel.dao.ParentDAB;
import android.repositorymodel.model.ParentObject;
import android.repositorymodel.model.User;
import android.repositorymodel.utilities.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Attiq ur Rehman on 3/7/2017.
 * Senior Software Engineer at Systems Ltd
 * attiq.ur.rehman1991@gmail.com
 */

public class RetrofitLogin implements LoginStore {
    JSONObject jsonObject;
    private RXLoginServices RXLoginServices;
    private ParentDAB parentDAB;

    public RetrofitLogin(ParentDAB parentDAB) {
        this.parentDAB = parentDAB;
    }

    @Override
    public void save(ParentObject parentObject) {
        RXLoginServices = RetrofitClient.getClient().create(RXLoginServices.class);
        jsonObject = new JSONObject();

        User user = (User) parentObject;
        RetrofitLoginServices retrofitLoginServices = RetrofitClient.getClient().create(RetrofitLoginServices.class);
        Call<User> respInfo = retrofitLoginServices.createTask(user);
//            Object object = respInfo.execute().body();

        respInfo.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                try {
                    jsonObject.put("username", "r_username");
                    jsonObject.put("password", "r_password");
                    parentDAB.updateDAB(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                try {
                    jsonObject.put("username", "r_username");
                    jsonObject.put("password", "r_password");
                    parentDAB.updateDAB(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
