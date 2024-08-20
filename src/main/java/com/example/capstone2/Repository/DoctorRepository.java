package com.example.capstone2.Repository;

import com.example.capstone2.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Doctor findDoctorById(int id);
}
