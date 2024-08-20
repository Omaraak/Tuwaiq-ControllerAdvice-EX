package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Staff;
import com.example.capstone2.Service.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hms/staff")
@RequiredArgsConstructor
public class StaffController {
    private final StaffService staffService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Staff>> getAll() {
        return ResponseEntity.status(200).body(staffService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Staff staff, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        staffService.add(staff);
        return ResponseEntity.status(201).body("Staff added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@Valid @RequestBody Staff staff, @PathVariable int id, Errors errors) throws ApiException {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        staffService.update(id, staff);
        return ResponseEntity.status(200).body("Staff updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id) throws ApiException {
        staffService.delete(id);
        return ResponseEntity.status(200).body("Staff deleted");
    }

    @GetMapping("/getStaffById/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable int id) throws ApiException {
        return ResponseEntity.status(200).body(staffService.findStaffById(id));
    }

    @PutMapping("/admitUser/{staffID}/{userID}")
    public ResponseEntity admitUser(@PathVariable int staffID, @PathVariable int userID) throws ApiException {
        staffService.admitUser(staffID, userID);
        return ResponseEntity.status(200).body("User admitted");
    }

    @PutMapping("/payRoll/{staffID}")
    public ResponseEntity payRoll(@PathVariable int staffID) throws ApiException {
        staffService.payRoll(staffID);
        return ResponseEntity.status(200).body("payRoll is done");
    }

    @PutMapping("/warning/{staffID}/{doctorID}")
    public ResponseEntity warning(@PathVariable int staffID, @PathVariable int doctorID) throws ApiException {
        staffService.warning(staffID, doctorID);
        return ResponseEntity.status(200).body("warning is done");
    }

    @PutMapping("/raise/{staffID}/{doctorID}")
    public ResponseEntity raise(@PathVariable int staffID, @PathVariable int doctorID) throws ApiException {
        staffService.raise(staffID, doctorID);
        return ResponseEntity.status(200).body("raise is done");
    }
}
