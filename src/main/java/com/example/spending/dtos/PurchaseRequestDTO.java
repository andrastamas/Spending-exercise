package com.example.spending.dtos;

public class PurchaseRequestDTO {

  private String description;
  private Double cost;
  private String currency;

  public PurchaseRequestDTO(String description, Double cost, String currency) {
    this.description = description;
    this.cost = cost;
    this.currency = currency;
  }

  // region Getter Setter
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
  //endregion
}