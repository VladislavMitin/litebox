package ru.mitin.vladislav.litebox.dto;

import com.google.gson.annotations.SerializedName;

public class SearchResultDto {
    @SerializedName("title")
    public String title;
    @SerializedName("link")
    public String url;
    @SerializedName("snippet")
    public String description;
}
