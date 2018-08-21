package ru.mitin.vladislav.litebox.core;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    private final static String APP_PREFERENCES = "APP_PREFERENCES";

    private final static String LAST_SEARCH_TEXT = "LAST_SEARCH_TEXT";

    private SharedPreferences sharedPreferences;

    public SharedPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void putSearchText(String value) {
        sharedPreferences.edit()
                .putString(LAST_SEARCH_TEXT, value)
                .apply();
    }

    public String getSearchText() {
        return sharedPreferences.getString(LAST_SEARCH_TEXT, null);
    }
}
