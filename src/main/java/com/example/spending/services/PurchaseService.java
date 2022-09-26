package com.example.spending.services;

import com.example.spending.dtos.PurchaseRequestDTO;
import com.example.spending.dtos.PurchaseResponseDTO;
import com.example.spending.models.Purchase;
import java.util.List;

public interface PurchaseService {

  List<PurchaseResponseDTO> collectPurchaseResponses(String currency, String sortMode, String sortOrder);

  Purchase handlePurchaseRequestDTO(PurchaseRequestDTO purchaseRequestDTO);
}
