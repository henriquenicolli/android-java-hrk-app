package com.henrique.projetomobile.com.henrique.projetomobile.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.henrique.projetomobile.com.henrique.projetomobile.model.Location;

import java.util.List;

@Dao
public interface LocationDao {

    @Query("SELECT * FROM location")
    List<Location> getAll();

    @Query("SELECT * FROM location WHERE location.ocurrenceId = :id")
    List<Location> getByOcurrenceId(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<Location> locatons);

    @Insert
    void insert(Location location);

    @Delete
    void remove(Location location);

    @Update
    void update(Location location);

}
