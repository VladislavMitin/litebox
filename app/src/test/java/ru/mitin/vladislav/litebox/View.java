package ru.mitin.vladislav.litebox;

import java.util.List;

import ru.mitin.vladislav.litebox.presenters.GoogleSearchPresenter;

public class View implements GoogleSearchPresenter.IGoogleSearchView {
    private List<GoogleSearchPresenter.SearchResultViewDto> results;
    private boolean showProgressBar;
    private boolean hideProgressBar;
    private String message;

    @Override
    public void setResult(List<GoogleSearchPresenter.SearchResultViewDto> result) {
        this.results = result;
    }

    @Override
    public void showProgressBar() {
        this.showProgressBar = true;
    }

    @Override
    public void hideProgressBar() {
        this.hideProgressBar = true;
    }

    @Override
    public void showNotification(String message) {
        this.message = message;
    }

    public List<GoogleSearchPresenter.SearchResultViewDto> getResults() {
        return results;
    }

    public void setResults(List<GoogleSearchPresenter.SearchResultViewDto> results) {
        this.results = results;
    }

    public boolean isShowProgressBar() {
        return showProgressBar;
    }

    public void setShowProgressBar(boolean showProgressBar) {
        this.showProgressBar = showProgressBar;
    }

    public boolean isHideProgressBar() {
        return hideProgressBar;
    }

    public void setHideProgressBar(boolean hideProgressBar) {
        this.hideProgressBar = hideProgressBar;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
