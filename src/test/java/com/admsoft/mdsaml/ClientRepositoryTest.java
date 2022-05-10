package com.admsoft.mdsaml;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ClientRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ClientRepository clientRepository;
    @Test
    public void clientRepoTest(){
        User user = userRepository.findByEmail("gregor0ad@gmail.com");
        Client client= new Client();
        client.setUser(user);
        client.setName("achmed goatfucker");
        client.setType("m25-40");
        clientRepository.save(client);
    }
}
