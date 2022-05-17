package com.example.hospital.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "doctor")
public class Doctor {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String specialty;

    private String room;

    private int photoResource;

    public Doctor(String name, String specialty, String room, int photoResource) {
        this.name = name;
        this.specialty = specialty;
        this.room = room;
        this.photoResource = photoResource;
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

    public String getSpecialty() {
        return specialty;
    }

    public String getRoom() {
        return room;
    }

    public int getPhotoResource() {
        return photoResource;
    }
}
