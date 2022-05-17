package com.example.hospital.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.hospital.DateConverter;

import java.util.Date;

@Entity(tableName = "patient")
@TypeConverters(DateConverter.class)
public class Patient {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String tel;

    private Date createdAt;

    public Patient(String name, String tel) {
        this.name = name;
        this.tel = tel;
        this.createdAt = new Date();
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
