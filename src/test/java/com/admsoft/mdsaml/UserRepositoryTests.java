package com.admsoft.mdsaml;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest

@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private UserRepository repo;
    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("runkpiglerojt@gmail.com");
        user.setPassword("123");
        user.setFirstName("redzesz");
        user.setLastName("redzesz");
        List<Client> clientList= new ArrayList<>();
        Client client =new Client();
        client.setType("jhb");
        client.setName("ajd9uiuo");
        client.setUser(user);
        clientList.add(client);

        user.setClientSet(clientList);
        User savedUser = repo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());

    }
}
