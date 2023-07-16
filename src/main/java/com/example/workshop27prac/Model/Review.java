package com.example.workshop27prac.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    private String name;
    private Integer rating;
    private String comment;
    private Integer gid;
    
}
