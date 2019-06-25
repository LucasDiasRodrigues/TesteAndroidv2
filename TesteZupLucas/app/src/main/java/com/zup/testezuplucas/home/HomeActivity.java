package com.zup.testezuplucas.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.zup.testezuplucas.R;
import com.zup.testezuplucas.login.LoginActivity;
import com.zup.testezuplucas.model.User;
import com.zup.testezuplucas.util.PreferencesController;

import java.text.NumberFormat;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        User user = (User) getIntent().getSerializableExtra("user");
        if (user != null) {
            setUpHeader(user);
        }
        startMainFragment();
    }

    public void setUpHeader(User user) {
        ((TextView) findViewById(R.id.headerUserName)).setText(user.getName());
        ((TextView) findViewById(R.id.headerUserAccount)).setText(user.getCompleteAccountFormated());
        ((TextView) findViewById(R.id.headerUserBalance)).setText(NumberFormat.getCurrencyInstance().format(user.getBalance()));
    }

    public void startMainFragment() {
        RecentOperationsFragment fragment = new RecentOperationsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.bottomFragment, fragment);
        transaction.commitAllowingStateLoss();
    }

    public void onClickLogOut(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.dialog_logout_title));
        builder.setMessage(R.string.dialog_logout_message);
        builder.setPositiveButton(R.string.dialog_logout_yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                logOut();
            }
        });
        builder.setNegativeButton(R.string.dialog_logout_no, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void logOut() {
        PreferencesController.getInstance(this).deleteLoggedUser();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
