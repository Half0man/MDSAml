package com.admsoft.mdsaml;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    @Query("SELECT u FROM Client  u WHERE u.id = ?1")
    public Client findById(long id);
}
