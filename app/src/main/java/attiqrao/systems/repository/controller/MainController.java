/*
 * Copyright (c) 2017.
 * Sample Project Architecture for Repository Model in Android.
 * Developed by Attiq Ur Rehman
 * Senior Software Engineer
 * Systems Limited Lahore
 */

package attiqrao.systems.repository.controller;

import android.content.BroadcastReceiver;
import android.content.Context;

/**
 * Created by Attiq ur Rehman on 3/7/2017.
 * Senior Software Engineer at Systems Ltd
 * attiq.ur.rehman1991@gmail.com
 */

public abstract class MainController {
    public Context context;
    public BroadcastReceiver broadcastReceiver;
    public abstract void updateResponse(Object object);
}
