package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Appointment;
import com.example.capstone2.Model.User;
import com.example.capstone2.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/hms/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.status(200).body(userService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody User user) {
        userService.add(user);
        return ResponseEntity.status(201).body("User added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@Valid @RequestBody User user, @PathVariable int id) throws ApiException {
        userService.update(id, user);
        return ResponseEntity.status(200).body("User updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id) throws ApiException {
        userService.delete(id);
        return ResponseEntity.status(200).body("User deleted");
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) throws ApiException {
        return ResponseEntity.status(200).body(userService.findUserById(id));
    }

    @PutMapping("/bookingAppointment/{userID}/{appointmentID}")
    public ResponseEntity bookingAppointment(@PathVariable int userID, @PathVariable int appointmentID) throws ApiException {
        userService.bookingAppointment(userID,appointmentID);
        return ResponseEntity.status(200).body("Booking completed");
    }

    @GetMapping("/myAppointments/{userID}")
    public ResponseEntity<List<Appointment>> getMyAppointments(@PathVariable int userID) throws ApiException {
        return ResponseEntity.status(200).body(userService.myAppointments(userID));
    }

    @PutMapping("/cancelAppointment/{userID}/{appointmentID}")
    public ResponseEntity cancelAppointment(@PathVariable int userID, @PathVariable int appointmentID) throws ApiException {
        userService.cancelAppointment(userID, appointmentID);
        return ResponseEntity.status(200).body("Appointment has been canceled");
    }

    @GetMapping("/showAvailableAppointments")
    public ResponseEntity<List<Appointment>> showAvailableAppointments() throws ApiException {
        return ResponseEntity.status(200).body(userService.showAvailableAppointments());
    }

    @PutMapping("/pay/{userID}")
    public ResponseEntity pay(@PathVariable int userID) throws ApiException {
        userService.pay(userID);
        return ResponseEntity.status(200).body("Payment is successful");
    }
}

