package com.stiho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stiho.dao.ReviewDAO;
import com.stiho.model.Review;

/**
 *
 * @author IS204_1
 */
@Service
@Transactional
public class ReviewService {

    @Autowired
    private ReviewDAO reviewDAO;

    /**
     *
     *
     * @param getters and setters to update review field and to store the
     * reviews.
     */

    public void addReview(Review review) {
        reviewDAO.addReview(review);
    }

    public void updateReview(Review review) {
        reviewDAO.updateReview(review);
    }

    public Review getReview(int id) {
        return reviewDAO.getReview(id);
    }

    public void deleteReview(int id) {
        reviewDAO.deleteReview(id);
    }

    public List<Review> getReviews() {
        return reviewDAO.getReviews();
    }

    public void storeAllReviews(List<Review> reviews) {

        reviewDAO.storeAllReviews(reviews);

    }
}
