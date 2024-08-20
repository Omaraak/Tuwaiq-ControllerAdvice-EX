package com.example.capstone2.Repository;

import com.example.capstone2.Model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer> {
    Pharmacy findPharmacyById(int id);
}
