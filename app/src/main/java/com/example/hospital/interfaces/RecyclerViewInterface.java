package com.example.hospital.interfaces;

import com.example.hospital.entities.Patient;

public interface RecyclerViewInterface {
    void onItemClick(String information, int position);

    void onItemLongClick(Patient patient, int position);
}
