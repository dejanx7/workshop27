package com.example.workshop27prac.Service;

import java.time.LocalDateTime;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workshop27prac.Model.Review;
import com.example.workshop27prac.Repository.GameRepo;
import com.example.workshop27prac.Repository.ReviewRepo;

@Service
public class ReviewService {

    @Autowired
    GameRepo gameRepo;
    @Autowired
    ReviewRepo reviewRepo;

    public void insertReview(Review review ){

        Document reviewDoc = new Document();
        reviewDoc
                .append("user", review.getName())
                .append("rating", review.getRating())
                .append("comment", review.getComment())
                .append("id", review.getGid())
                .append("posted", LocalDateTime.now())
                .append("name", gameRepo.getGameById(review.getGid()));

        reviewRepo.insertNewReview(reviewDoc);

                


    }

    
    
}
