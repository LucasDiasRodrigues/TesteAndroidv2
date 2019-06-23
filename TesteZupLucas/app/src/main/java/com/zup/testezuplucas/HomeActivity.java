package com.zup.testezuplucas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        startMainFragment();
    }

    public void startMainFragment(){
        RecentOperationsFragment fragment = new RecentOperationsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.bottomFragment, fragment);
        transaction.commitAllowingStateLoss();
    }
}
