/*
 * Copyright (c) 2017.
 * Sample Project Architecture for Repository Model in Android.
 * Developed by Attiq Ur Rehman
 * Senior Software Engineer
 * Systems Limited Lahore
 */

package attiqrao.systems.repository.view;

import attiqrao.systems.repository.utilities.ConnectivityStatus;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Attiq ur Rehman on 3/9/2017.
 * Senior Software Engineer at Systems Ltd
 * attiq.ur.rehman1991@gmail.com
 */

public class BaseActivity extends AppCompatActivity {

    protected boolean isNetworkAvailable() {
//        ConnectivityManager connectivityManager
//                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        return ConnectivityStatus.isConnected(this);
    }
}
