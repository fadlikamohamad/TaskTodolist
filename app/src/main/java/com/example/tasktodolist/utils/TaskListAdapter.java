package com.example.tasktodolist.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tasktodolist.R;
import com.example.tasktodolist.data.model.Task;
import com.example.tasktodolist.data.source.remote.response.DataResponse;
import com.example.tasktodolist.data.source.remote.rest.ApiConnection;
import com.example.tasktodolist.data.source.remote.rest.InterfaceConnection;
import com.example.tasktodolist.modul.dashboard.DashboardActivity;
import com.example.tasktodolist.modul.edittask.EditTaskActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {
    private static ArrayList<Task> mDataset;
    private static TaskListAdapter.MyClickListener myClickListener;
    Context mContext;
    public TaskListAdapter(ArrayList<Task> myDataset) {
        mDataset = myDataset;
    }
    String id;
    DashboardActivity dashboardActivity;

    public TaskListAdapter(Context context) {
        mDataset = new ArrayList<>();
        mContext = context;
    }

    public void updateDataTask(ArrayList<Task> data) {
        mDataset.clear();
        mDataset.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskListAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_task_list, parent, false);
        TaskListAdapter.TaskViewHolder myViewHolder = new TaskListAdapter.TaskViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskListAdapter.TaskViewHolder holder, int position) {
        holder.tvTaskId.setText(mDataset.get(position).getTask_id());
        holder.tvTaskTitle.setText(mDataset.get(position).getTask_title());
        holder.tvTaskDescription.setText(mDataset.get(position).getTask_description());
        holder.checkTask.setChecked(false);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = mDataset.get(position).getTask_id();
                popupDelete();
            }
        });
        holder.checkTask.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                id = mDataset.get(position).getTask_id();
                popupChecked();
            }
        });
    }

    private void popupChecked() {
        Context context = new ContextThemeWrapper(mContext, R.style.AppTheme);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(context);
        materialAlertDialogBuilder.setTitle("Check Task")
                .setMessage("Apa anda yakin sudah menyelesaikan task ini?")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //checkTask();
                        //deleteTask();
                        InterfaceConnection interfaceConnection = ApiConnection.getClient().create(InterfaceConnection.class);
                        Call<DataResponse> update_checked_task = interfaceConnection.updateCheckedTask(id);

                    }
                })
                .show();
    }

    private void checkTask() {
        InterfaceConnection interfaceConnection = ApiConnection.getClient().create(InterfaceConnection.class);
        Call<DataResponse> update_checked_task = interfaceConnection.updateCheckedTask(id);
        update_checked_task.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {

            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {

            }
        });
    }

    private void editTask(String id) {
        Intent intent = new Intent(String.valueOf(EditTaskActivity.class));
        intent.putExtra("TaskId", id);
        dashboardActivity.startActivity(intent);
    }

    private void popupDelete() {
        Context context = new ContextThemeWrapper(mContext, R.style.AppTheme);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(context);
        materialAlertDialogBuilder.setTitle("Delete Task")
                .setMessage("Apa anda yakin ingin menghapus task ini?")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteTask();
                    }
                })
                .show();
    }

    private void deleteTask() {
        InterfaceConnection interfaceConnection = ApiConnection.getClient().create(InterfaceConnection.class);
        Call<DataResponse> delete_data_task = interfaceConnection.deleteTask(id);
        delete_data_task.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(mContext,  "sukses", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(mContext, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return (mDataset != null) ? mDataset.size() : 0;
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTaskId;
        TextView tvTaskTitle;
        TextView tvTaskDescription;
        ImageButton btnDelete;
        CheckBox checkTask;
        public TaskViewHolder(View itemView) {
            super(itemView);
            tvTaskId = (TextView) itemView.findViewById(R.id.tvTaskId);
            tvTaskTitle = (TextView) itemView.findViewById(R.id.tvTaskTitle);
            tvTaskDescription = (TextView) itemView.findViewById(R.id.tvTaskDescription);
            btnDelete = (ImageButton) itemView.findViewById(R.id.imgBtnDeleteTask);
            checkTask = (CheckBox) itemView.findViewById(R.id.checkTask);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            myClickListener.onItemClick(position, view);
        }
    }

    public void setOnItemClickListener(TaskListAdapter.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}