package com.zup.testezuplucas.util;

import com.zup.testezuplucas.model.User;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ValueFormatter {

    public static String formatDate(String unformattedDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = dateFormat.parse(unformattedDate);
            dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            return dateFormat.format(date);
        } catch (ParseException e) {
            return unformattedDate;
        }
    }

    public static String formatCurrency(float value) {
        return NumberFormat.getCurrencyInstance().format(value);
    }

    public static String formatUserAccount(User user) {
        return user.getBankAccount() + " / " + maskAgencyNumber(user.getAgency());
    }

    private static String maskAgencyNumber(String agency) {
        String str = agency;
        if (str.length() > 7) {
            str = new StringBuilder(str).insert(str.length() - 1, "-")
                    .insert(str.length() - 7, ".")
                    .toString();
        }
        return str;
    }
}
