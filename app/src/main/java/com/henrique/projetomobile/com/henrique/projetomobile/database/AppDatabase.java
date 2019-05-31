package com.henrique.projetomobile.com.henrique.projetomobile.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.henrique.projetomobile.com.henrique.projetomobile.model.Location;
import com.henrique.projetomobile.com.henrique.projetomobile.model.Ocurrence;


@Database(entities = {Ocurrence.class, Location.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static String DATABASE_NAME = "gerenciamento_municipal";
    private static AppDatabase INSTANCE;

    public abstract OcurrenceDao ocurrenceDao();
    public abstract LocationDao locationDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
