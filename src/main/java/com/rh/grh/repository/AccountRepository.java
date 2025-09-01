package com.rh.grh.repository;

import com.rh.grh.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    // Rechercher un compte par username
    Optional<Account> findByUsername(String username);

    // Vérifier si un username existe déjà
    boolean existsByUsername(String username);
}
