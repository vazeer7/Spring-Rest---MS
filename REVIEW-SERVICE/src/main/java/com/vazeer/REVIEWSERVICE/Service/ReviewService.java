package com.vazeer.REVIEWSERVICE.Service;

import com.vazeer.REVIEWSERVICE.Model.Review;
import com.vazeer.REVIEWSERVICE.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Review> getAllReviews(Integer reviewId) {
        return reviewRepository.findByCompanyId(reviewId);
    }

    public List<Review> getReviewByCompanyId(Integer companyId) {
        List<Review> reviewList = reviewRepository.findByCompanyId(companyId);

        return reviewList;
    }

    public boolean addReview(Integer companyId, Review review) {
        if (companyId != null && review != null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean updateReview(Integer reviewId, Review updatedReview) {

        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if (reviewOptional.isPresent()){
            Review review = reviewOptional.get();
            review.setReviewDescription(updatedReview.getReviewDescription());
            review.setReviewRating(updatedReview.getReviewRating());
            review.setReviewTitle(updatedReview.getReviewTitle());
            review.setCompanyId(updatedReview.getCompanyId());

            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    public boolean deleteReview(Integer reviewId) {

        if (reviewRepository.existsById(reviewId)){
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
