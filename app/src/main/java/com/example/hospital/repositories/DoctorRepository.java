package com.example.hospital.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.hospital.entities.Doctor;
import com.example.hospital.room.AppDatabase;
import com.example.hospital.room.DoctorDao;

import java.util.List;

public class DoctorRepository {
    private DoctorDao doctorDao;
    private LiveData<List<Doctor>> allDoctors;

    public DoctorRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        doctorDao = database.doctorDao();
        allDoctors = doctorDao.getAll();
    }

    public void insert(Doctor doctor){
        new InsertDoctorAsyncTask(doctorDao).execute(doctor);
    }

    public void update(Doctor doctor){
        new UpdateDoctorAsyncTask(doctorDao).execute(doctor);
    }

    public void delete(Doctor doctor){
        new DeleteDoctorAsyncTask(doctorDao).execute(doctor);
    }

    public LiveData<List<Doctor>> getAllDoctors() {
        return allDoctors;
    }

    private static class InsertDoctorAsyncTask extends AsyncTask<Doctor, Void, Void> {
        private DoctorDao doctorDao;

        private InsertDoctorAsyncTask(DoctorDao doctorDao) {
            this.doctorDao = doctorDao;
        }

        @Override
        protected Void doInBackground(Doctor... doctors) {
            doctorDao.insert(doctors[0]);
            return null;
        }
    }

    private static class UpdateDoctorAsyncTask extends AsyncTask<Doctor, Void, Void> {
        private DoctorDao doctorDao;

        private UpdateDoctorAsyncTask(DoctorDao doctorDao) {
            this.doctorDao = doctorDao;
        }

        @Override
        protected Void doInBackground(Doctor... doctors) {
            doctorDao.update(doctors[0]);
            return null;
        }
    }

    private static class DeleteDoctorAsyncTask extends AsyncTask<Doctor, Void, Void> {
        private DoctorDao doctorDao;

        private DeleteDoctorAsyncTask(DoctorDao doctorDao) {
            this.doctorDao = doctorDao;
        }

        @Override
        protected Void doInBackground(Doctor... doctors) {
            doctorDao.delete(doctors[0]);
            return null;
        }
    }
}
