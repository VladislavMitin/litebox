package ru.mitin.vladislav.litebox.presenters;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ru.mitin.vladislav.litebox.R;
import ru.mitin.vladislav.litebox.interfaces.IResultRepository;
import ru.mitin.vladislav.litebox.models.SearchResult;

public class GoogleSearchPresenter {
    private IGoogleSearchView view;
    private IResultRepository resultRepository;
    private Context context;

    public GoogleSearchPresenter(Context context, IGoogleSearchView view, IResultRepository resultRepository) {
        this.context = context;
        this.view = view;
        this.resultRepository = resultRepository;
    }

    public void search(final String text, final boolean force) {
        view.showProgressBar();

        resultRepository.get(text, force)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<SearchResult>>() {
                    @Override
                    public void accept(List<SearchResult> results) throws Exception {
                        List<SearchResultViewDto> viewDto = new ArrayList<>();

                        for (SearchResult searchResult : results) {
                            SearchResultViewDto dto = new SearchResultViewDto();
                            dto.title = searchResult.title;
                            dto.url = searchResult.url;
                            dto.description = searchResult.description;

                            viewDto.add(dto);
                        }

                        view.setResult(viewDto);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showNotification(context.getString(R.string.server_error));
                        view.hideProgressBar();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        view.hideProgressBar();
                    }
                });
    }

    public interface IGoogleSearchView {
        void setResult(List<SearchResultViewDto> result);
        void showProgressBar();
        void hideProgressBar();
        void showNotification(String message);
    }

    public class SearchResultViewDto {
        public String title;
        public String url;
        public String description;
    }
}
