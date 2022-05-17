package com.example.hospital.ui.patient;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospital.databinding.PatientItemBinding;
import com.example.hospital.entities.Patient;
import com.example.hospital.interfaces.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientHolder> {

    private List<Patient> patients = new ArrayList<>();
    private PatientViewModel patientViewModel;
    private PatientFragment patientFragment;

    @NonNull
    @Override
    public PatientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PatientItemBinding binding = PatientItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        PatientHolder patientHolder = new PatientHolder(binding);
        return patientHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PatientHolder holder, int position) {
        Patient patient = patients.get(position);
        holder.binding.name.setText(patient.getName());
        holder.binding.tel.setText(patient.getTel());
        holder.binding.date.setText(patient.getCreatedAt().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();

                if(pos != RecyclerView.NO_POSITION){
                    String information = patients.get(pos).getName() + ", your position in list - " + pos;
                    Toast.makeText(view.getContext(), information, Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.itemView.setOnLongClickListener(view -> {
            AlertDialog alertDialog = new AlertDialog.Builder(holder.itemView.getContext()).setMessage("Вы хотите удалить")
                    .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int pos) {
                            patientViewModel.delete(patient);
                            String information = patient.getName() + "was deleted.";
                            Toast.makeText(view.getContext(), information, Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("НЕТ", null).create();
            alertDialog.show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    static class PatientHolder extends RecyclerView.ViewHolder {
        private PatientItemBinding binding;

        public PatientHolder(@NonNull PatientItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;

        }
    }

    public void setPatients(List<Patient> patients){
        this.patients = patients;
        notifyDataSetChanged();
    }

    public void setPatientViewModel(PatientViewModel patientViewModel){
        this.patientViewModel = patientViewModel;
        notifyDataSetChanged();
    }

    public void setPatientFragment(PatientFragment patientFragment){
        this.patientFragment = patientFragment;
        notifyDataSetChanged();
    }
}
