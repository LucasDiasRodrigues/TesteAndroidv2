package com.zup.testezuplucas;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClickLogin(View view) {
        if (isFieldsValid("", "")) {

            //Chamada do método

            startActivity(new Intent(this, HomeActivity.class));
        }
    }

    public boolean isFieldsValid(String user, String password) {

        //Validacao de campos

        return true;
    }
}
