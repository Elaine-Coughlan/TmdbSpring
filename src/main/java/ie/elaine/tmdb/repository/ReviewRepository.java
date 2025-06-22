package ie.elaine.tmdb.repository;

import ie.elaine.tmdb.entity.Movie;
import ie.elaine.tmdb.entity.Review;
import ie.elaine.tmdb.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByMovieOrderByCreatedAtDesc(Movie movie, Pageable pageable);
    Page<Review> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);

    @Query("SELECT COUNT(r) FROM Review r WHERE r.movie.id = :movieId")
    Long getReviewCountForMovie(@Param("movieId") Integer movieId);
}
