/*
 * Copyright (c) 2017.
 * Sample Project Architecture for Repository Model in Android.
 * Developed by Attiq Ur Rehman
 * Senior Software Engineer
 * Systems Limited Lahore
 */

package android.repositorymodel.presenter;

import android.repositorymodel.view.IView;

public interface IPresenter {

    void setView(IView iView);

    void onClick(String username, String message, boolean isNetAvailable);
}
