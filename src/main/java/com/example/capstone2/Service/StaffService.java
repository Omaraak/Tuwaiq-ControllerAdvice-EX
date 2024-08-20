package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Appointment;
import com.example.capstone2.Model.Doctor;
import com.example.capstone2.Model.Staff;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.AppointmentRepository;
import com.example.capstone2.Repository.DoctorRepository;
import com.example.capstone2.Repository.StaffRepository;
import com.example.capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;
    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    public List<Staff> getAll() {
        return staffRepository.findAll();
    }

    public void add(Staff staff) {
        staffRepository.save(staff);
    }

    public void update(int id, Staff staff) throws ApiException {
        Staff u = staffRepository.findStaffById(id);
        if (u == null) {
            throw new ApiException("staff not found");
        }
        u.setName(staff.getName());
        u.setSalary(staff.getSalary());
        u.setRole(staff.getRole());
        staffRepository.save(u);
    }

    public void delete(int id) throws ApiException {
        Staff u = staffRepository.findStaffById(id);
        if (u == null) {
            throw new ApiException("staff not found");
        }
        staffRepository.delete(u);
    }

    public Staff findStaffById(int id) throws ApiException {
        Staff u = staffRepository.findStaffById(id);
        if (u == null) {
            throw new ApiException("staff not found");
        }
        return u;
    }

    public void admitUser(int staffID, int userID) throws ApiException {
        Staff staff = staffRepository.findStaffById(staffID);
        if (staff == null) {
            throw new ApiException("staff not found");
        }
        User user = userRepository.findUserById(userID);
        if (user == null) {
            throw new ApiException("user not found");
        }
        List<Appointment> appointments = appointmentRepository.findAppointmentByUserID(userID);
        if (appointments == null) {
            throw new ApiException("appointments not found");
        }
        if (staff.getRole().equals("receptionist")) {
            for (Appointment appointment : appointments) {
                if (appointment.getStatus().equals("conformed") && appointment.getType().equals("on premise")){
                    appointment.setStatus("in progress");
                    appointmentRepository.save(appointment);
                    break;
                }
            }
        }
    }

    public void payRoll(int staffID) throws ApiException {
        Staff staff = staffRepository.findStaffById(staffID);
        if (staff == null) {
            throw new ApiException("staff not found");
        }
        if (!staff.getRole().equals("Accountant")) {
            throw new ApiException("staff is not Accountant");
        }
        List<Doctor> doctors = doctorRepository.findAll();
        if (doctors != null && !doctors.isEmpty()) {
            for (Doctor doctor : doctors) {
                staff.setBudget(staff.getBudget() - doctor.getSalary());
            }
            staffRepository.save(staff);
        }
        List<Staff> staffs = staffRepository.findAll();
        if (staffs != null && !doctors.isEmpty()) {
            for (Staff s : staffs) {
                staff.setBudget(staff.getBudget() - s.getSalary());
            }
            staffRepository.save(staff);
        }

    }

    public void warning(int staffID, int doctorID) throws ApiException {
        Staff staff = staffRepository.findStaffById(staffID);
        if (staff == null) {
            throw new ApiException("staff not found");
        }
        Doctor doctor = doctorRepository.findDoctorById(doctorID);
        if (doctor == null) {
            throw new ApiException("doctor not found");
        }
        if (!staff.getRole().equals("HR")) {
            throw new ApiException("staff is not HR");
        }
        if (doctor.getWarning() == 3){
            doctor.setSalary(doctor.getSalary() - 200);
            doctorRepository.save(doctor);
        }
        if (doctor.getWarning() > 3){
            doctorRepository.delete(doctor);
        }
        if (staff.getRole().equals("HR")) {
            doctor.setWarning(doctor.getWarning() + 1);
            doctorRepository.save(doctor);
        }
    }

    public void raise(int staffID, int doctorID) throws ApiException {
        Staff staff = staffRepository.findStaffById(staffID);
        if (staff == null) {
            throw new ApiException("staff not found");
        }
        Doctor doctor = doctorRepository.findDoctorById(doctorID);
        if (doctor == null) {
            throw new ApiException("doctor not found");
        }
        if (!staff.getRole().equals("Accountant")) {
            throw new ApiException("staff is not Accountant");
        }
        doctor.setSalary(doctor.getSalary() * 1.05);
        doctorRepository.save(doctor);
    }
}
