package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Medicine;
import com.example.capstone2.Model.Prescription;
import com.example.capstone2.Repository.MedicineRepository;
import com.example.capstone2.Repository.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;

    public List<Prescription> getAll() {
        return prescriptionRepository.findAll();
    }

    public void add(Prescription prescription) {
        prescriptionRepository.save(prescription);
    }

    public void update(int id, Prescription prescription) throws ApiException {
        Prescription u = prescriptionRepository.findPrescriptionById(id);
        if (u == null) {
            throw new ApiException("prescription not found");
        }
        u.setUserID(prescription.getUserID());
        u.setMedicineID(prescription.getMedicineID());
        prescriptionRepository.save(u);
    }

    public void delete(int id) throws ApiException {
        Prescription u = prescriptionRepository.findPrescriptionById(id);
        if (u == null) {
            throw new ApiException("prescription not found");
        }
        prescriptionRepository.delete(u);
    }

    public Prescription findPrescriptionById(int id) throws ApiException {
        Prescription u = prescriptionRepository.findPrescriptionById(id);
        if (u == null) {
            throw new ApiException("prescription not found");
        }
        return u;
    }
}
