/*
 * Copyright (c) 2017.
 * Sample Project Architecture for Repository Model in Android.
 * Developed by Attiq Ur Rehman
 * Senior Software Engineer
 * Systems Limited Lahore
 */

package attiqrao.systems.repository.services;

import attiqrao.systems.repository.factory.loginfactory.LoginFactory;
import attiqrao.systems.repository.factory.loginfactory.LoginStore;
import attiqrao.systems.repository.model.ParentObject;
import attiqrao.systems.repository.utilities.StaticInfo;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by Attiq ur Rehman.
 */
public class LoginFireBaseJobService extends JobService {
    private static final String TAG = "SyncService";

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        ParentObject parentObject = jobParameters.getExtras().getParcelable("parentObject");
        LoginStore store = LoginFactory.getLoginFactory().getStore(StaticInfo.NETWORK_TYPE, null);
        store.save(parentObject, this.getBaseContext());

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false; // Answers the question: "Should this job be retried?"
    }
}