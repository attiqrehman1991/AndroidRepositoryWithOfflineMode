/*
 * Copyright (c) 2017.
 * Sample Project Architecture for Repository Model in Android.
 * Developed by Attiq Ur Rehman
 * Senior Software Engineer
 * Systems Limited Lahore
 */

package android.repositorymodel.dao;

import android.repositorymodel.RepositoryApplication;
import android.repositorymodel.controller.MainController;
import android.repositorymodel.factory.loginfactory.LoginFactory;
import android.repositorymodel.factory.loginfactory.LoginStore;
import android.repositorymodel.factory.loginfactory.QueueLoginJob;
import android.repositorymodel.model.ParentObject;
import android.repositorymodel.model.User;
import android.repositorymodel.utilities.StaticInfo;

import com.birbit.android.jobqueue.JobManager;

import org.json.JSONObject;

import io.realm.Realm;

public class LoginDAB extends ParentDAB {

    private MainController mainController;

    @Override
    public void updateDAB(Object object) {
        JSONObject jsonObject = (JSONObject) object;

        String username = "", password = "";
        try {
            username = jsonObject.getString("username");
            password = jsonObject.getString("password");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.delete(User.class);

        User user = realm.createObject(User.class);
        user.setUsername(username);
        user.setPassword(password);
        realm.commitTransaction();

        mainController.updateResponse(user);
    }

    public void saveData(ParentObject parentObject, MainController mainController, boolean isInternet) {
        this.mainController = mainController;

        if (isInternet) {
            if (StaticInfo.JOB_QUEUE) {
                JobManager jobManager = RepositoryApplication.getInstance().getJobManager();
                jobManager.addJobInBackground(new QueueLoginJob(parentObject, LoginDAB.this));
            } else {
                // to make direct network call
                LoginStore store = LoginFactory.getLoginFactory().getStore(StaticInfo.NETWORK_TYPE, LoginDAB.this);
                store.save(parentObject);
            }
        } else {
            Realm realm = Realm.getDefaultInstance();
            User user = realm.where(User.class).findFirst();
            mainController.updateResponse(user);
        }
    }

}
