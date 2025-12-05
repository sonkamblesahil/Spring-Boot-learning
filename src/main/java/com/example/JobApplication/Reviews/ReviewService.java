package com.example.JobApplication.Reviews;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    boolean addReview(Long companyId,Review review);
    Review getReview(Long companyId, Long ReviewId);
    boolean updateReview(Long companyId, Long ReviewId, Review review);
    boolean deleteReview(Long companyId,Long ReviewId);
}
