package com.google.sps.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.com.sps.data.Comment;
import java.com.sps.data.RatingHistory;

/** 
 * PlaceReviews object class
 * The PlaceReviews object, used as the master object detailing a locations reviews and ratings
 * Uses implementations of smaller Comment and RatingHistory class.
 * Does not permit multiple reviews by one author for a given location.
 * Attempts to avoid PlaceReviews objects without any ratings or reviews.
 */
public class PlaceReviews {

  private final String place_id;
  private ArrayList<Comment> reviews;
  private HashSet<String> reviewers;
  private double rating;
  private RatingHistory history;

   /**
    * Constructor
    * This is the minimal, and avoided, constructor that makes an object without reviews.
    */
  public PlaceReviews(String place_id) {
    this.place_id = place_id;
  }

  /** 
   * Constructor
   * This constructor provides an initial rating and review.
   */
  public PlaceReviews(String place_id, Comment firstReview, double initialRating) {
    this.place_id = place_id;
    this.reviews.append(firstReview);
    this.rating = initialRating;
    this.reviewers.add(firstReview.getAuthor());
  }

  /** 
   * Updater method
   * Adds a review to the existing PlaceReviews list of reviews
   */
  public void addReview(Comment review) {
    if (!reviewers.contains(review.getAuthor())) {
      this.reviews.append(review);
    }
  }

  /** 
   * Updater method
   * This adds a rating to a given PlaceReviews aggregate rating
   * This does not permit a rating outside of [0, 10]
   */
  public void addRating(double rating) {
    if (rating > 10) {
      addRating(10);
    } else if (rating < 0) {
      addRating(0);
    } else {
      this.rating += (rating / entries);
    }
  }

  /**
   * Checks if author has posted at this location before
   * @return if the user has posted a review before for this location
   */
  public bool hasReviewed(String person) {
    return this.reviewers.contains(person);
  }
}