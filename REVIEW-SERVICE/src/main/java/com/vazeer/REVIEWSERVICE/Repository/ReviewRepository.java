package com.vazeer.REVIEWSERVICE.Repository;

import com.vazeer.REVIEWSERVICE.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Review findByReviewId(Integer reviewId);

    List<Review> findByCompanyId(Integer companyId);
}
