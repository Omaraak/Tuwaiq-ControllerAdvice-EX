package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Appointment;
import com.example.capstone2.Model.Doctor;
import com.example.capstone2.Model.Prescription;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.AppointmentRepository;
import com.example.capstone2.Repository.DoctorRepository;
import com.example.capstone2.Repository.PrescriptionRepository;
import com.example.capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final PrescriptionRepository prescriptionRepository;

    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }

    public void add(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public void update(int id, Doctor doctor) throws ApiException {
        Doctor u = doctorRepository.findDoctorById(id);
        if (u == null) {
            throw new ApiException("doctor not found");
        }
        u.setName(doctor.getName());
        u.setPosition(doctor.getPosition());
        u.setSpeciality(doctor.getSpeciality());
        u.setPhone(doctor.getPhone());
        u.setSalary(doctor.getSalary());
        u.setAge(doctor.getAge());
        doctorRepository.save(u);
    }

    public void delete(int id) throws ApiException {
        Doctor u = doctorRepository.findDoctorById(id);
        if (u == null) {
            throw new ApiException("doctor not found");
        }
        doctorRepository.delete(u);
    }

    public Doctor findDoctorById(int id) throws ApiException {
        Doctor u = doctorRepository.findDoctorById(id);
        if (u == null) {
            throw new ApiException("doctor not found");
        }
        return u;
    }

    public void addAppointment(Appointment appointment) throws ApiException {
        Doctor d = doctorRepository.findDoctorById(appointment.getDoctorID());
        if (d == null) {
            throw new ApiException("doctor not found");
        }
        Appointment a = appointmentRepository.findAppointmentByStartTimeAndEndTimeAndDoctorID(appointment.getStartTime(), appointment.getEndTime(),d.getId());
        if (a == null) {
            appointmentRepository.save(appointment);
        }
        else
            throw new ApiException("appointment already exist");
    }

    public void makePrescription(Prescription prescription) throws ApiException {
        Doctor d = doctorRepository.findDoctorById(prescription.getDoctorID());
        User u = userRepository.findUserById(prescription.getUserID());
        if (d == null) {
            throw new ApiException("doctor not found");
        }
        if (u == null) {
            throw new ApiException("user not found");
        }
        prescriptionRepository.save(prescription);
    }

    public List<Appointment> myAppointments(int doctorID) throws ApiException {
        Doctor d = doctorRepository.findDoctorById(doctorID);
        if (d == null) {
            throw new ApiException("doctor not found");
        }
        return appointmentRepository.findAppointmentByDoctorID(doctorID);
    }

}
