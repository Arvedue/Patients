package com.example.hospital.ui.appointment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.hospital.databinding.FragmentAppointmentBinding;
import com.example.hospital.entities.Patient;
import com.example.hospital.repositories.PatientRepository;
import com.example.hospital.ui.patient.PatientViewModel;

public class AppointmentFragment extends Fragment {

    private FragmentAppointmentBinding binding;
    private EditText name;
    private EditText tel;
    private Button save;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AppointmentViewModel appointmentViewModel =
                new ViewModelProvider(this).get(AppointmentViewModel.class);

        binding = FragmentAppointmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.etName.getText().toString();
                String tel = binding.etTel.getText().toString();

                if(name.trim().isEmpty() || tel.trim().isEmpty()){
                    Toast.makeText(getContext(), "Please insert name and tel", Toast.LENGTH_SHORT).show();
                    return;
                }

                PatientRepository patientRepository = new PatientRepository(requireActivity().getApplication());
                patientRepository.insert(new Patient(name, tel));

                Toast.makeText(getContext(), "Patient is saved!", Toast.LENGTH_SHORT).show();
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