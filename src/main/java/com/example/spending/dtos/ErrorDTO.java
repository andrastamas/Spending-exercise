package com.example.spending.dtos;

public class ErrorDTO {

  private String error;

  public ErrorDTO(String error) {
    this.error = error;
  }

  // region Getter
  public String getError() {
    return error;
  }
  //endregion
}
