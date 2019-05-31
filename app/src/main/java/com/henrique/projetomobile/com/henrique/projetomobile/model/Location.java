package com.henrique.projetomobile.com.henrique.projetomobile.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

import static android.arch.persistence.room.ForeignKey.CASCADE;


@Entity(tableName = "location",
        foreignKeys = @ForeignKey(entity = Ocurrence.class,
                                    parentColumns = "id", onDelete = CASCADE, onUpdate = CASCADE,
                                    childColumns  = "ocurrenceId"))
public class Location implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "cityName")
    public String cityName;

    @ColumnInfo(name = "country")
    public String country;

    @ColumnInfo(index = true)
    private int ocurrenceId;

    public Location(){

    }

    public int getOcurrenceId() {
        return ocurrenceId;
    }

    public void setOcurrenceId(int ocurrenceId) {
        this.ocurrenceId = ocurrenceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
