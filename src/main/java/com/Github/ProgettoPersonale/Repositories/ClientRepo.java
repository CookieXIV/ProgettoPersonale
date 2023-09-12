package com.Github.ProgettoPersonale.Repositories;

import com.Github.ProgettoPersonale.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
}
