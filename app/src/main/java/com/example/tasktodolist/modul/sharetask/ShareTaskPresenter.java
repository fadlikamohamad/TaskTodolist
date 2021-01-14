package com.example.tasktodolist.modul.sharetask;

import com.example.tasktodolist.data.source.remote.response.DataResponse;
import com.example.tasktodolist.data.source.remote.rest.ApiConnection;
import com.example.tasktodolist.data.source.remote.rest.InterfaceConnection;
import com.example.tasktodolist.modul.newtask.NewTaskContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShareTaskPresenter implements ShareTaskContract.Presenter{
    private final ShareTaskContract.View view;

    public ShareTaskPresenter(ShareTaskContract.View view) {           //add
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void saveDataTask(final String title, final String description, final String email){
        InterfaceConnection interfaceConnection = ApiConnection.getClient().create(InterfaceConnection.class);
        Call<DataResponse> add_task = interfaceConnection.insertTask(title, description, email);
        add_task.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.isSuccessful()) {

                } else {

                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {

            }
        });
        view.redirectToTaskList();
    }
}
