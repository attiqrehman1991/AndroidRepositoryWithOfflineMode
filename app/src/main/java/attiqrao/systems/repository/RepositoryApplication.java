/*
 * Copyright (c) 2017.
 * Sample Project Architecture for Repository Model in Android.
 * Developed by Attiq Ur Rehman
 * Senior Software Engineer
 * Systems Limited Lahore
 */

package attiqrao.systems.repository;

import android.app.job.JobScheduler;
import android.os.Build;
import android.support.multidex.MultiDexApplication;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RepositoryApplication extends MultiDexApplication {

    private static FirebaseJobDispatcher dispatcher;
    private static JobScheduler jobScheduler;

    public static FirebaseJobDispatcher getDispatcher() {
        return dispatcher;
    }

    public static JobScheduler getJobScheduler() {
        return jobScheduler;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        }

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
    }
}
