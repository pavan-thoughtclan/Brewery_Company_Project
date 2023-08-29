package com.tc.brewery.controller;

import com.tc.brewery.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/ratings/{beerId}/{userId}")
    public ResponseEntity<Void> addRating(
            @PathVariable int beerId,
            @PathVariable int userId,
            @RequestBody Map<String, Object> ratingData) {


        String ratingValueStr = ratingData.get("ratingValue").toString(); // Get rating as String
        BigDecimal ratingValue = new BigDecimal(ratingValueStr); // Convert to BigDecimal


//        BigDecimal ratingValue = (BigDecimal) ratingData.get("ratingValue");
        String review = (String) ratingData.get("review");

        return ratingService.addRating(beerId, userId, ratingValue, review);
    }
}
