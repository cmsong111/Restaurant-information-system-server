package com.galaxy.Restaurantinformationsystem.Controller;

import com.galaxy.Restaurantinformationsystem.DTO.ReviewDTO;
import com.galaxy.Restaurantinformationsystem.DTO.StoreDTO;
import com.galaxy.Restaurantinformationsystem.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;

    }

    @PostMapping("/create")
    public ReviewDTO createReview(@RequestBody ReviewDTO reviewDTO) {
        return reviewService.createReview(reviewDTO);
    }

    @PostMapping("/update")
    public ReviewDTO updateReview(@RequestBody ReviewDTO reviewDTO) {
        if (reviewDTO.getRPK() == null) {
            return null;
        } else {
            return reviewService.createReview(reviewDTO);
        }
    }

    @PostMapping("/delete")
    @ResponseBody
    public String deleteReview(@RequestBody ReviewDTO reviewDTO) {
        if (reviewDTO.getRPK() == null) {
            return "delete falied";
        } else {
            reviewService.deleteReview(reviewDTO);
            return "requeted";
        }
    }

    @PostMapping("/read")
    public ReviewDTO readReview(@RequestBody ReviewDTO reviewDTO) {
        if (reviewDTO.getRPK() == null) {
            return null;
        } else {
            return reviewService.readReview(reviewDTO);
        }
    }

    @PostMapping("/readStore")
    public ArrayList<ReviewDTO> updateReview(@RequestBody StoreDTO storeDTO) {
        return reviewService.findByStore(storeDTO);
    }

}
