package ie.elaine.tmdb.dto;

import java.util.List;

public class TmdbCreditsResponse {

    private int id;
    private List<TmdbCast> cast;
    private List<TmdbCrew> crew;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<TmdbCast> getCast() {
        return cast;
    }

    public void setCast(List<TmdbCast> cast) {
        this.cast = cast;
    }

    public List<TmdbCrew> getCrew() {
        return crew;
    }

    public void setCrew(List<TmdbCrew> crew) {
        this.crew = crew;
    }
}
