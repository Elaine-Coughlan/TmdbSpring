package ie.elaine.tmdb.service;

import ie.elaine.tmdb.entity.Movie;
import ie.elaine.tmdb.entity.Rating;
import ie.elaine.tmdb.entity.User;
import ie.elaine.tmdb.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private MovieService movieService;

    public Rating rateMovie(User user, Integer movieId, Integer ratingValue) {
        Movie movie = movieService.getOrCreateMovie(movieId);

        Optional<Rating> existingRating = ratingRepository.findByUserAndMovie(user, movie);

        Rating rating;
        if (existingRating.isPresent()) {
            rating = existingRating.get();
            rating.setRating(ratingValue);
        } else {
            rating = new Rating();
            rating.setUser(user);
            rating.setMovie(movie);
            rating.setRating(ratingValue);
        }

        return ratingRepository.save(rating);
    }

    public Optional<Rating> getUserRatingForMovie(User user, Integer movieId) {
        return movieService.findById(movieId)
                .flatMap(movie -> ratingRepository.findByUserAndMovie(user, movie));
    }

    public Double getAverageRatingForMovie(Integer movieId) {
        return ratingRepository.getAverageRatingForMovie(movieId);
    }

    public Long getRatingCountForMovie(Integer movieId) {
        return ratingRepository.getRatingCountForMovie(movieId);
    }

    public Page<Rating> getUserRatings(User user, Pageable pageable) {
        return ratingRepository.findByUserOrderByCreatedAtDesc(user, pageable);
    }
}
