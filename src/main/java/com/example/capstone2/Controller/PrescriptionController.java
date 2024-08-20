package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Prescription;
import com.example.capstone2.Service.PrescriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hms/prescription")
@RequiredArgsConstructor
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Prescription>> getAll() {
        return ResponseEntity.status(200).body(prescriptionService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Prescription prescription, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        prescriptionService.add(prescription);
        return ResponseEntity.status(201).body("Prescription added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@Valid @RequestBody Prescription prescription, @PathVariable int id, Errors errors) throws ApiException {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        prescriptionService.update(id, prescription);
        return ResponseEntity.status(200).body("Prescription updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id) throws ApiException {
        prescriptionService.delete(id);
        return ResponseEntity.status(200).body("Prescription deleted");
    }

    @GetMapping("/getPrescriptionById/{id}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable int id) throws ApiException {
        return ResponseEntity.status(200).body(prescriptionService.findPrescriptionById(id));
    }
}
