package com.webservice.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.webservice.demo.entity.Views;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "review")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Review extends AbstractModel {

  @JsonView(Views.Thin.class)
  @Column(name = "review")
  private String review;

  @JsonView(Views.Thin.class)
  @Column(name = "description", columnDefinition = "longtext")
  private String description;

  @JsonView(Views.Thin.class)
  @Column(name = "rating")
  private double rating;

  @JsonView(Views.Thin.class)
  @Column(name = "date")
  private String date;

  @JsonView(Views.Thin.class)
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
