package com.altran.brastlewark.user.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Athmos on 30/11/2017.
 */

public class User {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("age")
    private int age;

    @SerializedName("weight")
    private float weight;

    @SerializedName("height")
    private float height;

    @SerializedName("hair_color")
    private String hairColor;

    @SerializedName("professions")
    private List<String> professions;

    @SerializedName("friends")
    private List<String> friends;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public int getAge() {
        return age;
    }

    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }

    public String getHairColor() {
        return hairColor;
    }

    public List<String> getProfessions() {
        return professions;
    }

    public List<String> getFriends() {
        return friends;
    }
}
