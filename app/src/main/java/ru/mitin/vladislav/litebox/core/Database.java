package ru.mitin.vladislav.litebox.core;

import android.arch.persistence.room.RoomDatabase;

import ru.mitin.vladislav.litebox.interfaces.dao.SearchResultDao;
import ru.mitin.vladislav.litebox.models.SearchResult;

@android.arch.persistence.room.Database(entities = { SearchResult.class }, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract SearchResultDao getSearchResultDao();
}
