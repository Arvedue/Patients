package com.example.hospital.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hospital.entities.Patient;

import java.util.List;

@Dao
public interface PatientDao {

    @Insert
    void insert(Patient patient);

    @Delete
    void delete(Patient patient);

    @Update
    void update(Patient patient);

    @Query("SELECT * FROM patient")
    LiveData<List<Patient>> getAll();
}
