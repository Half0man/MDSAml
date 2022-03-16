package com.admsoft.mdsaml;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionFileRepository extends JpaRepository<TransactionFile, Long> {
    @Query("SELECT u FROM TransactionFile  u WHERE u.id = ?1")
    public TransactionFile findById(long id);
}
