package com.altran.brastlewark.common.repository;

import com.altran.brastlewark.user.domain.model.Brastlewark;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by Athmos on 30/11/2017.
 */

public interface ApiRepository {

    String BASE_URL_DEV = "https://raw.githubusercontent.com/";

    @Headers({
            "Content-Type: application/json",
    })
    @GET("rrafols/mobile_test/master/data.json")
    Call<Brastlewark> getUsers();

}
