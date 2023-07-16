package com.example.workshop27prac.Repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.workshop27prac.Model.Game;

@Repository
public class GameRepo {

    @Autowired
    MongoTemplate template;

    public String getGameById(Integer gid){


        // db.game.find({gid : <gid>})
        Criteria criteria = Criteria.where("gid").is(gid);
        Query query = Query.query(criteria);
        
        Game game = template.findOne(query, Game.class, "game");
        String name = game.getName();
        System.out.println(name);
        return name;
    }

    public Boolean checkIfGameExist(Integer gid){

        Criteria criteria = Criteria.where("gid").is(gid);
        Query query = Query.query(criteria);
        
        return template.exists(query, Game.class, "game");
        


    }

    
}
