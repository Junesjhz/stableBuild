package com.iskandar.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class ToDoList extends AppCompatActivity {

    EditText editText;
    RecyclerView recyclerView;
    CardView cv_add, cv_reset;

    List<ToDoListData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        editText = findViewById(R.id.et_addTask);
        cv_add = findViewById(R.id.cv_btn_add);
        cv_reset = findViewById(R.id.cv_btn_reset);
        recyclerView = findViewById(R.id.rv_taskList);

        //intialize database
        database = RoomDB.getInstance(this);
        //store db value in data list
        dataList = database.mainDao().getAll();

        //intialize linear layout manager
        linearLayoutManager = new LinearLayoutManager(this);
        //set layout manager
        recyclerView.setLayoutManager(linearLayoutManager);
        //initialize adapter
        adapter = new MainAdapter(ToDoList.this,dataList);
        //set adapter
        recyclerView.setAdapter(adapter);

       cv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get string from each edit text
                String sText = editText.getText().toString().trim();
                //check condition
                if(!sText.equals("")){
                    //when text is not empty
                    //initialize main data
                    ToDoListData data = new ToDoListData();
                    //set text on main data
                    data.setText(sText);
                    //insert text in db
                    database.mainDao().insert(data);
                    //clear edit
                    editText.setText("");
                    //notify when data is inserted
                    dataList.clear();
                    dataList.addAll(database.mainDao().getAll());
                    adapter.notifyDataSetChanged();
                }
            }
        });

        cv_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete all data from db
                database.mainDao().reset(dataList);
                //notify when all data erased
                dataList.clear();
                dataList.addAll(database.mainDao().getAll());
                adapter.notifyDataSetChanged();
            }
        });
    }
}
