package com.iskandar.playgrounds.todolist;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iskandar.playgrounds.R;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    //initialize variable
    private List<ToDoListData> dataList;
    private Activity context;
    private RoomDB database;

    //create constructor
    public MainAdapter(Activity context,List<ToDoListData> dataList){
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //initialize main data
        ToDoListData data = dataList.get(position);
        //initialize database
        database = RoomDB.getInstance(context);
        //set text on text view
        holder.textView.setText(data.getText());

        holder.btEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //initialize main data
                ToDoListData d = dataList.get(holder.getAdapterPosition());
                //get id
                int sID = d.getID();
                //get text
                String sText = d.getText();

                //dialog
                Dialog dialog = new Dialog(context);
                //set content view
                dialog.setContentView(R.layout.dialog_update);
                //initialize width
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                //initialize height
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                //set layout
                dialog.getWindow().setLayout(width,height);
                //show dialog
                dialog.show();

                //initialize and assign variable
                EditText editText = dialog.findViewById(R.id.et_dialog);
                Button bt_update = dialog.findViewById(R.id.bt_update);

                //set text on edit text
                editText.setText(sText);

                bt_update.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        //dismiss dialog
                        dialog.dismiss();
                        //get updated text from edit text
                        String uText = editText.getText().toString().trim();
                        //update text in db
                        database.mainDao().update(sID, uText);
                        //notify when data is updated
                        dataList.clear();
                        dataList.addAll(database.mainDao().getAll());
                        notifyDataSetChanged();
                    }
                });
            }
        });

        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intialize main data
                ToDoListData d = dataList.get(holder.getAdapterPosition());
                //delete text from db
                database.mainDao().delete(d);
                //notify when data is deleted
                int position = holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,dataList.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //initialize variable
        TextView textView;
        ImageView btEdit, btDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //assigning variable
            textView = itemView.findViewById(R.id.tv_items);
            btEdit = itemView.findViewById(R.id.btn_edit);
            btDelete = itemView.findViewById(R.id.btn_del);

        }
    }
}
