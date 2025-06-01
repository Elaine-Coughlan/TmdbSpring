package ie.elaine.tmdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TmdbMovieResponse {

    private int id;
    private String title;

    @JsonProperty("original_title")
    private String originalTitle;

    private String overview;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("backdrop_path")
    private String backdropPath;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("vote_average")
    private double voteAverage;

    @JsonProperty("vote_count")
    private int voteCount;

    @JsonProperty("genre_ids")
    private List<Integer> genreIds;

    private List<TmdbGenre> genres;

    private int runtime;

    @JsonProperty("imdb_id")
    private String imdbId;

    private String status;
    private String tagline;
    private long budget;
    private long revenue;

    @JsonProperty("production_companies")
    private List<TmdbProductionCompany> productionCompanies;

    @JsonProperty("production_countries")
    private List<TmdbProductionCountry> productionCountries;

    @JsonProperty("spoken_languages")
    private List<TmdbLanguage> spokenLanguages;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<TmdbLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<TmdbLanguage> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public List<TmdbProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<TmdbProductionCountry> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public List<TmdbProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<TmdbProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public List<TmdbGenre> getGenres() {
        return genres;
    }

    public void setGenres(List<TmdbGenre> genres) {
        this.genres = genres;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
