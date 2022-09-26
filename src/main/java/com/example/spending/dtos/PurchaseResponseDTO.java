package com.example.spending.dtos;

import java.time.LocalDateTime;

public class PurchaseResponseDTO {

  private String description;
  private Double cost;
  private String currency;
  private LocalDateTime issueDate;

  public PurchaseResponseDTO() {
  }

  public PurchaseResponseDTO(String description, Double cost, String currency, LocalDateTime issueDate) {
    this.description = description;
    this.cost = cost;
    this.currency = currency;
    this.issueDate = issueDate;
  }

  //region Getter Setter
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
  //endregion
}