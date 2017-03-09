/*
 * Copyright (c) 2017.
 * Sample Project Architecture for Repository Model in Android.
 * Developed by Attiq Ur Rehman
 * Senior Software Engineer
 * Systems Limited Lahore
 */

package android.repositorymodel.view;

import android.os.Bundle;
import android.repositorymodel.R;
import android.repositorymodel.presenter.IPresenter;
import android.repositorymodel.presenter.Presenter;
import android.repositorymodel.utilities.StaticInfo;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements IView {

    IPresenter iPresenter;
    boolean internet = false;
    private TextView tv_response;
    private EditText ed_username, ed_password;
    private Button buttonSubmit, buttonAsyncTask, buttonRx, buttonRetrofit, buttonVolley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iPresenter = Presenter.getInstance();
        initWiddgets();
        initListener();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void initWiddgets() {
        tv_response = (TextView) findViewById(R.id.tv_response);
        ed_username = (EditText) findViewById(R.id.ed_username);
        ed_password = (EditText) findViewById(R.id.ed_password);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        buttonAsyncTask = (Button) findViewById(R.id.buttonAsyncTask);
        buttonRx = (Button) findViewById(R.id.buttonRx);
        buttonRetrofit = (Button) findViewById(R.id.buttonRetrofit);
        buttonVolley = (Button) findViewById(R.id.buttonVolley);
    }

    private void initListener() {
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ed_username.getText().toString();
                String password = ed_password.getText().toString();
                MainActivity.this.findViewById(R.id.progress).setVisibility(View.VISIBLE);
                buttonSubmit.setEnabled(false);
                iPresenter.setView(MainActivity.this);
                iPresenter.onClick(username, password, isNetworkAvailable());
            }
        });
        buttonAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticInfo.NETWORK_TYPE = StaticInfo.ASYNC_SERVICES;
            }
        });
        buttonRx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticInfo.NETWORK_TYPE = StaticInfo.RX_SERVICES;
            }
        });
        buttonRetrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticInfo.NETWORK_TYPE = StaticInfo.RETROFIT_SERVICES;
            }
        });
        buttonVolley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                internet = !internet;
                if (internet)
                    buttonVolley.setText("Y");
                else
                    buttonVolley.setText("N");
            }
        });
    }

    @Override
    protected boolean isNetworkAvailable() {
        return super.isNetworkAvailable();
//        return internet;
    }

    @Override
    public void loginResponse(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        MainActivity.this.findViewById(R.id.progress).setVisibility(View.GONE);
        buttonSubmit.setEnabled(true);
        tv_response.setText(message);
    }
}
