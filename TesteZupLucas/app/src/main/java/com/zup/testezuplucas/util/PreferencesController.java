package com.zup.testezuplucas.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.zup.testezuplucas.model.User;

public final class PreferencesController {
    private Context context;
    private SharedPreferences prefs;

    private static PreferencesController INSTANCE;

    private PreferencesController(Context context) {
        this.context = context;
    }

    public static PreferencesController getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new PreferencesController(context);
        }
        return INSTANCE;
    }

    public void saveLoggedUser(User user) {
        prefs = context.getSharedPreferences("TesteNOSQLDB", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("hasUser", true);
        editor.putInt("userId", user.getUserId());
        editor.putString("name", user.getName());
        editor.putString("bankAccount", user.getBankAccount());
        editor.putString("agency", user.getAgency());
        editor.putFloat("balance", user.getBalance());
        editor.commit();
    }

    public User getLoggedUser() {
        prefs = context.getSharedPreferences("TesteNOSQLDB", Context.MODE_PRIVATE);
        if (prefs.getBoolean("hasUser", false)) {
            User user = new User();
            user.setUserId(prefs.getInt("userId", 0));
            user.setName(prefs.getString("name", ""));
            user.setBankAccount(prefs.getString("bankAccount", ""));
            user.setAgency(prefs.getString("agency", ""));
            user.setBalance(prefs.getFloat("balance", 0.0f));
            return user;
        } else {
            return null;
        }
    }
}
