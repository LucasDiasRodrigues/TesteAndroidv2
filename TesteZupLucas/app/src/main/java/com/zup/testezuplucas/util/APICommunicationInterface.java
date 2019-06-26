package com.zup.testezuplucas.util;

import com.zup.testezuplucas.model.ListOperationsPOJO;
import com.zup.testezuplucas.model.Operation;
import com.zup.testezuplucas.model.User;
import com.zup.testezuplucas.model.UserPOJO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APICommunicationInterface {
    @FormUrlEncoded
    @POST("login")
    Call<UserPOJO> logIn(@Field("user") String user, @Field("password") String password);

    @GET("statements/{userId}")
    Call<ListOperationsPOJO> loadRecentOperations(@Path("userId") int id);
}
