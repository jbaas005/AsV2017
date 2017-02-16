/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stiho.dao;

import com.stiho.model.Review;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mustafa
 */
@Repository
public class ReviewDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * review toevoegen
     *
     * @param review
     */
    public void addReview(Review review) {
        getCurrentSession().save(review);
    }

    /**
     * review updaten
     *
     * @param review
     */
    public void updateReview(Review review) {
        Review reviewToUpdate = getReview(review.getReviewId());

        getCurrentSession().update(reviewToUpdate);
    }

    /**
     * review opvragen
     *
     * @param id
     * @return
     */
    public Review getReview(int id) {
        Review review = (Review) getCurrentSession().get(Review.class, id);
        return review;
    }

    /**
     * review verwijderen
     *
     * @param id
     */
    public void deleteReview(int id) {
        Review review = getReview(id);
        if (review != null) {
            getCurrentSession().delete(review);
        }
    }

    public Number amountOfReviews() {
        return (Number) getCurrentSession().createCriteria("Review").setProjection(Projections.rowCount()).uniqueResult();
    }

    public Number avarageReviewOutcome() {

        return (Number) getCurrentSession().createCriteria("Review").setProjection(Projections.avg("Review"));

    }

    /**
     * alle reviews opvragen
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Review> getReviews() {
        return getCurrentSession().createQuery("from Review").list();
    }

    /**
     * alle reviews opslaan
     *
     * @param reviews
     */
    public void storeAllReviews(List<Review> reviews) {
        for (Review review : reviews) {
            getCurrentSession().save(review);
        }
    }
}
