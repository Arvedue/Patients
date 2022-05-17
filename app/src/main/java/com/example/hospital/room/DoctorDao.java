package com.example.hospital.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hospital.entities.Doctor;

import java.util.List;

@Dao
public interface DoctorDao {

    @Insert
    void insert(Doctor doctor);

    @Delete
    void delete(Doctor doctor);

    @Update
    void update(Doctor doctor);

    @Query("SELECT * FROM doctor")
    LiveData<List<Doctor>> getAll();
}
