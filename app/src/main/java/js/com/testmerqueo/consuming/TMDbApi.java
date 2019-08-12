package js.com.testmerqueo.consuming;

import js.com.testmerqueo.models.MoviesRespons;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDbApi {

    @GET("movie/popular")
    Call<MoviesRespons> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );
}