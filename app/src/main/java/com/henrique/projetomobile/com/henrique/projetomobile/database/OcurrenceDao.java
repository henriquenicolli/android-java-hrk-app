package com.henrique.projetomobile.com.henrique.projetomobile.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.henrique.projetomobile.com.henrique.projetomobile.model.Ocurrence;

import java.util.List;

@Dao
public interface OcurrenceDao {

    @Query("SELECT * FROM ocurrence")
    List<Ocurrence> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<Ocurrence> ocurrences);

    @Insert
    void insert(Ocurrence ocurrence);

    @Delete
    void remove(Ocurrence ocurrence);

    @Update
    void update(Ocurrence ocurrence);

}
