package com.Github.ProgettoPersonale.Repositories;

import com.Github.ProgettoPersonale.Entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepo extends JpaRepository<Staff, Long> {

}
