/*
 * Copyright (c) 2017.
 * Sample Project Architecture for Repository Model in Android.
 * Developed by Attiq Ur Rehman
 * Senior Software Engineer
 * Systems Limited Lahore
 */

package android.repositorymodel.factory.loginfactory;

import android.repositorymodel.RepositoryApplication;
import android.repositorymodel.dao.ParentDAB;
import android.repositorymodel.model.ParentObject;

import com.birbit.android.jobqueue.JobManager;

import org.json.JSONObject;

/**
 * Created by Attiq ur Rehman on 3/7/2017.
 * Senior Software Engineer at Systems Ltd
 * attiq.ur.rehman1991@gmail.com
 */

public class JobQueueLogin implements LoginStore {
    JSONObject jsonObject;
    private ParentDAB parentDAB;

    public JobQueueLogin(ParentDAB parentDAB) {
        this.parentDAB = parentDAB;
    }

    @Override
    public void save(ParentObject parentObject) {
        JobManager jobManager = RepositoryApplication.getInstance().getJobManager();
        jobManager.addJobInBackground(new QueueLoginJob(parentObject));

    }
}
