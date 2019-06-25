package com.zup.testezuplucas.home;

import com.zup.testezuplucas.model.ListOperationsPOJO;
import com.zup.testezuplucas.model.User;
import com.zup.testezuplucas.util.APICommunicationInterface;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OperationsFragmentController {
    APICommunicationInterface APICaller;

    public WeakReference<RecentOperationsFragment> fragmentRef;

    public OperationsFragmentController(RecentOperationsFragment fragment) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://bank-app-test.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        fragmentRef = new WeakReference<>(fragment);

        APICaller = retrofit.create(APICommunicationInterface.class);
    }

    public void getOperarionsList(User user) {
        APICaller.loadRecentOperations(user.getUserId()).enqueue(new Callback<ListOperationsPOJO>() {
            @Override
            public void onResponse(Call<ListOperationsPOJO> call, Response<ListOperationsPOJO> response) {
                if (response.body() != null) {
                    fragmentRef.get().listOperationsSuccess(response.body().getOperations());
                }
            }

            @Override
            public void onFailure(Call<ListOperationsPOJO> call, Throwable t) {
                fragmentRef.get().listOperationsFailure();
            }
        });

    }


}
