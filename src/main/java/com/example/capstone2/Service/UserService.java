package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Appointment;
import com.example.capstone2.Model.Prescription;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final MedicineRepository medicineRepository;


    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void add(User user) {
        userRepository.save(user);
    }

    public void update(int id, User user) throws ApiException {
        User u = userRepository.findUserById(id);
        if (u == null) {
            throw new ApiException("user not found");
        }
        u.setName(user.getName());
        u.setAge(user.getAge());
        u.setPhoneNumber(user.getPhoneNumber());
        u.setEmail(user.getEmail());
        u.setBalance(user.getBalance());
        userRepository.save(u);
    }

    public void delete(int id) throws ApiException {
        User u = userRepository.findUserById(id);
        if (u == null) {
            throw new ApiException("user not found");
        }
        userRepository.delete(u);
    }

    public User findUserById(int id) throws ApiException {
        User u = userRepository.findUserById(id);
        if (u == null) {
            throw new ApiException("user not found");
        }
        return u;
    }

    public void bookingAppointment(int userId, int appointmentID) throws ApiException {
        User u = userRepository.findUserById(userId);
        Appointment a = appointmentRepository.findAppointmentById(appointmentID);
        if (u == null) {
            throw new ApiException("user not found");
        }
        if (a == null) {
            throw new ApiException("appointment not found");
        }
        if (a.getUserID() == 0) {
            a.setUserID(userId);
            appointmentRepository.save(a);
        }
        else
            throw new ApiException("appointment is already booked");

    }

    public List<Appointment> myAppointments(int userID) throws ApiException {
        List<Appointment> a = appointmentRepository.findAppointmentByUserID(userID);
        if (a == null) {
            throw new ApiException("no appointments found");
        }
        return a;
    }

    public void cancelAppointment(int userID, int appointmentID) throws ApiException {
        User u = userRepository.findUserById(userID);
        Appointment a = appointmentRepository.findAppointmentById(appointmentID);
        if (u == null) {
            throw new ApiException("user not found");
        }
        if (a == null) {
            throw new ApiException("appointment not found");
        }
        if (a.getUserID() == u.getId()) {
            a.setStatus("canceled");
            appointmentRepository.save(a);
        }
    }
    public void pay(int userID) throws ApiException {
        User u = userRepository.findUserById(userID);
        if (u == null) {
            throw new ApiException("user not found");
        }
        if (u.getBalance() < 0) {
            throw new ApiException("user balance is negative");
        }
        List<Appointment> appointmentList = appointmentRepository.findAppointmentByUserID(userID);
        if (appointmentList != null) {
            for (Appointment a1 : appointmentList) {
                u.setBalance(u.getBalance() - a1.getPrice());
                userRepository.save(u);
            }
        }

        List<Prescription> prescriptionList = prescriptionRepository.findPrescriptionByUserID(userID);
        if (prescriptionList != null) {
            for (Prescription p1 : prescriptionList) {
                if (p1.isRedeemed()){
                    u.setBalance(u.getBalance() - medicineRepository.findMedicineById(p1.getMedicineID()).getPrice());
                    userRepository.save(u);
                }
            }
        }
    }

    public List<Appointment> showAvailableAppointments () throws ApiException {
        List<Appointment> appointmentList = appointmentRepository.findAll();
        if (appointmentList == null || appointmentList.isEmpty()) {
            throw new ApiException("no appointments found");
        }
        List<Appointment> availableAppointments = new ArrayList<>();
        for (Appointment a : appointmentList) {
            if (a.getUserID() == 0)
                availableAppointments.add(a);
        }
        return availableAppointments;
    }
}

