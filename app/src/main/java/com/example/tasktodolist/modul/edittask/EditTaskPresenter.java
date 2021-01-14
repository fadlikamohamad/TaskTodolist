package com.example.tasktodolist.modul.edittask;

import com.example.tasktodolist.data.model.Task;
import com.example.tasktodolist.data.source.remote.response.DataResponse;
import com.example.tasktodolist.data.source.remote.rest.ApiConnection;
import com.example.tasktodolist.data.source.remote.rest.InterfaceConnection;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditTaskPresenter implements EditTaskContract.Presenter{
    private final EditTaskContract.View view;
    List<Task> editedTask;

    public EditTaskPresenter(EditTaskContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void saveData(final String id, final String title, final String description){
        Task dataTask = new Task();
        dataTask.setTask_title(title);
        dataTask.setTask_description(description);
        InterfaceConnection interfaceConnection = ApiConnection.getClient().create(InterfaceConnection.class);
        Call<DataResponse> update_data_task = interfaceConnection.updateTask(dataTask.getTask_title(), dataTask.getTask_description(), id);
        update_data_task.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {

            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {

            }
        });
        view.redirectToTaskList();
    }

    @Override
    public void loadData(String id) {
        InterfaceConnection interfaceConnection = ApiConnection.getClient().create(InterfaceConnection.class);
        Call<DataResponse> load_task = interfaceConnection.getTaskDetail(id);
        load_task.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.isSuccessful()) {
                    editedTask = response.body().getAllTask();
                } else {

                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {

            }
        });
        view.showData(editedTask);
    }
}
