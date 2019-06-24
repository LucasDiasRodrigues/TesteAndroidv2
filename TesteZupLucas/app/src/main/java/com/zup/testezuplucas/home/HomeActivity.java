package com.zup.testezuplucas.home;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.zup.testezuplucas.R;
import com.zup.testezuplucas.model.User;

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
        ((TextView)findViewById(R.id.headerUserName)).setText(user.getName());
        ((TextView)findViewById(R.id.headerUserAccount)).setText(user.getCompleteAccount());
        ((TextView)findViewById(R.id.headerUserBalance)).setText(String.valueOf(user.getBalance()));
    }

    public void startMainFragment() {
        RecentOperationsFragment fragment = new RecentOperationsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.bottomFragment, fragment);
        transaction.commitAllowingStateLoss();
    }

    public void onClickLogOut(View view){
     // todo   logout
    }
}
