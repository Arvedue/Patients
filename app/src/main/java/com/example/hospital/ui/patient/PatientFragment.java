package com.example.hospital.ui.patient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospital.databinding.FragmentPatientBinding;
import com.example.hospital.entities.Patient;
import com.example.hospital.interfaces.RecyclerViewInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PatientFragment extends Fragment {

    private PatientViewModel patientViewModel;
    private FragmentPatientBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);

        binding = FragmentPatientBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.recyclerViewPatient;
        recyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        recyclerView.setHasFixedSize(true);

        final PatientAdapter patientAdapter = new PatientAdapter();
        recyclerView.setAdapter(patientAdapter);
        patientViewModel.getAllPatients().observe(requireActivity(), new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                patientAdapter.setPatients(patients);
                patientAdapter.setPatientViewModel(patientViewModel);
                patientAdapter.setPatientFragment(PatientFragment.this);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}