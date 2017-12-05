package com.altran.brastlewark.user.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Athmos on 30/11/2017.
 */

public class Brastlewark {

    @SerializedName("Brastlewark")
    private List<User> brastlewark;

    public List<User> getBrastlewark() {
        return brastlewark;
    }

    public void setBrastlewark(List<User> brastlewark) {
        this.brastlewark = brastlewark;
    }

    public int getMaxAge() {
        int ageMax = 0;
        for (User user : getBrastlewark()) {
            if (user.getAge() > ageMax)
                ageMax = user.getAge();
        }
        return ageMax;
    }

}
