package com.zup.testezuplucas.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("userId")
    private int userId;
    @SerializedName("name")
    private String name;
    @SerializedName("bankAccount")
    private String bankAccount;
    @SerializedName("agency")
    private String agency;
    @SerializedName("balance")
    private float balance;

    public User() {
    }

    public User(int userId, String name, String bankAccount, String agency, float balance) {
        this.userId = userId;
        this.name = name;
        this.bankAccount = bankAccount;
        this.agency = agency;
        this.balance = balance;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getCompleteAccountFormated() {
        return bankAccount  + " / " + maskAgencyNumber();
    }

    private String maskAgencyNumber(){
        String str = getAgency();
        if(str.length() > 7){
            str = new StringBuilder(str).insert(str.length()-1, "-")
                    .insert(str.length() - 8, ".")
                    .toString();
        }

        return str;
    }

    @NonNull
    @Override
    public String toString() {
        return name + "--" + bankAccount + "--" + agency;
    }
}
