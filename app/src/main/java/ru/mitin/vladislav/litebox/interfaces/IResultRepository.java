package ru.mitin.vladislav.litebox.interfaces;

import java.util.List;

import io.reactivex.Observable;
import ru.mitin.vladislav.litebox.models.SearchResult;

public interface IResultRepository {
    Observable<List<SearchResult>> get(final String text, boolean force);
}
