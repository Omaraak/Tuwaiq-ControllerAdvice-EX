package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Appointment;
import com.example.capstone2.Repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    public void add(int doctorID, Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    public void update(int id, Appointment appointment) throws ApiException {
        Appointment u = appointmentRepository.findAppointmentById(id);
        if (u == null) {
            throw new ApiException("appointment not found");
        }
        u.setStatus(appointment.getStatus());
        u.setType(appointment.getType());
        u.setUserID(appointment.getUserID());
        u.setDoctorID(appointment.getDoctorID());
        appointmentRepository.save(u);
    }

    public void delete(int id) throws ApiException {
        Appointment u = appointmentRepository.findAppointmentById(id);
        if (u == null) {
            throw new ApiException("appointment not found");
        }
        appointmentRepository.delete(u);
    }

    public Appointment findAppointmentById(int id) throws ApiException {
        Appointment u = appointmentRepository.findAppointmentById(id);
        if (u == null) {
            throw new ApiException("appointment not found");
        }
        return u;
    }
}
