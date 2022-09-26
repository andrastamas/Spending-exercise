package com.example.spending.repositories;

import com.example.spending.models.Purchase;
import java.util.List;
import net.bytebuddy.TypeCache.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

  // huf/usd dátum szerint < || >
  @Query("SELECT p FROM Purchase p where p.currency = :currency order by p.issueDate + sortOrder")
  List<Purchase> findByCurrency(String currency,Sort sortOrder);

  // huf/usd összeg szerint < || >
  @Query("SELECT p FROM Purchase p where p.currency = :currency order by p.cost sortOrder")
  List<Purchase> findByCost(String currency, Sort sortOrder);

}
