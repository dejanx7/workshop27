package com.example.workshop27prac.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public void insertReview(Review review) {

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

    public Boolean updateReview(Document newReview, String id) {

        Document reviewDoc = reviewRepo.checkIfReviewExist(id);

        if (reviewDoc == null) {

            return false;
        } else {

            if (!reviewDoc.containsKey("edited")) {

                List<Document> listOfReviewEdit = new ArrayList<>();
                newReview.append("posted", LocalDateTime.now());
                listOfReviewEdit.add(newReview);

                reviewDoc.append("edited", listOfReviewEdit);
                reviewRepo.updateReview(reviewDoc, id);
            } else {

                List<Document> listOfReviewEdit = reviewDoc.getList("edited", Document.class);
                newReview.append("posted", LocalDateTime.now());
                listOfReviewEdit.add(newReview);

                reviewDoc.append("edited", listOfReviewEdit);
                reviewRepo.updateReview(reviewDoc, id);
            }

            // reviewDoc.replace("comment", newReview.getString("comment"));
            // reviewDoc.replace("rating", newReview.getInteger("rating"));
            // reviewDoc.replace("posted", LocalDateTime.now());
            // reviewRepo.updateReview(reviewDoc, id);

            return true;

        }

    }

    public Document getReviewById(String review_id){

        return reviewRepo.checkIfReviewExist(review_id);
    }

    

    public Document getLatestReviewEdit(String review_id){

        Document review = reviewRepo.checkIfReviewExist(review_id);
        
        List<Document> editedList = review.getList("edited", Document.class);
        System.out.println("in service" + editedList);
        Document latestReview = editedList.get(editedList.size() -1);
        System.out.println("one review"+latestReview);

        return latestReview;



    }

}
