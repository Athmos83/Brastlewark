package com.altran.brastlewark.user.domain.usecase;

import com.altran.brastlewark.main.view.presenter.MainPresenter;
import com.altran.brastlewark.user.repository.UserRepository;

/**
 * Created by Athmos on 30/11/2017.
 */

public class GetUser {

    private UserRepository userRepository;

    public GetUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(MainPresenter.getUserListener getUserListener) {
        userRepository.getUsers(getUserListener);
    }
}
