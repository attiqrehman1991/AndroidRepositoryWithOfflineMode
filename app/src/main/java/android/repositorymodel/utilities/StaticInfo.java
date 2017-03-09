/*
 * Copyright (c) 2017.
 * Sample Project Architecture for Repository Model in Android.
 * Developed by Attiq Ur Rehman
 * Senior Software Engineer
 * Systems Limited Lahore
 */

package android.repositorymodel.utilities;

import com.squareup.otto.Bus;

/**
 * Created by Attiq ur Rehman on 3/7/2017.
 * Senior Software Engineer at Systems Ltd
 * attiq.ur.rehman1991@gmail.com
 */

public class StaticInfo {
    public final static int ASYNC_SERVICES = 1;
    public final static int RX_SERVICES = 2;
    public final static int RETROFIT_SERVICES = 3;

    public static int NETWORK_TYPE = RX_SERVICES;
    public static final String BASE_URL="http://localhost/";
}
