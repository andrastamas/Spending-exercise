package com.example.spending.services;

import com.example.spending.dtos.PurchaseRequestDTO;
import com.example.spending.dtos.PurchaseResponseDTO;
import com.example.spending.enums.PurchaseFilter;
import com.example.spending.exceptions.InvalidCostException;
import com.example.spending.exceptions.InvalidCurrencyException;
import com.example.spending.exceptions.InvalidDescriptionAndCurrencyAndAmountException;
import com.example.spending.exceptions.InvalidDescriptionException;
import com.example.spending.exceptions.PurchaseQuieryException;
import com.example.spending.models.Purchase;
import com.example.spending.repositories.PurchaseRepository;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {

  private final PurchaseRepository purchaseRepository;

  @Autowired
  public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
    this.purchaseRepository = purchaseRepository;
  }

  // ő csinál a filterezett purchase listából response dto-t
  @Override
  public List<PurchaseResponseDTO> collectPurchaseResponses(String currency, String sortMode, String sortOrder) {
    List<Purchase> purchaseList = createPurchaseList(currency, sortMode, sortOrder);
    List<PurchaseResponseDTO> purchaseResponseDTOList = new ArrayList<>();
    ModelMapper modelMapper = new ModelMapper();
    for (Purchase purchase : purchaseList) {
      PurchaseResponseDTO purchaseResponseDTO = modelMapper.map(purchase, PurchaseResponseDTO.class);
      purchaseResponseDTOList.add(purchaseResponseDTO);
    }
    return purchaseResponseDTOList;
  }

  @Override
  public Purchase handlePurchaseRequestDTO(PurchaseRequestDTO purchaseRequestDTO) {
    validateInputDto(purchaseRequestDTO);
    return saveNewPurchase(purchaseRequestDTO);
  }

  // Mi alapján lesz adatbázis lekérdezés
  private List<Purchase> createPurchaseList(String currency, String sortMode, String sortOrder) {
    if (convertStringToPurchaseFilter(mode).equals(PurchaseFilter.ALLCOSTDESC)) {
      return purchaseRepository.findAllByOrderByCostDesc();
    } else if (convertStringToPurchaseFilter(mode).equals(PurchaseFilter.ALLCOSTASC)) {
      return purchaseRepository.findAllByOrderByCostAsc();
    } else if (convertStringToPurchaseFilter(mode).equals(PurchaseFilter.ALLDATEDESC)) {
      return purchaseRepository.findAllByOrderByIssueDateDesc();
    } else {
      return purchaseRepository.findAllByOrderByIssueDateAsc();
    }
  }

  private Purchase saveNewPurchase(PurchaseRequestDTO purchaseRequestDTO) {
    Purchase purchase = convertPurchaseFromPurchaseRequestDTO(purchaseRequestDTO);
    return purchaseRepository.save(purchase);
  }

  private Purchase convertPurchaseFromPurchaseRequestDTO(PurchaseRequestDTO purchaseRequestDTO) {
    ModelMapper modelMapper = new ModelMapper();
    Purchase currentPurchase = modelMapper.map(purchaseRequestDTO, Purchase.class);
    return currentPurchase;
  }

  private PurchaseFilter convertStringToPurchaseFilter(String mode) {
    try {
      return PurchaseFilter.valueOf(mode.toUpperCase());
    } catch (IllegalArgumentException exception) {
      throw new PurchaseQuieryException("Invalid purchase query mode.");
    }
  }

  private boolean validateInputDto(PurchaseRequestDTO purchaseRequestDTO) {
    String description = purchaseRequestDTO.getDescription();
    String currency = purchaseRequestDTO.getCurrency();
    Double amount = purchaseRequestDTO.getCost();

    if (isStringValueValid(description) && isStringValueValid(currency) && isDoubleCostValid(amount)) {
      throw new InvalidDescriptionAndCurrencyAndAmountException("All fields are required.");
    } else if (isStringValueValid(description)) {
      throw new InvalidDescriptionException("Description is required.");
    } else if (isStringValueValid(currency)) {
      throw new InvalidCurrencyException("Currency is required.");
    } else if (isDoubleCostValid(amount)) {
      throw new InvalidCostException("Cost is required");
    } else {
      return true;
    }
  }

  private boolean isStringValueValid(String input) {
    return input == null || input.isEmpty();
  }

  private boolean isDoubleCostValid(Double input) {
    return input == null;
  }
}
