package com.altran.brastlewark.common.injection.modules;

import com.altran.brastlewark.common.repository.ApiRepository;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Athmos on 30/11/2017.
 */

@Module
public class DataModule {

    @Provides @Singleton
    Gson providesGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides @Singleton
    OkHttpClient providesOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides @Singleton
    Retrofit providesRetrofit(Gson gson, OkHttpClient okHttpClient) {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(ApiRepository.BASE_URL_DEV).client(okHttpClient).build();
        return retrofit;
    }

    @Provides @Singleton
    ApiRepository providesApiRepository(Retrofit retrofit) {
        return retrofit.create(ApiRepository.class);
    }
}