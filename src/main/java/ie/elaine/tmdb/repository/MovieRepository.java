package ie.elaine.tmdb.repository;

import ie.elaine.tmdb.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// MovieRepository
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query("SELECT m FROM Movie m WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    Page<Movie> findByTitleContainingIgnoreCase(@Param("title") String title, Pageable pageable);

    @Query("SELECT m FROM Movie m JOIN m.genres g WHERE g.id = :genreId")
    Page<Movie> findByGenreId(@Param("genreId") Integer genreId, Pageable pageable);

    @Query("SELECT m FROM Movie m ORDER BY m.voteAverage DESC")
    Page<Movie> findTopRated(Pageable pageable);

    @Query("SELECT m FROM Movie m WHERE m.releaseDate LIKE :year%")
    Page<Movie> findByReleaseYear(@Param("year") String year, Pageable pageable);
}
