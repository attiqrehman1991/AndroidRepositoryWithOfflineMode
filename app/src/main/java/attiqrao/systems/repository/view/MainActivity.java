/*
 * Copyright (c) 2017.
 * Sample Project Architecture for Repository Model in Android.
 * Developed by Attiq Ur Rehman
 * Senior Software Engineer
 * Systems Limited Lahore
 */

package attiqrao.systems.repository.view;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import attiqrao.systems.repository.R;
import attiqrao.systems.repository.presenter.IPresenter;
import attiqrao.systems.repository.presenter.Presenter;
import attiqrao.systems.repository.utilities.StaticInfo;

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
    protected void onDestroy() {
        iPresenter.unRegisterReceiver(this);
        super.onDestroy();
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
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(Presenter.LOGIN_SUCCESSFUL);
                iPresenter.onClick(username, password, isNetworkAvailable(), MainActivity.this, intentFilter);
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
