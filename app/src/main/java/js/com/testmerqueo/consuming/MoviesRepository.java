package js.com.testmerqueo.consuming;

import androidx.annotation.NonNull;
import js.com.testmerqueo.models.MoviesRespons;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesRepository {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String LANGUAGE = "en-US";

    private static MoviesRepository repository;

    private TMDbApi api;

    private MoviesRepository(TMDbApi api) {
        this.api = api;
    }

    public static MoviesRepository getInstance() {
        if (repository == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            repository = new MoviesRepository(retrofit.create(TMDbApi.class));
        }

        return repository;
    }

    public void getMovies(final OnGetMoviesCallback callback) {
        api.getPopularMovies("260139a808f50e9c66c712c726bfc174", LANGUAGE, 1)
                .enqueue(new Callback<MoviesRespons>() {
                    @Override
                    public void onResponse(@NonNull Call<MoviesRespons> call, @NonNull Response<MoviesRespons> response) {
                        if (response.isSuccessful()) {
                            MoviesRespons moviesResponse = response.body();
                            if (moviesResponse != null && moviesResponse.getMovies() != null) {
                                callback.onSuccess(moviesResponse.getMovies());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }


                    @Override
                    public void onFailure(Call<MoviesRespons> call, Throwable t) {
                        callback.onError();
                    }
                });
    }
}