package com.admsoft.mdsaml;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class TransactionFileUploadTests {
    @Autowired
    private TestEntityManager entityManager;
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
        TransactionFile savedTransactionfile=repository.save(transactionFile);
        TransactionFile existTransactionfile= entityManager.find(TransactionFile.class,savedTransactionfile.getId());
        assertThat(transactionFile.getId()).isEqualTo(existTransactionfile.getId());
    }
}
