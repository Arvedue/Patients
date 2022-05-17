package com.example.hospital.ui.doctor;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hospital.entities.Doctor;
import com.example.hospital.repositories.DoctorRepository;

import java.util.List;

public class DoctorViewModel extends AndroidViewModel {

    private DoctorRepository doctorRepository;
    private LiveData<List<Doctor>> allDoctors;

    public DoctorViewModel(@NonNull Application application) {
        super(application);
        doctorRepository = new DoctorRepository(application);
        allDoctors = doctorRepository.getAllDoctors();
    }

    public LiveData<List<Doctor>> getAllDoctorsLive() {
        return allDoctors;
    }

    public void insert(Doctor doctor) {
        doctorRepository.insert(doctor);
    }

    public void update(Doctor doctor) {
       doctorRepository.update(doctor);
    }

    public void delete(Doctor doctor) {
        doctorRepository.delete(doctor);
    }

}
