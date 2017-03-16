/*
 * Copyright (c) 2017.
 * Sample Project Architecture for Repository Model in Android.
 * Developed by Attiq Ur Rehman
 * Senior Software Engineer
 * Systems Limited Lahore
 */

package attiqrao.systems.repository.factory.loginfactory;

import android.content.Context;
import attiqrao.systems.repository.dao.ParentDAB;
import attiqrao.systems.repository.model.ParentObject;
import attiqrao.systems.repository.model.User;
import attiqrao.systems.repository.utilities.RetrofitClient;

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

public class RetrofitLogin extends LoginStore {
    JSONObject jsonObject;
    private RXLoginServices RXLoginServices;
    private ParentDAB parentDAB;

    public RetrofitLogin(ParentDAB parentDAB) {
        this.parentDAB = parentDAB;
    }

    @Override
    public void save(ParentObject parentObject, Context context) {
        RXLoginServices = RetrofitClient.getClient().create(RXLoginServices.class);
        jsonObject = new JSONObject();
        this.context = context;

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
                    if (parentDAB != null)
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
                    if (parentDAB != null)
                        parentDAB.updateDAB(jsonObject);
                    else
                        generateBroadCast(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
