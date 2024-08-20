package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Appointment;
import com.example.capstone2.Model.Doctor;
import com.example.capstone2.Model.Prescription;
import com.example.capstone2.Service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hms/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Doctor>> getAll() {
        return ResponseEntity.status(200).body(doctorService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Doctor doctor, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        doctorService.add(doctor);
        return ResponseEntity.status(201).body("Doctor added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@Valid @RequestBody Doctor doctor, @PathVariable int id, Errors errors) throws ApiException {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        doctorService.update(id, doctor);
        return ResponseEntity.status(200).body("Doctor updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id) throws ApiException {
        doctorService.delete(id);
        return ResponseEntity.status(200).body("Doctor deleted");
    }

    @GetMapping("/getDoctorById/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable int id) throws ApiException {
        return ResponseEntity.status(200).body(doctorService.findDoctorById(id));
    }

    @PostMapping("/addAppointmentTime")
    public ResponseEntity addAppointmentTime(@Valid @RequestBody Appointment appointment, Errors errors) throws ApiException {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        doctorService.addAppointment(appointment);
        return ResponseEntity.status(201).body("Appointment added");
    }

    @PostMapping("/makePrescription")
    public ResponseEntity makePrescription(@Valid @RequestBody Prescription prescription, Errors errors) throws ApiException {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        doctorService.makePrescription(prescription);
        return ResponseEntity.status(201).body("Prescription made");
    }

    @GetMapping("/myAppointments/{doctorID}")
    public ResponseEntity<List<Appointment>> getMyAppointments(@PathVariable int doctorID) throws ApiException {
        return ResponseEntity.status(200).body(doctorService.myAppointments(doctorID));
    }
}
