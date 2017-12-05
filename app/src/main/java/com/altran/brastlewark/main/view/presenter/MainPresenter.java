package com.altran.brastlewark.main.view.presenter;

import com.altran.brastlewark.common.view.BasePresenter;
import com.altran.brastlewark.user.domain.model.Brastlewark;
import com.altran.brastlewark.user.domain.usecase.GetUser;

/**
 * Created by Athmos on 30/10/2017.
 */

public class MainPresenter extends BasePresenter {

    GetUser getUser;

    public MainPresenter(GetUser getUser) {
        this.getUser = getUser;
    }


    public void getUsers() {
        getUser.execute(new getUserListener() {
            @Override
            public void onSuccess(Brastlewark brastlewark) {
                if (isViewAttached()) {
                    ((View) getView()).onGetUser(brastlewark, true);
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    public interface getUserListener {

        void onSuccess(Brastlewark brastlewark);

        void onError();
    }

    public interface View {

        void onGetUser(Brastlewark brastlewark, boolean calledByPresenter);

    }


}
