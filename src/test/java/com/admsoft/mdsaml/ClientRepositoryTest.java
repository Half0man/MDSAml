package com.admsoft.mdsaml;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
@DataJpaTest
@EnableJpaRepositories("com.admsoft.mdsaml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ClientRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ClientRepository clientRepository;
    @Test
    public void clientRepoTest(){
        User user = userRepository.findByEmail("drunkpiglerojnt@gmail.com");
        Client client= new Client();
        client.setUser(user);
        client.setName("achmed goatfucker");
        client.setType("m25-40");
        clientRepository.save(client);
    }
}
