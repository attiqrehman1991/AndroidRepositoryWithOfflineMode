/*
 * Copyright (c) 2017.
 * Sample Project Architecture for Repository Model in Android.
 * Developed by Attiq Ur Rehman
 * Senior Software Engineer
 * Systems Limited Lahore
 */

package attiqrao.systems.repository.dao;

import android.content.Context;

public abstract class ParentDAB {
    public abstract void updateDAB(Object object);

    public abstract void unregisterReceiver(Context context);
}
