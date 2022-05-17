package com.example.hospital.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.hospital.R;
import com.example.hospital.entities.Doctor;
import com.example.hospital.entities.Patient;

import java.util.Date;

@Database(entities = {Patient.class, Doctor.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract PatientDao patientDao();

    public abstract DoctorDao doctorDao();

    public static synchronized AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "hospital_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private PatientDao patientDao;
        private DoctorDao doctorDao;

        private PopulateDbAsyncTask(AppDatabase database){
            patientDao = database.patientDao();
            doctorDao = database.doctorDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            patientDao.insert(new Patient("John Smith", "0777777777"));

            doctorDao.insert(new Doctor("Kid Norman", "Pediatrics", "112", R.drawable.doctor1));
            doctorDao.insert(new Doctor("Robert Williams", "Family medicine", "113", R.drawable.doctor2));
            doctorDao.insert(new Doctor("Charles David", "Dermatology", "114", R.drawable.doctor3));
            doctorDao.insert(new Doctor("Wallas Jennifer", "Allergy", "115", R.drawable.doctor4));
            doctorDao.insert(new Doctor("Born Elizabeth", "Immunology", "116", R.drawable.doctor5));
            doctorDao.insert(new Doctor("Matthew Sara", "Surgery", "117", R.drawable.doctor6));
            doctorDao.insert(new Doctor("Kim Steven", "Psychiatry", "118", R.drawable.doctor7));
            return null;
        }
    }
}
