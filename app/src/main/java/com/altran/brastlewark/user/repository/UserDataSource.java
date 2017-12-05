package com.altran.brastlewark.user.repository;

import com.altran.brastlewark.common.repository.ApiRepository;
import com.altran.brastlewark.main.view.presenter.MainPresenter;
import com.altran.brastlewark.user.domain.model.Brastlewark;
import com.altran.brastlewark.user.domain.model.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Athmos on 30/11/2017.
 */

public class UserDataSource {

    protected ApiRepository apiRepository;

    public UserDataSource(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    public void getUsers(MainPresenter.getUserListener getUserListener) {
        Call<Brastlewark> call = apiRepository.getUsers();
         call.enqueue(new Callback<Brastlewark>() {
            @Override
            public void onResponse(Call<Brastlewark> call, Response<Brastlewark> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && !response.body().getBrastlewark().isEmpty())
                    getUserListener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<Brastlewark> call, Throwable t) {
            }
        });
    }
}
