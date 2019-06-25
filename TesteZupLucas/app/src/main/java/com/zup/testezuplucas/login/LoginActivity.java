package com.zup.testezuplucas.login;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.zup.testezuplucas.R;
import com.zup.testezuplucas.home.HomeActivity;
import com.zup.testezuplucas.model.User;
import com.zup.testezuplucas.util.PreferencesController;


interface LoginInterface {
    void loginSuccess(User user);

    void loginFailure();
}


public class LoginActivity extends AppCompatActivity implements LoginInterface {

    LoginController loginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginController = new LoginController(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkIfHasUserSaved();
    }

    public void checkIfHasUserSaved() {
        User pastUser = PreferencesController.getInstance(this).getLoggedUser();
        if (pastUser != null) {
            askIfUserWantToContinueLoggedIn(pastUser);
        }
    }

    public void askIfUserWantToContinueLoggedIn(final User pastUser) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.dialog_hi) + " " + pastUser.getName());
        builder.setMessage(R.string.dialog_welcome_back);
        builder.setPositiveButton(R.string.continue_session, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                goToHome(pastUser);
            }
        });
        builder.setNegativeButton(R.string.another_user, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                logOut();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void logOut() {
        PreferencesController.getInstance(this).deleteLoggedUser();
    }

    public void onClickLogin(View view) {
        setProgressVisibility(true);
        String user = ((EditText) findViewById(R.id.userInput)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordInput)).getText().toString();
        if (isUserValid(user)) {
            if (isPasswordValid(password)) {
                callLogin(user, password);
            } else {
                setPasswordInputError();
                setProgressVisibility(false);
            }
        } else {
            setUserInputError();
            setProgressVisibility(false);
        }
    }

    public void setProgressVisibility(boolean visible) {
        if (visible)
            findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
        else
            findViewById(R.id.progressBar).setVisibility(View.GONE);
    }

    public boolean isUserValid(String user) {
        return loginController.isUserValid(user);
    }

    public boolean isPasswordValid(String password) {
        return loginController.isPasswordValid(password);
    }

    public void setUserInputError() {
        EditText userInput = findViewById(R.id.userInput);
        userInput.setError(getString(R.string.user_input_error));
        userInput.requestFocus();
    }

    public void setPasswordInputError() {
        EditText passwordInput = findViewById(R.id.passwordInput);
        passwordInput.setError(getString(R.string.password_input_error));
        passwordInput.requestFocus();
    }

    public void callLogin(String user, String password) {
        loginController.login(user, password);
    }

    private void goToHome(User user) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginSuccess(User user) {
        PreferencesController.getInstance(this).saveLoggedUser(user);
        setProgressVisibility(false);
        goToHome(user);
    }

    @Override
    public void loginFailure() {
        Toast.makeText(this, "Ops... deu ruim. Tente novamente", Toast.LENGTH_SHORT).show();
    }
}
