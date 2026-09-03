package com.aaronlifeapp.schedule;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String date;

    //Setter method
    public void setId(Long id){
        this.id = id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setDate(String date){
        this.date = date;
    }

    //Getter method
    public Long getId(){
        return this.id;
    }
    public String getTitle(){
        return this.title;
    }
    public String getDate(){
        return this.date;
    }
}
