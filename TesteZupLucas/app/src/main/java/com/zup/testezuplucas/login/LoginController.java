package com.zup.testezuplucas.login;

import android.util.Log;

import com.zup.testezuplucas.R;
import com.zup.testezuplucas.model.UserPOJO;
import com.zup.testezuplucas.util.APICommunicationInterface;

import java.lang.ref.WeakReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public boolean isUserValid(String user) {
        Pattern patternCPF;
        Matcher matcherCPF;

        Pattern patternEmail;
        Matcher matcherEmail;

        final String CPF_PATTERN = "[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}";
        final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

        patternCPF = Pattern.compile(CPF_PATTERN);
        matcherCPF = patternCPF.matcher(user);

        patternEmail = Pattern.compile(EMAIL_PATTERN);
        matcherEmail = patternEmail.matcher(user);

        return (matcherCPF.matches() || matcherEmail.matches());
    }

    public boolean isPasswordValid(String password) {
        Pattern pattern;
        Matcher matcherPassword;

        final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#%!*:;?$_\\-.,%]).{4,20})";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcherPassword = pattern.matcher(password);

        return matcherPassword.matches();
    }
}
