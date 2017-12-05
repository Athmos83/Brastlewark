package com.altran.brastlewark.user.injection.module;

import com.altran.brastlewark.common.repository.ApiRepository;
import com.altran.brastlewark.user.domain.usecase.GetUser;
import com.altran.brastlewark.user.repository.UserDataSource;
import com.altran.brastlewark.user.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Athmos on 30/11/2017.
 */

@Module
public class UserModule {

    @Provides @Singleton
    UserRepository providesUserRepository(UserDataSource userDataSource) {
        return new UserRepository(userDataSource);
    }

    @Provides @Singleton
    UserDataSource providesUserDataSource(ApiRepository apiRepository) {
        return new UserDataSource(apiRepository);
    }
    // usecases

    @Provides @Singleton
    public GetUser providesGetUser(UserRepository userRepository) {
        return new GetUser(userRepository);
    }

}
