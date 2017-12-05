package com.altran.brastlewark.user.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.altran.brastlewark.R;
import com.altran.brastlewark.main.view.activity.MainActivity;
import com.altran.brastlewark.user.domain.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Athmos on 30/11/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context context;
    private List<User> users;

    public UserAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    public void setData(List<User> users) {
        this.users = users;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_user, viewGroup, false);
        return new UserViewHolder(v);    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = users.get(position);
        if (getItemCount() == 0) {
            holder.rootView.setVisibility(View.GONE);
        } else {
            holder.userName.setText(user.getName());
            Picasso.with(context)
                    .load(user.getThumbnail())
                    .into(holder.userImage);
            holder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)context).onClickUser(user);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.Root_Layout)
        public CardView rootView;

        @BindView(R.id.User_Name)
        public TextView userName;

        @BindView(R.id.User_Image)
        public ImageView userImage;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
