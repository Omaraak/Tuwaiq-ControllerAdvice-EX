package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Pharmacy;
import com.example.capstone2.Service.PharmacyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hms/pharmacy")
@RequiredArgsConstructor
public class PharmacyController {
    private final PharmacyService pharmacyService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Pharmacy>> getAll() {
        return ResponseEntity.status(200).body(pharmacyService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Pharmacy pharmacy, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        pharmacyService.add(pharmacy);
        return ResponseEntity.status(201).body("Pharmacy added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@Valid @RequestBody Pharmacy pharmacy, @PathVariable int id, Errors errors) throws ApiException {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        pharmacyService.update(id, pharmacy);
        return ResponseEntity.status(200).body("Pharmacy updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id) throws ApiException {
        pharmacyService.delete(id);
        return ResponseEntity.status(200).body("Pharmacy deleted");
    }

    @GetMapping("/getPharmacyById/{id}")
    public ResponseEntity<Pharmacy> getPharmacyById(@PathVariable int id) throws ApiException {
        return ResponseEntity.status(200).body(pharmacyService.findPharmacyById(id));
    }

    @PutMapping("/redeemPrescription/{prescriptionID}")
    public ResponseEntity redeemPrescription(@PathVariable int prescriptionID) throws ApiException {
        pharmacyService.redeemPrescription(prescriptionID);
        return ResponseEntity.status(200).body("Prescription is redeemed");
    }

    @PutMapping("/restock/{medicineID}/{pharmacyID}/{amount}")
    public ResponseEntity restock(@PathVariable int medicineID, @PathVariable int pharmacyID, @PathVariable int amount) throws ApiException {
        pharmacyService.restock(medicineID, pharmacyID, amount);
        return ResponseEntity.status(200).body("Prescription is restocked");
    }

    @GetMapping("/lowStock/{pharmacyID}/{threshold}")
    public ResponseEntity lowStock(@PathVariable int pharmacyID, @PathVariable int threshold) throws ApiException {
        return ResponseEntity.status(200).body(pharmacyService.lowStock(pharmacyID, threshold));
    }
}
