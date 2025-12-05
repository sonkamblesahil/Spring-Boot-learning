package com.example.JobApplication.Reviews;


import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
       return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addCompanyReview(@PathVariable Long companyId,@RequestBody Review review){
       boolean issaved =  reviewService.addReview(companyId,review);
       if(issaved){
        return new ResponseEntity<>("review added successfully", HttpStatus.OK);

       }else{
           return new ResponseEntity<>("The company does not exist",HttpStatus.NOT_FOUND);

       }
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable  Long reviewId){
        return new ResponseEntity<>(reviewService.getReview(companyId,reviewId),HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,@PathVariable Long reviewId, @RequestBody Review review){
        boolean updated = reviewService.updateReview(companyId,reviewId,review);
        if(updated)
        return new ResponseEntity<>("review updated successfully",HttpStatus.OK);
        else{
            return new ResponseEntity<>("not updated",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,@PathVariable Long reviewId){
        boolean deleted = reviewService.deleteReview(companyId,reviewId);
        if(deleted) return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
        return new ResponseEntity<>("Cannot delete the review",HttpStatus.NOT_FOUND);
    }
}
