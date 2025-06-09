package ie.elaine.tmdb.service;



import ie.elaine.tmdb.config.TmdbConfig;
import ie.elaine.tmdb.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.List;

@Service
public class TmdbService {

    private final WebClient webClient;
    private final TmdbConfig tmdbConfig;

    @Autowired
    public TmdbService(TmdbConfig tmdbConfig) {
        this.tmdbConfig = tmdbConfig;
        this.webClient = WebClient.builder()
                .baseUrl(tmdbConfig.getBaseUrl())
                .defaultHeader("Authorization", "Bearer " + tmdbConfig.getKey())
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    @Cacheable("movies")
    public TmdbMovieResponse getMovieDetails(int movieId) {
        return webClient.get()
                .uri("/movie/{id}", movieId)
                .retrieve()
                .bodyToMono(TmdbMovieResponse.class)
                .timeout(Duration.ofMillis(tmdbConfig.getTimeout()))
                .block();
    }

    @Cacheable("movieSearch")
    public TmdbSearchResponse<TmdbMovieResponse> searchMovies(String query, int page) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/movie")
                        .queryParam("query", query)
                        .queryParam("page", page)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<TmdbSearchResponse<TmdbMovieResponse>>() {})
                .timeout(Duration.ofMillis(tmdbConfig.getTimeout()))
                .block();
    }

    @Cacheable("popularMovies")
    public TmdbSearchResponse<TmdbMovieResponse> getPopularMovies(int page) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/popular")
                        .queryParam("page", page)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<TmdbSearchResponse<TmdbMovieResponse>>() {})
                .timeout(Duration.ofMillis(tmdbConfig.getTimeout()))
                .block();
    }

    @Cacheable("topRatedMovies")
    public TmdbSearchResponse<TmdbMovieResponse> getTopRatedMovies(int page) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/top_rated")
                        .queryParam("page", page)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<TmdbSearchResponse<TmdbMovieResponse>>() {})
                .timeout(Duration.ofMillis(tmdbConfig.getTimeout()))
                .block();
    }

    @Cacheable("nowPlayingMovies")
    public TmdbSearchResponse<TmdbMovieResponse> getNowPlayingMovies(int page) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/now_playing")
                        .queryParam("page", page)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<TmdbSearchResponse<TmdbMovieResponse>>() {})
                .timeout(Duration.ofMillis(tmdbConfig.getTimeout()))
                .block();
    }

    @Cacheable("movieCredits")
    public TmdbCreditsResponse getMovieCredits(int movieId) {
        return webClient.get()
                .uri("/movie/{id}/credits", movieId)
                .retrieve()
                .bodyToMono(TmdbCreditsResponse.class)
                .timeout(Duration.ofMillis(tmdbConfig.getTimeout()))
                .block();
    }

    @Cacheable("personDetails")
    public TmdbPersonResponse getPersonDetails(int personId) {
        return webClient.get()
                .uri("/person/{id}", personId)
                .retrieve()
                .bodyToMono(TmdbPersonResponse.class)
                .timeout(Duration.ofMillis(tmdbConfig.getTimeout()))
                .block();
    }

    @Cacheable("genres")
    public List<TmdbGenre> getGenres() {
        var response = webClient.get()
                .uri("/genre/movie/list")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<TmdbGenreListResponse>() {})
                .timeout(Duration.ofMillis(tmdbConfig.getTimeout()))
                .block();

        return response != null ? response.getGenres() : List.of();
    }

    public String getImageUrl(String imagePath, String size) {
        if (imagePath == null || imagePath.isEmpty()) {
            return null;
        }
        return tmdbConfig.getImageBaseUrl() + "/" + size + imagePath;
    }

    public String getPosterUrl(String posterPath) {
        return getImageUrl(posterPath, "w500");
    }

    public String getBackdropUrl(String backdropPath) {
        return getImageUrl(backdropPath, "w1280");
    }

    public String getProfileUrl(String profilePath) {
        return getImageUrl(profilePath, "w185");
    }
}