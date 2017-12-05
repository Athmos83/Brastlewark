package com.altran.brastlewark.user.repository;

import com.altran.brastlewark.main.view.presenter.MainPresenter;

/**
 * Created by Athmos on 30/11/2017.
 */

public class UserRepository {

    protected UserDataSource userDataSource;

    public UserRepository(UserDataSource userDataSource) {
        this.userDataSource = userDataSource;
    }

    public void getUsers(MainPresenter.getUserListener getUserListener) {
        userDataSource.getUsers(getUserListener);
    }
}
