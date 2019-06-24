package com.zup.testezuplucas.login;

import android.util.Log;

import com.zup.testezuplucas.model.UserPOJO;
import com.zup.testezuplucas.util.APICommunicationInterface;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginController {
    APICommunicationInterface APICaller;

    public WeakReference<LoginActivity> activityRef;

    public LoginController(LoginActivity activity) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://bank-app-test.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        activityRef = new WeakReference<>(activity);

        APICaller = retrofit.create(APICommunicationInterface.class);
    }

    public void login(String user, String password) {
        APICaller.logIn(user, password).enqueue(new Callback<UserPOJO>() {
            @Override
            public void onResponse(Call<UserPOJO> call, Response<UserPOJO> response) {
                if (response.body() != null) {
                    LoginController.this.activityRef.get().loginSuccess(response.body().getUserAccount());
                    Log.i("responseLogin", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<UserPOJO> call, Throwable t) {
                LoginController.this.activityRef.get().loginFailure();
            }
        });
    }
}
