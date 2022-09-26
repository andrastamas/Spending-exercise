package com.example.spending.exceptions;

public class InvalidDescriptionAndCurrencyAndAmountException extends ApplicationException {

  public InvalidDescriptionAndCurrencyAndAmountException(String message) {
    super(message);
  }
}
