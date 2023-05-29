package com.webservice.demo.service.impl;

import com.webservice.demo.model.Review;
import com.webservice.demo.repository.ReviewRepository;
import com.webservice.demo.service.ReviewService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    ReviewRepository reviewRepository;
    private Logger logger = Logger.getLogger(HashCodeServiceImpl.class);

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public String save(Review review) {
        try {
            if (review.isBoIdRequired(review.getBoId()))
                review.setBoId("REV" + reviewRepository.count());
            reviewRepository.save(review);
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
            return "FAIL";
        }
        return "SUCCESS";
    }
}
