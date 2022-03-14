package com.admsoft.mdsaml;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class TransactionFileUploadTests {
    @Autowired
    private TransactionFileRepository repository;
    @Test
    public void createTestTransactionfile(){
        TransactionFile transactionFile =new TransactionFile();
        transactionFile.setTypeOfAction("cash-in");
        transactionFile.setSourceId(1);
        transactionFile.setDestinationId(2);
        transactionFile.setAmountOfMoney(2137);
        transactionFile.setTransactionDate("01.01.2020 21:37");
        transactionFile.setTitle("cash-in");

    }
}
