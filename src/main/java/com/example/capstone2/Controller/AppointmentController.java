package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Appointment;
import com.example.capstone2.Service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hms/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Appointment>> getAll() {
        return ResponseEntity.status(200).body(appointmentService.getAll());
    }

    @PostMapping("/add/{doctorID}")
    public ResponseEntity<String> add(@PathVariable int doctorID, @Valid @RequestBody Appointment appointment) throws ApiException {
        appointmentService.add(doctorID,appointment);
        return ResponseEntity.status(201).body("Appointment added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody Appointment appointment, @PathVariable int id) throws ApiException {
        appointmentService.update(id, appointment);
        return ResponseEntity.status(200).body("Appointment updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) throws ApiException {
        appointmentService.delete(id);
        return ResponseEntity.status(200).body("Appointment deleted");
    }

    @GetMapping("/getAppointmentById/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable int id) throws ApiException {
        return ResponseEntity.status(200).body(appointmentService.findAppointmentById(id));
    }
}
