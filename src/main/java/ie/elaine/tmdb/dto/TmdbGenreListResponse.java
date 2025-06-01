package ie.elaine.tmdb.dto;

import java.util.List;

public class TmdbGenreListResponse {

    private List<TmdbGenre> genres;

    public List<TmdbGenre> getGenres() {
        return genres;
    }

    public void setGenres(List<TmdbGenre> genres) {
        this.genres = genres;
    }
}
