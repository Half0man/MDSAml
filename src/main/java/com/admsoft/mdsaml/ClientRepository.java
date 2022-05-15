package com.admsoft.mdsaml;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    @Query("SELECT u FROM Client  u WHERE u.id = ?1")
    public Client findById(long id);
    @Query("SELECT u FROM Client  u WHERE u.name = ?1")
    public Client findByName(String name);
    @Query("FROM Client  c WHERE c.user.email = ?1")
    public List<Client> findByUser(String email);
}
