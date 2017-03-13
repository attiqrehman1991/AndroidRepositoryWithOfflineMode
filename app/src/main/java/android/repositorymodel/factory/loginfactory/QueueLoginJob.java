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
import android.repositorymodel.utilities.StaticInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;

/**
 * Created by Attiq ur Rehman on 3/13/2017.
 * Senior Software Engineer at Systems Ltd
 * attiq.ur.rehman1991@gmail.com
 */

public class QueueLoginJob extends Job {

    private ParentObject parentObject;
    private ParentDAB parentDAB;

    public QueueLoginJob(ParentObject parentObject, ParentDAB parentDAB) {
        super(new Params(Priority.MID).requireNetwork().persist());
        this.parentObject = parentObject;
        this.parentDAB = parentDAB;
    }

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        LoginStore store = LoginFactory.getLoginFactory().getStore(StaticInfo.NETWORK_TYPE, parentDAB);
        store.save(parentObject);
    }

    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {

    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(@NonNull Throwable throwable, int runCount, int maxRunCount) {
//        if (throwable instanceof ErrorRequestException) {
//            ErrorRequestException error = (ErrorRequestException) throwable;
//            int statusCode = error.getResponse().raw().code();
//            if (statusCode >= 400 && statusCode < 500) {
//                return RetryConstraint.CANCEL;
//            }
//        }
        return null;
    }
}
