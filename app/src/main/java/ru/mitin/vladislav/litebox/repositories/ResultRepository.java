package ru.mitin.vladislav.litebox.repositories;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import ru.mitin.vladislav.litebox.core.Constants;
import ru.mitin.vladislav.litebox.dto.ResponseDto;
import ru.mitin.vladislav.litebox.dto.SearchResultDto;
import ru.mitin.vladislav.litebox.interfaces.IGoogleSearchService;
import ru.mitin.vladislav.litebox.interfaces.IResultRepository;
import ru.mitin.vladislav.litebox.interfaces.dao.SearchResultDao;
import ru.mitin.vladislav.litebox.models.SearchResult;

public class ResultRepository implements IResultRepository {
    private IGoogleSearchService service;
    private SearchResultDao dao;

    public ResultRepository(IGoogleSearchService service, SearchResultDao dao) {
        this.service = service;
        this.dao = dao;
    }

    public Observable<List<SearchResult>> get(final String text, boolean force) {
        if(force) {
            return service.search(Constants.GOOGLE_API_KEY, Constants.GOOGLE_CX, text)
                    .map(new Function<ResponseDto, List<SearchResult>>() {
                        @Override
                        public List<SearchResult> apply(ResponseDto responseDto) throws Exception {
                            return getSearchResult(responseDto);
                        }
                    })
                    .doOnNext(new Consumer<List<SearchResult>>() {
                        @Override
                        public void accept(List<SearchResult> searchResults) throws Exception {
                            for(SearchResult searchResult : searchResults) {
                                searchResult.searchText = text;
                            }

                            dao.deleteBySearchText(text);
                            dao.insertAll(searchResults);
                        }
                    });
        } else  {
            return dao.getBySearchText(text).toObservable();
        }
    }

    private List<SearchResult> getSearchResult(ResponseDto dto) {
        List<SearchResult> result = new ArrayList<>();

        if(dto.result != null) {
            for (SearchResultDto searchResultDto : dto.result) {
                SearchResult searchResult = new SearchResult();
                searchResult.title = searchResultDto.title;
                searchResult.url = searchResultDto.url;
                searchResult.description = searchResultDto.description;

                result.add(searchResult);
            }
        }

        return result;
    }
}
