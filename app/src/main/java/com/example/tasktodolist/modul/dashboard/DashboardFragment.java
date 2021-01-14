package com.example.tasktodolist.modul.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasktodolist.R;
import com.example.tasktodolist.base.BaseFragment;
import com.example.tasktodolist.data.model.Task;
import com.example.tasktodolist.data.source.remote.response.DataResponse;
import com.example.tasktodolist.data.source.remote.rest.ApiConnection;
import com.example.tasktodolist.data.source.remote.rest.InterfaceConnection;
import com.example.tasktodolist.modul.edittask.EditTaskActivity;
import com.example.tasktodolist.modul.newtask.NewTaskActivity;
import com.example.tasktodolist.modul.sharetask.ShareTaskActivity;
import com.example.tasktodolist.utils.TaskListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends BaseFragment<DashboardActivity, DashboardContract.Presenter> implements DashboardContract.View {
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    FloatingActionButton buttonAdd;
    String email;

    public DashboardFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        mPresenter = new DashboardPresenter(this);
        mPresenter.start();
        mRecyclerView = fragmentView.findViewById(R.id.rvTaskList);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        final ArrayList<Task> data = mPresenter.getDataSet();
        mAdapter = new TaskListAdapter(data);
        mRecyclerView.setAdapter(mAdapter);
        setTitle("Task Todolist");

        buttonAdd = fragmentView.findViewById(R.id.btnNew);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoNewTask();
            }
        });


        ((TaskListAdapter) mAdapter).setOnItemClickListener(new TaskListAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                String id = data.get(position).getTask_id();
                String title = data.get(position).getTask_title();
                String description = data.get(position).getTask_description();
                editTask(id, title, description);
            }
        });


        return fragmentView;
    }

    @Override
    public void setPresenter(DashboardContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void gotoNewTask() {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(activity, NewTaskActivity.class);
        bundle.putString("email", email);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void editTask(String id, String title, String description) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(activity, EditTaskActivity.class);
        bundle.putString("taskId", id);
        bundle.putString("taskTitle", title);
        bundle.putString("taskDescription", description);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Task> taskList = new ArrayList<>();
        RecyclerView taskTable;
        InterfaceConnection interfaceConnection;
        TaskListAdapter taskListAdapter;
        taskTable = (RecyclerView)view.findViewById(R.id.rvTaskList);
        interfaceConnection = ApiConnection.getClient().create(InterfaceConnection.class);
        taskListAdapter = new TaskListAdapter(getContext());
        Call<DataResponse> data = interfaceConnection.getTaskByUser(email);
        Log.d("daftar", data.toString());
        data.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.isSuccessful()) {
                    List<Task> listTask = response.body().getAllTask();
                    taskList.addAll(listTask);
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getActivity(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                taskListAdapter.updateDataTask(taskList);
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                //taskList.add(new Task("Failure", "TF", "5"));
                //taskListAdapter.updateDataTask(taskList);
                Toast.makeText(getActivity(), "Failure", Toast.LENGTH_LONG).show();
            }
        });
        taskTable.setAdapter(taskListAdapter);
        taskTable.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
}