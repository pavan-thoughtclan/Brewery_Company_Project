package com.tc.brewery.service;

import com.tc.brewery.entity.Beer;
import com.tc.brewery.entity.Rating;
import com.tc.brewery.entity.User;
import com.tc.brewery.repository.BeerRepository;
import com.tc.brewery.repository.RatingRepository;
import com.tc.brewery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private UserRepository userRepository;

//    public ResponseEntity<Void> addRating(int beerId, long userId, BigDecimal ratingValue, String review) {
//        Beer beer = beerRepository.findById(beerId).orElse(null);
//        User user = userRepository.findById(userId).orElse(null);
//
//        if (beer == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//
//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//
//        Rating rating = new Rating();
//        rating.setBeer(beer);
//        rating.setUser(user);
//        rating.setRating(ratingValue);
//        rating.setReview(review);
//
//        ratingRepository.save(rating);
//
//        return ResponseEntity.ok().build();
//    }


    public ResponseEntity<Void> addRating(int beerId, long userId, BigDecimal ratingValue, String review) {
        Beer beer = beerRepository.findById(beerId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (beer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Rating rating = new Rating();
        rating.setBeer(beer);
        rating.setUser(user);
        rating.setRating(ratingValue);
        rating.setReview(review);

        ratingRepository.save(rating);

        // Recalculate and update averageRating for all beers
        updateAverageRatingsForAllBeers();

        return ResponseEntity.ok().build();
    }

    private void updateAverageRatingsForAllBeers() {
        List<Beer> allBeers = beerRepository.findAll();
        for (Beer beer : allBeers) {
            calculateAverageRating(beer);
            beerRepository.save(beer);
        }
    }

    private void calculateAverageRating(Beer beer) {
        if (beer.getRatings() != null && !beer.getRatings().isEmpty()) {
            BigDecimal totalRating = BigDecimal.ZERO;
            for (Rating rating : beer.getRatings()) {
                totalRating = totalRating.add(rating.getRating());
            }
            beer.setAverageRating(totalRating.divide(BigDecimal.valueOf(beer.getRatings().size()), 2, RoundingMode.HALF_UP));
        } else {
            beer.setAverageRating(null);
        }
    }

}

