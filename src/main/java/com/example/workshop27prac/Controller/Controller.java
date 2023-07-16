package com.example.workshop27prac.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<String> insertReview(Review review){  

        if(review.getName() == ""){

            return ResponseEntity.status(406).body("No name entered!");
        }
        if(review.getRating() < 0 || review.getRating() > 10){

            return ResponseEntity.status(406).body("Rating is invalid!");

        }
        if(!gameService.checkIfGameExist(review.getGid())){

            return ResponseEntity.status(406).body("Game ID is invalid!");
        } else{
            
            reviewService.insertReview(review);
            return ResponseEntity.ok("Insert Successful!");
        }



    }





 
}
