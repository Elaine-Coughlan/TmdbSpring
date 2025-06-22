package ie.elaine.tmdb.service;

import ie.elaine.tmdb.entity.User;
import ie.elaine.tmdb.entity.Watchlist;
import ie.elaine.tmdb.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

    public void addToWatchlist(User user, Integer movieId) {
        if (!watchlistRepository.existsByUserAndMovieId(user, movieId)) {
            Watchlist watchlist = new Watchlist();
            watchlist.setUser(user);
            watchlist.setMovieId(movieId);
            watchlistRepository.save(watchlist);
        }
    }

    public void removeFromWatchlist(User user, Integer movieId) {
        watchlistRepository.deleteByUserAndMovieId(user, movieId);
    }

    public boolean isInWatchlist(User user, Integer movieId) {
        return watchlistRepository.existsByUserAndMovieId(user, movieId);
    }

    public Page<Watchlist> getUserWatchlist(User user, Pageable pageable) {
        return watchlistRepository.findByUserOrderByAddedAtDesc(user, pageable);
    }

    public Long getWatchlistCount(User user) {
        return watchlistRepository.getWatchlistCountForUser(user);
    }
}
