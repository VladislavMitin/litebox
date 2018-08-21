package ru.mitin.vladislav.litebox.views;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import ru.mitin.vladislav.litebox.R;
import ru.mitin.vladislav.litebox.core.Database;
import ru.mitin.vladislav.litebox.core.RetrofitWrapper;
import ru.mitin.vladislav.litebox.core.SharedPreferencesManager;
import ru.mitin.vladislav.litebox.interfaces.IGoogleSearchService;
import ru.mitin.vladislav.litebox.presenters.GoogleSearchPresenter;
import ru.mitin.vladislav.litebox.repositories.ResultRepository;

public class GoogleSearchActivity extends AppCompatActivity implements GoogleSearchPresenter.IGoogleSearchView {
    private EditText searchText;
    private RecyclerView searchResult;
    private ProgressBar progressBar;
    private View background;

    private SharedPreferencesManager sharedPreferencesManager;

    private GoogleSearchPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_google_search);

        background = this.findViewById(R.id.notification_background);
        searchText = this.findViewById(R.id.search_text);
        progressBar = this.findViewById(R.id.progressBar);
        searchResult = this.findViewById(R.id.search_result);
        searchResult.setLayoutManager(new LinearLayoutManager(this));
        searchResult.setAdapter(new ResultRecyclerViewAdapter(this, new ArrayList<GoogleSearchPresenter.SearchResultViewDto>()));

        this.findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchText.getText().toString().trim().equals("")) {
                    showNotification(getString(R.string.no_input_text));
                    return;
                }

                if(!isNetworkAvailable()) {
                    showNotification(getString(R.string.network_unavailable));
                    return;
                }

                sharedPreferencesManager.putSearchText(searchText.getText().toString().trim());
                presenter.search(searchText.getText().toString(), true);
            }
        });

        presenter = new GoogleSearchPresenter(this,this, new ResultRepository(RetrofitWrapper.getService(IGoogleSearchService.class),
                Room.databaseBuilder(this.getApplicationContext(), Database.class, "google-search-database").build().getSearchResultDao()));

        sharedPreferencesManager = new SharedPreferencesManager(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        String lastSearchText = sharedPreferencesManager.getSearchText();

        if(lastSearchText != null) {
            searchText.setText(lastSearchText);
            presenter.search(lastSearchText, false);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setResult(List<GoogleSearchPresenter.SearchResultViewDto> result) {
        this.searchResult.setAdapter(new ResultRecyclerViewAdapter(this, result));
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showNotification(String message) {
        Snackbar.make(background, message, Snackbar.LENGTH_SHORT).show();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
