package js.com.testmerqueo.consuming;

import js.com.testmerqueo.models.MovieOb;

import java.util.List;

public interface OnGetMoviesCallback {

    void onSuccess(List<MovieOb> movies);

    void onError();
}