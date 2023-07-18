package com.example.workshop27prac.Controller;

import java.time.LocalDateTime;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.workshop27prac.Model.Review;
import com.example.workshop27prac.Repository.ReviewRepo;
import com.example.workshop27prac.Service.GameService;
import com.example.workshop27prac.Service.ReviewService;

@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    ReviewRepo reviewRepo;

    @Autowired
    ReviewService reviewService;

    @Autowired
    GameService gameService;

    @PostMapping(path = "/review", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> insertReview(Review review) {

        if (review.getName() == "") {

            return ResponseEntity.status(406).body("No name entered!");
        }
        if (review.getRating() < 0 || review.getRating() > 10) {

            return ResponseEntity.status(406).body("Rating is invalid!");

        }
        if (!gameService.checkIfGameExist(review.getGid())) {

            return ResponseEntity.status(406).body("Game ID is invalid!");
        } else {

            reviewService.insertReview(review);
            return ResponseEntity.ok("Insert Successful!");
        }

    }

    @PutMapping(path = "/review/{review_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateReview(@PathVariable String review_id, @RequestBody Document newReview) {

        System.out.println(newReview + review_id);

        Boolean isUpdated = reviewService.updateReview(newReview, review_id);

        if (isUpdated == false) {

            return ResponseEntity.status(406).body("Update Unsuccessful");
        } else {

            return ResponseEntity.ok("Update Successful");
        }

    }

    @GetMapping(path = "/review/{review_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Document> getReview(@PathVariable String review_id) {

        Document review = new Document();
        Document fullReview = reviewService.getReviewById(review_id);
        Document latestEdit = reviewService.getLatestReviewEdit(review_id);

        review.append("user", fullReview.getString("user"));
        review.append("rating", latestEdit.getInteger("rating"));
        review.append("comment", latestEdit.getString("comment"));
        review.append("ID", fullReview.getInteger("id"));
        review.append("posted", latestEdit.getDate("posted"));
        review.append("name", fullReview.getString("name"));
        review.append("edited", true);
        review.append("timestamp", LocalDateTime.now());

        return ResponseEntity.ok(review);

    }


    @GetMapping(path = "/review/{review_id}/history", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Document> getReview2(@PathVariable String review_id){

        return ResponseEntity.ok(reviewService.getReviewById(review_id));
    }

}
