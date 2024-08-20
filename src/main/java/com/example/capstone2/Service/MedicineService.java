package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Medicine;
import com.example.capstone2.Repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineService {
    private final MedicineRepository medicineRepository;

    public List<Medicine> getAll() {
        return medicineRepository.findAll();
    }

    public void add(Medicine medicine) {
        medicineRepository.save(medicine);
    }

    public void update(int id, Medicine medicine) throws ApiException {
        Medicine u = medicineRepository.findMedicineById(id);
        if (u == null) {
            throw new ApiException("medicine not found");
        }
        u.setName(medicine.getName());
        u.setPrice(medicine.getPrice());
        u.setQuantity(medicine.getQuantity());
        medicineRepository.save(u);
    }

    public void delete(int id) throws ApiException {
        Medicine u = medicineRepository.findMedicineById(id);
        if (u == null) {
            throw new ApiException("medicine not found");
        }
        medicineRepository.delete(u);
    }

    public Medicine findMedicineById(int id) throws ApiException {
        Medicine u = medicineRepository.findMedicineById(id);
        if (u == null) {
            throw new ApiException("medicine not found");
        }
        return u;
    }
}
