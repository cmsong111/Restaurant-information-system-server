package com.galaxy.Restaurantinformationsystem.Controller;

import com.galaxy.Restaurantinformationsystem.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ReviewController {

    private ReviewService reviewService;


    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;

    }
}
