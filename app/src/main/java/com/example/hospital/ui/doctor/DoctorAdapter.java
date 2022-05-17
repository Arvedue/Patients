package com.example.hospital.ui.doctor;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospital.R;
import com.example.hospital.databinding.DoctorItemBinding;
import com.example.hospital.entities.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorHolder> {

    private List<Doctor> doctors = new ArrayList<>();
    private DoctorViewModel doctorViewModel;
    private DoctorFragment doctorFragment;

    @NonNull
    @Override
    public DoctorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DoctorItemBinding binding = DoctorItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new DoctorHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorHolder holder, int position) {
        Doctor doctor = doctors.get(position);
        holder.binding.name.setText(doctor.getName());
        holder.binding.specialty.setText(doctor.getSpecialty());
        holder.binding.room.setText("Room number: " + doctor.getRoom());
        holder.binding.photo.setImageResource(doctor.getPhotoResource());
        holder.binding.menu.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(holder.binding.getRoot().getContext(), holder.binding.menu);
            popupMenu.getMenuInflater().inflate(R.menu.card_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getTitle().toString()){
                        case "Позвогить в регистратуру":
                            holder.binding.getRoot().getContext().startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0555203613")));
                            return true;

                        case "Записаться к врачу":
                            // TODO navigateToAddPatient
                            return true;
                    }
                    return true;
                }
            });
            popupMenu.show();
        });
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    static class DoctorHolder extends RecyclerView.ViewHolder {
        DoctorItemBinding binding;

        public DoctorHolder(@NonNull DoctorItemBinding doctorItemBinding) {
            super(doctorItemBinding.getRoot());
            this.binding = doctorItemBinding;
        }
    }

    public void setDoctors(List<Doctor> doctors){
        this.doctors = doctors;
        notifyDataSetChanged();
    }

    public void setDoctorViewModel(DoctorViewModel doctorViewModel){
        this.doctorViewModel = doctorViewModel;
        notifyDataSetChanged();
    }

    public void setDoctorFragment(DoctorFragment doctorFragment){
        this.doctorFragment = doctorFragment;
        notifyDataSetChanged();
    }
}
