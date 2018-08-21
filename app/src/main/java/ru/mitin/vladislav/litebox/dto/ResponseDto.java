package ru.mitin.vladislav.litebox.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDto {
    @SerializedName("items")
    public List<SearchResultDto> result;
}
