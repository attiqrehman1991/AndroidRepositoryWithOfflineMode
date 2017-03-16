/*
 * Copyright (c) 2017.
 * Sample Project Architecture for Repository Model in Android.
 * Developed by Attiq Ur Rehman
 * Senior Software Engineer
 * Systems Limited Lahore
 */

package attiqrao.systems.repository;

import android.app.Application;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RepositoryApplication extends Application {

    private static FirebaseJobDispatcher dispatcher;

    public static FirebaseJobDispatcher getDispatcher() {
        return dispatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
    }
}
