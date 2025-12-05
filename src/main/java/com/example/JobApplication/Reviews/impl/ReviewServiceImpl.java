package com.example.JobApplication.Reviews.impl;

import com.example.JobApplication.Reviews.Review;
import com.example.JobApplication.Reviews.ReviewRepository;
import com.example.JobApplication.Reviews.ReviewService;
import com.example.JobApplication.company.Company;
import com.example.JobApplication.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.findCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        return reviewRepository.findByCompanyId(companyId)
                .stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long ReviewId, Review updatedReview) {
        if(companyService.findCompanyById(companyId)!=null){
            updatedReview.setCompany(companyService.findCompanyById(companyId));
            updatedReview.setId(ReviewId);
            reviewRepository.save(updatedReview);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long companyId, Long ReviewId) {
        if(companyService.findCompanyById(companyId)!=null && reviewRepository.existsById(ReviewId)){
            Review review =reviewRepository.findById(ReviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompany(company,companyId);
            reviewRepository.deleteById(ReviewId);
            return true;
        }else{
            return false;
        }
    }
}
