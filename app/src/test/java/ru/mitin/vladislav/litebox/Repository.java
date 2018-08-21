package ru.mitin.vladislav.litebox;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import ru.mitin.vladislav.litebox.interfaces.IResultRepository;
import ru.mitin.vladislav.litebox.models.SearchResult;

public class Repository implements IResultRepository {
    private boolean emptyResult;

    public boolean isEmptyResult() {
        return emptyResult;
    }

    public void setEmptyResult(boolean emptyResult) {
        this.emptyResult = emptyResult;
    }

    @Override
    public Observable<List<SearchResult>> get(String text, boolean force) {
        List<SearchResult> results = new ArrayList<>();
        results.add(new SearchResult());
        results.add(new SearchResult());

        return emptyResult ? Observable.<List<SearchResult>>empty() : Observable.fromArray(results);
    }
}
