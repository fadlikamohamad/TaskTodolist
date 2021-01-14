package com.example.tasktodolist.modul.dashboard;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasktodolist.data.model.Task;
import com.example.tasktodolist.data.source.remote.response.DataResponse;
import com.example.tasktodolist.data.source.remote.rest.ApiConnection;
import com.example.tasktodolist.data.source.remote.rest.InterfaceConnection;
import com.example.tasktodolist.utils.TaskListAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_SHORT;

public class DashboardPresenter implements DashboardContract.Presenter{
    private final DashboardContract.View view;

    public DashboardPresenter(DashboardContract.View view) {           //add
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public ArrayList<Task> getDataSet() {
        //get Data from API
        ArrayList<Task> data = new ArrayList<>();
        InterfaceConnection interfaceConnection;
        interfaceConnection = ApiConnection.getClient().create(InterfaceConnection.class);
        Call<DataResponse> taskList = interfaceConnection.getTask();
        Log.d("daftar", taskList.toString());
        taskList.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.isSuccessful()) {
                    List<Task> allTask = response.body().getAllTask();
                    data.addAll(allTask);
                    //data.add(new Task("Response", "Task Response", "1"));
                } else {
                    //data.add(new Task("UnResponse", "Task UnResponse", "1"));
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                //data.add(new Task("Failure", "Task Failure", "1"));
            }
        });
        //data.add(new Task("6", "Task6", "2"));
        //data.add(new Task("7", "Task7", "2"));
        return data;
    }
}
