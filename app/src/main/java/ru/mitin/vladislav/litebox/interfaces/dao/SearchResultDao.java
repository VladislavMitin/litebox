package ru.mitin.vladislav.litebox.interfaces.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;
import ru.mitin.vladislav.litebox.models.SearchResult;

@Dao
public interface SearchResultDao {
    @Insert
    void insertAll(List<SearchResult> searchResult);

    @Query("SELECT * FROM searchresult WHERE searchText = :searchTextId")
    Single<List<SearchResult>> getBySearchText(String searchTextId);

    @Query("DELETE FROM searchresult WHERE searchText = :searchTextId")
    void deleteBySearchText(String searchTextId);
}
