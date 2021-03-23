package com.iskandar.weatherapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDao {
    @Insert(onConflict = REPLACE) //insert query
    void insert(ToDoListData toDoListData);

    @Delete //delete query
    void delete(ToDoListData toDoListData);

    @Delete //delete all query
    void reset(List<ToDoListData> toDoListData);

    //update query
    @Query("UPDATE tbl_todolist SET text = :sText WHERE ID = :sID")
    void update(int sID, String sText);

    //get all data query
    @Query("SELECT * FROM tbl_todolist")
    List<ToDoListData> getAll();
}

