package com.Github.ProgettoPersonale.Repositories;


import com.Github.ProgettoPersonale.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    Optional<Order> findOrderByClientNameAndClientSurname (String clientName, String clientSurname);

    Optional<Order> findOrderByClientNumber(String clientNumber);

    Optional<Order> findOrderByClientEmail(String clientEmail);
}