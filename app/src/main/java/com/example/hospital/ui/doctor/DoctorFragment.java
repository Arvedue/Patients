package com.example.hospital.ui.doctor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospital.databinding.FragmentDoctorBinding;
import com.example.hospital.entities.Doctor;

import java.util.List;

public class DoctorFragment extends Fragment {

    private DoctorViewModel doctorViewModel;
    private FragmentDoctorBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        doctorViewModel = new ViewModelProvider(this).get(DoctorViewModel.class);

        binding = FragmentDoctorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.recyclerViewDoctor;
        recyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        recyclerView.setHasFixedSize(true);

        final DoctorAdapter doctorAdapter = new DoctorAdapter();
        recyclerView.setAdapter(doctorAdapter);
        doctorViewModel.getAllDoctorsLive().observe(requireActivity(), new Observer<List<Doctor>>() {
            @Override
            public void onChanged(List<Doctor> doctors) {
                doctorAdapter.setDoctors(doctors);
                doctorAdapter.setDoctorViewModel(doctorViewModel);
                doctorAdapter.setDoctorFragment(DoctorFragment.this);
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