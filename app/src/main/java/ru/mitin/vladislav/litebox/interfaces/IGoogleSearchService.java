package ru.mitin.vladislav.litebox.interfaces;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.mitin.vladislav.litebox.dto.ResponseDto;

public interface IGoogleSearchService {
    @GET("customsearch/v1")
    Observable<ResponseDto> search(@Query("key") String key, @Query("cx") String cx, @Query("q") String q);
}
