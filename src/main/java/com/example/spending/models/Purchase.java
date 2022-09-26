package com.example.spending.models;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Purchase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String description;
  private Double cost;
  private String currency;
  private LocalDateTime issueDate;

  public Purchase() {
  }

  public Purchase(String description, Double cost, String currency, LocalDateTime issueDate) {
    this.description = description;
    this.cost = cost;
    this.currency = currency;
    this.issueDate = LocalDateTime.now();
  }

  // region Getter Setter

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getCost() {
    return cost;
  }

  public void setCost(Double cost) {
    this.cost = cost;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public LocalDateTime getIssueDate() {
    return issueDate;
  }

  public void setIssueDate(LocalDateTime issueDate) {
    this.issueDate = issueDate;
  }

  // endregion

}