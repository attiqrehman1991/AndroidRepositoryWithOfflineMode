/*
 * Copyright (c) 2017.
 * Sample Project Architecture for Repository Model in Android.
 * Developed by Attiq Ur Rehman
 * Senior Software Engineer
 * Systems Limited Lahore
 */

package attiqrao.systems.repository.services;

import android.app.job.JobParameters;
import android.app.job.JobService;

import attiqrao.systems.repository.dao.LoginDAB;
import attiqrao.systems.repository.factory.loginfactory.LoginFactory;
import attiqrao.systems.repository.factory.loginfactory.LoginStore;
import attiqrao.systems.repository.model.ParentObject;
import attiqrao.systems.repository.utilities.StaticInfo;

public class LoginJobService extends JobService {
    private static final String TAG = "SyncService";

    @Override
    public boolean onStartJob(JobParameters job) {
        ParentObject parentObject = LoginDAB.parentObject;
        LoginStore store = LoginFactory.getLoginFactory().getStore(StaticInfo.NETWORK_TYPE, null);
        store.save(parentObject, this.getBaseContext());
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false; // Answers the question: "Should this job be retried?"
    }
}