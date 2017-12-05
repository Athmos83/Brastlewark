package com.altran.brastlewark.user.view.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.altran.brastlewark.R;
import com.altran.brastlewark.user.domain.model.User;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Athmos on 01/12/2017.
 */

public class DialogFragmentUser extends DialogFragment {

    private User user;

    @BindView(R.id.User_Name_Dialog)
    protected TextView userNameDialog;

    @BindView(R.id.User_Image_Dialog)
    protected ImageView userImageDialog;

    @BindView(R.id.User_Height)
    protected TextView userHeight;

    @BindView(R.id.User_Weight)
    protected TextView userWeight;

    @BindView(R.id.Hair_Color)
    protected TextView userHairColor;

    @BindView(R.id.User_Age_Dialog)
    protected TextView userAge;

    @BindView(R.id.User_Professions)
    protected LinearLayout professionLayout;

    @BindView(R.id.User_Friends)
    protected LinearLayout friendsLayout;

    public static DialogFragmentUser newInstance() {
        return new DialogFragmentUser();
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void onResume() {
        super.onResume();

        int width = getResources().getDimensionPixelSize(R.dimen.popup_width);
        int height = getResources().getDimensionPixelSize(R.dimen.popup_height);
        getDialog().getWindow().setLayout(width, height);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog_user, container, false);
        ButterKnife.bind(this, v);
        userNameDialog.setText(user.getName());
        Picasso.with(getActivity())
                .load(user.getThumbnail())
                .into(userImageDialog);
        userHeight.setText("Height : " + user.getHeight());
        userWeight.setText("Weight : " + user.getWeight());
        if (user.getHairColor() != null)
           userHairColor.setText("Hair color : " + user.getHairColor());
        userAge.setText(user.getAge() + " years old");
        if (!user.getFriends().isEmpty()) {
            TextView friendLabel = new TextView(getActivity());
            friendLabel.setTextSize(20);
            friendLabel.setText(R.string.friends_label);
            friendsLayout.addView(friendLabel);

            for (String friend : user.getFriends()) {
                TextView friendName = new TextView(getActivity());
                friendName.setText(friend);
                friendsLayout.addView(friendName);
            }
        }

        if (!user.getProfessions().isEmpty()) {
            TextView professionsLabel = new TextView(getActivity());
            professionsLabel.setTextSize(20);
            professionsLabel.setText(R.string.professions_label);
            professionLayout.addView(professionsLabel);

            for (String professions : user.getProfessions()) {
                TextView professionName = new TextView(getActivity());
                professionName.setText(professions);
                professionLayout.addView(professionName);
            }
        }
        return v;
    }
}
