package com.example.workshop27prac.Repository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepo {

    @Autowired
    MongoTemplate template;

    public void insertNewReview(Document document) {

        // template.insert(review, "reviews");
        template.save(document, "reviews");

    }

    public Document checkIfReviewExist(String id) {

        Document reviewDoc = template.findById(id, Document.class, "reviews");

        return reviewDoc;
        // if(reviewDoc == null){

        // return false;
        // }else{

        // return true;
        // }

    }

    public void updateReview(Document newReview, String id) {

        System.out.println("in repoo " + newReview);
        Criteria criteria = Criteria.where("_id").is(id);
        Query query = Query.query(criteria);
        // template.upsert(query, Update.fromDocument(newReview), Document.class,
        // "reviews");
        template.updateFirst(query, Update.fromDocument(newReview), Document.class, "reviews");

    }

}
