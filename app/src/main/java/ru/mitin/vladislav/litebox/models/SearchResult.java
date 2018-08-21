package ru.mitin.vladislav.litebox.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class SearchResult {
    @PrimaryKey(autoGenerate = true) public long id;
    public String searchText;
    public String title;
    public String url;
    public String description;
}
