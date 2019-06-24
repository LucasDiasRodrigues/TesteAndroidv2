package com.zup.testezuplucas.model;

import com.google.gson.annotations.SerializedName;

public class UserPOJO {
    @SerializedName("userAccount")
    private User userAccount;

    public User getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(User userAccount) {
        this.userAccount = userAccount;
    }
}
