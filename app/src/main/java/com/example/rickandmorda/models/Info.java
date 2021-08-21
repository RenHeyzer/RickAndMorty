package com.example.rickandmorda.models;

import com.google.gson.annotations.SerializedName;

public class Info {

    @SerializedName("count")
    private int count;

    @SerializedName("pages")
    private int pages;

    @SerializedName("next")
    private String next;

    @SerializedName("prev")
    private String prev;
}
