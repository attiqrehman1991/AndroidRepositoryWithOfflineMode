/*
 * Copyright (c) 2017.
 * Sample Project Architecture for Repository Model in Android.
 * Developed by Attiq Ur Rehman
 * Senior Software Engineer
 * Systems Limited Lahore
 */

package attiqrao.systems.repository.dao;

import android.app.job.JobInfo;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Job;

import org.json.JSONObject;

import attiqrao.systems.repository.RepositoryApplication;
import attiqrao.systems.repository.controller.MainController;
import attiqrao.systems.repository.factory.loginfactory.LoginFactory;
import attiqrao.systems.repository.factory.loginfactory.LoginStore;
import attiqrao.systems.repository.model.ParentObject;
import attiqrao.systems.repository.model.User;
import attiqrao.systems.repository.services.LoginFireBaseJobService;
import attiqrao.systems.repository.services.LoginJobService;
import attiqrao.systems.repository.utilities.StaticInfo;
import io.realm.Realm;

public class LoginDAB extends ParentDAB {

    public final static String LOGIN_SUCCESSFUL = "LOGIN_SUCCESSFUL";
    public static ParentObject parentObject;
    private MainController mainController;
    private LoginReceiver loginReceiver;

    @Override
    public void updateDAB(Object object) {
        JSONObject jsonObject = (JSONObject) object;

        String username = "", password = "";
        try {
            username = jsonObject.getString("username");
            password = jsonObject.getString("password");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.delete(User.class);

        User user = realm.createObject(User.class);
        user.setUsername(username);
        user.setPassword(password);
        realm.commitTransaction();

        mainController.updateResponse(user);
    }

    public void saveData(ParentObject parentObject, MainController mainController, boolean isInternet, Context context, IntentFilter intentFilter) {
        this.mainController = mainController;

        if (isInternet) {
            if (StaticInfo.JOB_QUEUE) {
                loginReceiver = new LoginReceiver();
                context.registerReceiver(loginReceiver, intentFilter);
                this.parentObject = parentObject;
                // After adding request in the queue, the request call is executed -- check by calling onBackPress function
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    JobInfo.Builder builder = new JobInfo.Builder( 1,
                            new ComponentName( context.getPackageName(),
                                    LoginJobService.class.getName() ) );
                    builder.setMinimumLatency(1 * 1000); // wait at least
                    builder.setOverrideDeadline(5 * 1000); // maximum delay
                    builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
                    RepositoryApplication.getJobScheduler().schedule(builder.build());
                } else {
//                    Bundle myExtrasBundle = new Bundle();
//                    myExtrasBundle.putParcelable("parentObject", parentObject);
                    Job myJob = RepositoryApplication.getDispatcher().newJobBuilder()
                            .addConstraint(Constraint.ON_ANY_NETWORK)
//                            .setReplaceCurrent(false)
                            // one-off job
//                            .setRecurring(false)
                            .setService(LoginFireBaseJobService.class) // the JobService that will be called
                            .setTag("login-tag")        // uniquely identifies the job
//                            .setExtras(myExtrasBundle)
                            .build();
                    RepositoryApplication.getDispatcher().mustSchedule(myJob);
                }
            } else {
                // to make direct network call
                LoginStore store = LoginFactory.getLoginFactory().getStore(StaticInfo.NETWORK_TYPE, LoginDAB.this);
                store.save(parentObject, null);
            }
        } else {
            Realm realm = Realm.getDefaultInstance();
            User user = realm.where(User.class).findFirst();
            mainController.updateResponse(user);
        }
    }

    @Override
    public void unregisterReceiver(Context context) {
        context.unregisterReceiver(loginReceiver);
    }

    private class LoginReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(LOGIN_SUCCESSFUL)) {

                String jsonString = intent.getStringExtra("json");
                try {
                    updateDAB(new JSONObject(jsonString));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
