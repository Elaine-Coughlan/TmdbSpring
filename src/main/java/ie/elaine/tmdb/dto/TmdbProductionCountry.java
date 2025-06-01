package ie.elaine.tmdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TmdbProductionCountry {

    @JsonProperty("iso_3166_1")
    private String iso31661;

    private String name;

    public String getIso31661() {
        return iso31661;
    }

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
