package ie.elaine.tmdb.repository;

import ie.elaine.tmdb.entity.User;
import ie.elaine.tmdb.entity.Watchlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {
    Page<Watchlist> findByUserOrderByAddedAtDesc(User user, Pageable pageable);
    boolean existsByUserAndMovieId(User user, Integer movieId);
    void deleteByUserAndMovieId(User user, Integer movieId);

    @Query("SELECT COUNT(w) FROM Watchlist w WHERE w.user = :user")
    Long getWatchlistCountForUser(@Param("user") User user);
}
