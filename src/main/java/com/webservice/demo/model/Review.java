package com.webservice.demo.model;


import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "review")
@Getter
@Setter
public class Review extends AbstractModel {

  @Column(name = "review")
  private String review;

  @Column(name = "description")
  private String description;

  @Column(name = "rating")
  private double rating;

  @Column(name = "date")
  private String date;

  @Column(name = "userName")
  private String userName;

  public Review(){}

  public Review(String rawData){
    String[] reviews = rawData.split("\\//");
    setReview(reviews[0]);
    setRating(Double.parseDouble(reviews[1]));
    setDate(reviews[2]);
    setUserName(reviews[3].split("on")[0].replace("By"," ").trim());
    setDescription(reviews[4]);
  }
}
