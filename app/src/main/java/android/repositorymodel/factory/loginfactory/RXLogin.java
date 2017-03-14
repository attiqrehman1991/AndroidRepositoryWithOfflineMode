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
import android.repositorymodel.utilities.RetrofitRXClient;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Attiq ur Rehman on 3/7/2017.
 * Senior Software Engineer at Systems Ltd
 * attiq.ur.rehman1991@gmail.com
 */

public class RXLogin implements LoginStore {
    JSONObject jsonObject;
    private RXLoginServices RXLoginServices;

    private ParentDAB parentDAB;

    public RXLogin(ParentDAB parentDAB) {
        this.parentDAB = parentDAB;
    }

    @Override
    public void save(ParentObject parentObject) {
        RXLoginServices = RetrofitRXClient.getClient().create(RXLoginServices.class);
        jsonObject = new JSONObject();

        User user = (User) parentObject;
        RXLoginServices.userLogin(user).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Response<Object>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                try {
                    jsonObject.put("username", "rx_username");
                    jsonObject.put("password", "rx_password");
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                if (parentDAB != null)
                    parentDAB.updateDAB(jsonObject);
            }

            @Override
            public void onNext(Response<Object> objectResponse) {
                try {
                    jsonObject.put("username", "rx_username");
                    jsonObject.put("password", "rx_password");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (parentDAB != null)
                    parentDAB.updateDAB(jsonObject);
            }
        });
    }
}
