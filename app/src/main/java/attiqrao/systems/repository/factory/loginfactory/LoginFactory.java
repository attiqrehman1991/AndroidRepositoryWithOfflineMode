/*
 * Copyright (c) 2017.
 * Sample Project Architecture for Repository Model in Android.
 * Developed by Attiq Ur Rehman
 * Senior Software Engineer
 * Systems Limited Lahore
 */

package attiqrao.systems.repository.factory.loginfactory;

import attiqrao.systems.repository.dao.ParentDAB;
import attiqrao.systems.repository.utilities.StaticInfo;

public class LoginFactory {
    private static LoginFactory loginFactory;

    private LoginFactory() {
    }

    public static synchronized LoginFactory getLoginFactory() {
        if (loginFactory == null) {
            loginFactory = new LoginFactory();
        }
        return loginFactory;
    }

    public LoginStore getStore(int storeType, ParentDAB parentDAB) {
        switch (storeType) {
            case StaticInfo.ASYNC_SERVICES:
                return new AsyncLogin(parentDAB);
            case StaticInfo.RX_SERVICES:
                return new RXLogin(parentDAB);
            case StaticInfo.RETROFIT_SERVICES:
                return new RetrofitLogin(parentDAB);
            default:
                return null;
        }
    }
}
