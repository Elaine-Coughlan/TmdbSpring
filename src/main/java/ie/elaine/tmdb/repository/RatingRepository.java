package ie.elaine.tmdb.repository;

import ie.elaine.tmdb.entity.Movie;
import ie.elaine.tmdb.entity.Rating;
import ie.elaine.tmdb.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByUserAndMovie(User user, Movie movie);

    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.movie.id = :movieId")
    Double getAverageRatingForMovie(@Param("movieId") Integer movieId);

    @Query("SELECT COUNT(r) FROM Rating r WHERE r.movie.id = :movieId")
    Long getRatingCountForMovie(@Param("movieId") Integer movieId);

    Page<Rating> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);
}
