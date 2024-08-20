package com.example.capstone2.Repository;

import com.example.capstone2.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
    Appointment findAppointmentById(int id);
    Appointment findAppointmentByStartTimeAndEndTimeAndDoctorID(String startTime, String endTime, int doctorID);
    List<Appointment> findAppointmentByDoctorID(int doctorID);
    List<Appointment> findAppointmentByUserID(int userID);
}
