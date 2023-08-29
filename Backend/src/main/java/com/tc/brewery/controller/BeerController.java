package com.tc.brewery.controller;
import com.tc.brewery.entity.Beer;
import com.tc.brewery.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class BeerController {
    @Autowired
    private BeerService beerService;

    @GetMapping("/beers/categories")
    public Set<String> getAllCategories() {
        return beerService.getAllCategories();
    }

    @GetMapping("/beers")
    public List<Beer> getAllBeers() {
        return beerService.getAllBeers();
    }

    @GetMapping("/beers/categories/{category}")
    public List<Beer> getBeersByCategory(@PathVariable String category) {
        category=category.replace("_"," ");
        return beerService.getBeersByCategory(category);
    }

    @GetMapping("/beers/categories/{category}/{beerId}")
    public ResponseEntity<?> getBeerByCategoryAndId(@PathVariable String category, @PathVariable int beerId) {
        category=category.replace("_"," ");
        Beer beer = beerService.getBeerByCategoryAndId(category, beerId);

        if (beer == null) {
            return ResponseEntity.notFound().build(); // Return 404 Not Found
        } else {
            return ResponseEntity.ok(beer); // Return the beer if found
        }
    }

    @GetMapping("/beers/{beerId}")
    public ResponseEntity<Beer> getBeerById(@PathVariable int beerId) {
        Beer beer = beerService.getBeerById(beerId);

        if (beer == null) {
            return ResponseEntity.notFound().build(); // Return 404 status code
        }

        return ResponseEntity.ok(beer);
    }

    @GetMapping("/beers/Highrated")
    public List<Beer> getHighRatedBeers() {
        return beerService.getBeersWithHighRatings();
    }

    @GetMapping("/beers/Moderaterated")
    public List<Beer> getBeersWithinManualRatingsRange() {
        return beerService.getBeersWithinManualRatingsRange();
    }
}

