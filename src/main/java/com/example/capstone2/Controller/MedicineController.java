package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Medicine;
import com.example.capstone2.Service.MedicineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hms/medicine")
@RequiredArgsConstructor
public class MedicineController {
    private final MedicineService medicineService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Medicine>> getAll() {
        return ResponseEntity.status(200).body(medicineService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Medicine medicine, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        medicineService.add(medicine);
        return ResponseEntity.status(201).body("Medicine added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@Valid @RequestBody Medicine medicine, @PathVariable int id, Errors errors) throws ApiException {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        medicineService.update(id, medicine);
        return ResponseEntity.status(200).body("Medicine updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id) throws ApiException {
        medicineService.delete(id);
        return ResponseEntity.status(200).body("Medicine deleted");
    }

    @GetMapping("/getMedicineById/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable int id) throws ApiException {
        return ResponseEntity.status(200).body(medicineService.findMedicineById(id));
    }
}
