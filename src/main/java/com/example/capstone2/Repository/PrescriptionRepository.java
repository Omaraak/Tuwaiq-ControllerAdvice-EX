package com.example.capstone2.Repository;

import com.example.capstone2.Model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {
    Prescription findPrescriptionById(int id);
    List<Prescription> findPrescriptionByUserID(int userID);
}
