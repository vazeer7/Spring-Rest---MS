package com.vazeer.REVIEWSERVICE.Controller;

import com.vazeer.REVIEWSERVICE.Model.Review;
import com.vazeer.REVIEWSERVICE.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{reviewId}")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable("reviewId") Integer reviewId){
        return ResponseEntity.ok(reviewService.getAllReviews(reviewId));
    }

    @GetMapping()
    public List<Review> getReviewByCompanyId(@RequestParam Integer companyId){
        return reviewService.getReviewByCompanyId(companyId);
    }

    @PostMapping()
    public ResponseEntity<String> addReview(@RequestParam Integer companyId, @RequestBody Review review){
        boolean exist = reviewService.addReview(companyId, review);
        if (exist)
            return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Company Id doesn't exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/review/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable("reviewId") Integer reviewId, @RequestBody Review updatedReview){
        boolean exist = reviewService.updateReview(reviewId, updatedReview);
        if (exist)
            return new ResponseEntity<>("Review updated successfully", HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>("Id doesn't exist", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable("reviewId") Integer reviewId){
        boolean exist = reviewService.deleteReview(reviewId);
        if (exist)
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Review doesn't exist", HttpStatus.NOT_FOUND);
    }
}
