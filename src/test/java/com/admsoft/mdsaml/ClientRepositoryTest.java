package com.admsoft.mdsaml;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

import java.util.List;

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

        List<Client> client =clientRepository.findByUser("drunkpiglerojnt@gmail.com");
        User user = userRepository.findByEmail("drunkpiglerojnt@gmail.com");
    }



}
