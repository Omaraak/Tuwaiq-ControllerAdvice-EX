package com.example.capstone2.Repository;

import com.example.capstone2.Model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
    Medicine findMedicineById(int id);
    List<Medicine> findMedicineByPharmacyID(int pharmacyID);
}
