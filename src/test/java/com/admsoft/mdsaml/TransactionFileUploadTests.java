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
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    UserRepository userRepository;
    @Test
    public void createTestTransactionfile(){
        TransactionFile transactionFile =new TransactionFile();
        transactionFile.setTypeOfAction("cash-in");
        transactionFile.setAmountOfMoney(2);
        transactionFile.setTransactionTime("2-3");
        transactionFile.setAmountOnMoneyLeft(4);
        Client client=new Client();
        client.setUser(userRepository.findByEmail("drunkpiglerojnt@gmail.com"));
        client.setType("m25-40");
        client.setName("cunt");
        clientRepository.save(client);
        transactionFile.setClient(client);
        repository.save(transactionFile);


    }
}
