package com.altran.brastlewark.main.view.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.altran.brastlewark.BrastlewarkBaseApplication;
import com.altran.brastlewark.R;
import com.altran.brastlewark.common.view.BaseView;
import com.altran.brastlewark.main.injection.modules.MainModule;
import com.altran.brastlewark.main.view.presenter.MainPresenter;
import com.altran.brastlewark.user.domain.model.Brastlewark;
import com.altran.brastlewark.user.domain.model.User;
import com.altran.brastlewark.user.view.dialog.DialogFragmentUser;
import com.altran.brastlewark.user.view.adapter.UserAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements BaseView, MainPresenter.View {

    private static final int GET_SELECTED_FILTERS = 1;
    public static final String MAX_AGE = "MAX_AGE";
    public static final String MIN_AGE = "MIN_AGE";

    @Inject
    MainPresenter mainPresenter;

    @BindView(R.id.User_Recycler)
    protected RecyclerView recyclerUser;

    @BindView(R.id.Progress_Bar)
    protected LinearLayout progressBar;

    @BindView(R.id.Edit_Text_Search)
    protected EditText editTextSearch;

    private UserAdapter userAdapter;

    private Brastlewark brastlewark;
    private List<User> users;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((BrastlewarkBaseApplication) getApplicationContext()).getApplicationComponent()
                .getMainComponent(new MainModule()).inject(this);
        mainPresenter.attachView(this);
        ButterKnife.bind(this);
        editTextSearch.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateUserList(s.toString());
            }
        });
        onInit();
    }

    private void updateUserList(String userFilter) {
        List<User> newListUser = new ArrayList<>();
        for (User user : users) {
            if (user.getName().toLowerCase().contains(userFilter.toLowerCase())) {
                newListUser.add(user);
            }
        }
        userAdapter.setData(newListUser);
        userAdapter.notifyDataSetChanged();
    }

    @Override
    public void onInit() {
        mainPresenter.getUsers();
    }

    @Override
    public void onGetUser(Brastlewark brastlewark, boolean calledByPresenter) {
        if (brastlewark != null && calledByPresenter) {
            this.brastlewark = brastlewark;
        }
        if (brastlewark != null && brastlewark.getBrastlewark() != null) {
            if (calledByPresenter)
                this.users = brastlewark.getBrastlewark();
            List<User> userFiltred = brastlewark.getBrastlewark();
            progressBar.setVisibility(View.GONE);
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            recyclerUser.setLayoutManager(staggeredGridLayoutManager);
            this.userAdapter = new UserAdapter(this, userFiltred);
            recyclerUser.setAdapter(this.userAdapter);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GET_SELECTED_FILTERS) {
            if (resultCode == RESULT_OK) {
                int maxAge = data.getIntExtra(MAX_AGE, 0);
                int minAge = data.getIntExtra(MIN_AGE, 0);
                editTextSearch.setText("");
                dataFilter(minAge, maxAge);
            }
        }
    }

    private void dataFilter(int minAge, int maxAge) {
        Brastlewark brastlewarkFilter = new Brastlewark();
        List<User> newList = new ArrayList<>();

        for (User user : users) {
            if (user.getAge() >= minAge && user.getAge() <= maxAge) {
                newList.add(user);
            }
        }
        brastlewarkFilter.setBrastlewark(newList);
        onGetUser(brastlewarkFilter, false);
    }

    public void onClickUser(User user) {
        showDialog(user);
    }

    void showDialog(User user) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        DialogFragmentUser dialogFragmentUser = DialogFragmentUser.newInstance();
        dialogFragmentUser.setUser(user);
        dialogFragmentUser.show(ft, "dialog");
    }

    @OnClick(R.id.Button_Filter)
    public void onClickFilters() {
        Intent intent = new Intent(this, FilterActivity.class);
        intent.putExtra(MAX_AGE, brastlewark.getMaxAge());
        startActivityForResult(intent, GET_SELECTED_FILTERS);
    }
}
