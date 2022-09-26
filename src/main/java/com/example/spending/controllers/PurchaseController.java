package com.example.spending.controllers;

import com.example.spending.dtos.ErrorDTO;
import com.example.spending.dtos.PurchaseRequestDTO;
import com.example.spending.dtos.PurchaseResponseDTO;
import com.example.spending.exceptions.InvalidCostException;
import com.example.spending.exceptions.InvalidCurrencyException;
import com.example.spending.exceptions.InvalidDescriptionAndCurrencyAndAmountException;
import com.example.spending.exceptions.InvalidDescriptionException;
import com.example.spending.services.PurchaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController {

  private final PurchaseService purchaseService;

  @Autowired
  public PurchaseController(PurchaseService purchaseService) {
    this.purchaseService = purchaseService;
  }

  @PostMapping("/user/purchase")
  public ResponseEntity<?> createPurchase(@RequestBody PurchaseRequestDTO purchaseRequestDTO) {

    try {
      purchaseService.handlePurchaseRequestDTO(purchaseRequestDTO);
    } catch (InvalidCostException | InvalidCurrencyException | InvalidDescriptionException |
             InvalidDescriptionAndCurrencyAndAmountException exception) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(exception.getMessage()));
    }
    return ResponseEntity.ok().body(HttpStatus.CREATED);
  }

  @GetMapping("user/purchase")
  public ResponseEntity<List<PurchaseResponseDTO>> createPurchaseList(
      @RequestParam(required = true, name = "currency") String currency,
      @RequestParam(required = false, name = "sortMode") String sortMode,
      @RequestParam(required = false, name = "sortOrder") String sortOrder) {
    return ResponseEntity.ok().body(purchaseService.collectPurchaseResponses(currency, sortMode, sortOrder));
  }
}