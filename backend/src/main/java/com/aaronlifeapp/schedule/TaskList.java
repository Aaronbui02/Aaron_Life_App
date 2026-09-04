package com.aaronlifeapp.schedule;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TaskList {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;

    //Setter method
    public void setId(String id){
        this.id = id;
    }
    public void setTitle(String title){
        this.title = title;
    }

    //Getter method
    public String getId(){
        return this.id;
    }
    public String getTitle(){
        return this.title;
    }

}
