package com.example.workshop27prac.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workshop27prac.Repository.GameRepo;

@Service
public class GameService {

    @Autowired
    GameRepo gameRepo;

    public String getGameById(Integer gid){

        return gameRepo.getGameById(gid);


    }

    public Boolean checkIfGameExist(Integer gid){

        return gameRepo.checkIfGameExist(gid);
    }
    
}
