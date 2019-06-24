package com.zup.testezuplucas.login;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.zup.testezuplucas.R;
import com.zup.testezuplucas.home.HomeActivity;
import com.zup.testezuplucas.model.User;
import com.zup.testezuplucas.util.PreferencesController;


interface loginInterface {
    void loginSuccess(User user);

    void loginFailure();
}


public class LoginActivity extends AppCompatActivity implements loginInterface {

    LoginController loginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginController = new LoginController(this);
    }

    public void onClickLogin(View view) {
        if (isFieldsValid("", "")) {
            callLogin();
        }
    }

    public boolean isFieldsValid(String user, String password) {

        // todo Validacao de campos

        return true;
    }

    public void callLogin() {
        loginController.login("test_user", "Test@1");
    }

    private void goToHome(User user){
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    @Override
    public void loginSuccess(User user) {
        PreferencesController.getInstance(this).saveLoggedUser(user);
        goToHome(user);
    }

    @Override
    public void loginFailure() {

    }
}
