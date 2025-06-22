package ie.elaine.tmdb.service;

import ie.elaine.tmdb.dto.TmdbGenre;
import ie.elaine.tmdb.dto.TmdbMovieResponse;
import ie.elaine.tmdb.entity.Genre;
import ie.elaine.tmdb.entity.Movie;
import ie.elaine.tmdb.repository.GenreRepository;
import ie.elaine.tmdb.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private TmdbService tmdbService;

    public Movie getOrCreateMovie(Integer tmdbId) {
        Optional<Movie> existingMovie = movieRepository.findById(tmdbId);

        if (existingMovie.isPresent()) {
            return existingMovie.get();
        }

        // Fetch from TMDB and create new movie
        TmdbMovieResponse tmdbMovie = tmdbService.getMovieDetails(tmdbId);
        return createMovieFromTmdb(tmdbMovie);
    }

    private Movie createMovieFromTmdb(TmdbMovieResponse tmdbMovie) {
        Movie movie = new Movie();
        movie.setId(tmdbMovie.getId());
        movie.setTitle(tmdbMovie.getTitle());
        movie.setOriginalTitle(tmdbMovie.getOriginalTitle());
        movie.setOverview(tmdbMovie.getOverview());
        movie.setPosterPath(tmdbMovie.getPosterPath());
        movie.setBackdropPath(tmdbMovie.getBackdropPath());
        movie.setReleaseDate(tmdbMovie.getReleaseDate());
        movie.setVoteAverage(tmdbMovie.getVoteAverage());
        movie.setVoteCount(tmdbMovie.getVoteCount());
        movie.setRuntime(tmdbMovie.getRuntime());
        movie.setImdbId(tmdbMovie.getImdbId());
        movie.setStatus(tmdbMovie.getStatus());
        movie.setTagline(tmdbMovie.getTagline());
        movie.setBudget(tmdbMovie.getBudget());
        movie.setRevenue(tmdbMovie.getRevenue());

        // Handle genres
        if (tmdbMovie.getGenres() != null) {
            Set<Genre> genres = tmdbMovie.getGenres().stream()
                    .map(this::getOrCreateGenre)
                    .collect(Collectors.toSet());
            movie.setGenres(genres);
        }

        return movieRepository.save(movie);
    }

    private Genre getOrCreateGenre(TmdbGenre tmdbGenre) {
        return genreRepository.findById(tmdbGenre.getId())
                .orElseGet(() -> {
                    Genre genre = new Genre();
                    genre.setId(tmdbGenre.getId());
                    genre.setName(tmdbGenre.getName());
                    return genreRepository.save(genre);
                });
    }

    public Page<Movie> searchMovies(String title, Pageable pageable) {
        return movieRepository.findByTitleContainingIgnoreCase(title, pageable);
    }

    public Page<Movie> getMoviesByGenre(Integer genreId, Pageable pageable) {
        return movieRepository.findByGenreId(genreId, pageable);
    }

    public Page<Movie> getTopRatedMovies(Pageable pageable) {
        return movieRepository.findTopRated(pageable);
    }

    public Optional<Movie> findById(Integer id) {
        return movieRepository.findById(id);
    }
}
