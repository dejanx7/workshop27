package com.example.workshop27prac.Repository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.example.workshop27prac.Model.Review;

@Repository
public class ReviewRepo {

    @Autowired
    MongoTemplate template;
    

    public void insertNewReview(Document document){

        // template.insert(review, "reviews");
        template.save(document, "reviews");



    }
}
