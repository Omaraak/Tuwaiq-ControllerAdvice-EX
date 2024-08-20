package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Medicine;
import com.example.capstone2.Model.Pharmacy;
import com.example.capstone2.Model.Prescription;
import com.example.capstone2.Repository.MedicineRepository;
import com.example.capstone2.Repository.PharmacyRepository;
import com.example.capstone2.Repository.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PharmacyService {
    private final PharmacyRepository pharmacyRepository;
    private final MedicineRepository medicineRepository;
    private final PrescriptionRepository prescriptionRepository;

    public List<Pharmacy> getAll() {
        return pharmacyRepository.findAll();
    }

    public void add(Pharmacy pharmacy) {
        pharmacyRepository.save(pharmacy);
    }

    public void update(int id, Pharmacy pharmacy) throws ApiException {
        Pharmacy u = pharmacyRepository.findPharmacyById(id);
        if (u == null) {
            throw new ApiException("pharmacy not found");
        }
        u.setName(pharmacy.getName());
        u.setAddress(pharmacy.getAddress());
        u.setPhone(pharmacy.getPhone());
        pharmacyRepository.save(u);
    }

    public void delete(int id) throws ApiException {
        Pharmacy u = pharmacyRepository.findPharmacyById(id);
        if (u == null) {
            throw new ApiException("pharmacy not found");
        }
        pharmacyRepository.delete(u);
    }

    public Pharmacy findPharmacyById(int id) throws ApiException {
        Pharmacy u = pharmacyRepository.findPharmacyById(id);
        if (u == null) {
            throw new ApiException("pharmacy not found");
        }
        return u;
    }

    public void redeemPrescription(int prescriptionID) throws ApiException {
        Prescription p = prescriptionRepository.findPrescriptionById(prescriptionID);
        if (p == null) {
            throw new ApiException("prescription not found");
        }
        Medicine m = medicineRepository.findMedicineById(p.getMedicineID());
        if (m == null) {
            throw new ApiException("medicine not found");
        }
        if (m.getQuantity() > 0 && !p.isRedeemed()){
            m.setQuantity(m.getQuantity() - 1);
            p.setRedeemed(true);
            medicineRepository.save(m);
            prescriptionRepository.save(p);
        }
        else {
            throw new ApiException("medicine out of stock or prescription is already redeemed");
        }
    }

    public void restock(int medicineID, int pharmacyID, int amount) throws ApiException {
        Medicine m = medicineRepository.findMedicineById(medicineID);
        if (m == null) {
            throw new ApiException("medicine not found");
        }
        Pharmacy p = pharmacyRepository.findPharmacyById(pharmacyID);
        if (p.getId() == m.getPharmacyID()){
            m.setQuantity(m.getQuantity() + amount);
            medicineRepository.save(m);
        }
    }

    public List<Medicine> lowStock(int pharmacyID, int threshold) throws ApiException {
        Pharmacy p = pharmacyRepository.findPharmacyById(pharmacyID);
        if (p == null) {
            throw new ApiException("pharmacy not found");
        }
        List<Medicine> lowStock = medicineRepository.findMedicineByPharmacyID(pharmacyID);
        List<Medicine> medicineList = new ArrayList<>();
        for (Medicine m : lowStock) {
            if (m.getQuantity() < threshold) {
                medicineList.add(m);
            }
        }
        return medicineList;
    }
}
