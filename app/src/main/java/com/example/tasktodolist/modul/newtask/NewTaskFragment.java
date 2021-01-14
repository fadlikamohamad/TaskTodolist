package com.example.tasktodolist.modul.newtask;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.tasktodolist.R;
import com.example.tasktodolist.base.BaseFragment;
import com.example.tasktodolist.modul.dashboard.DashboardActivity;
import com.google.android.material.textfield.TextInputEditText;

public class NewTaskFragment extends BaseFragment<NewTaskActivity, NewTaskContract.Presenter> implements NewTaskContract.View {
    TextInputEditText etTaskTitle;
    TextInputEditText etTaskDescription;
    Button btnSave;
    String email;

    public NewTaskFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_new_task, container, false);
        mPresenter = new NewTaskPresenter(this);
        mPresenter.start();
        etTaskTitle = fragmentView.findViewById(R.id.inputTaskTitle);
        etTaskDescription = fragmentView.findViewById(R.id.inputTaskDescription);
        btnSave = fragmentView.findViewById(R.id.btnAddTask);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtSaveClick();
            }
        });
        setTitle("Add New Task");
        return fragmentView;
    }

    public void setBtSaveClick(){
        String title = etTaskTitle.getText().toString();
        String description = etTaskDescription.getText().toString();
        mPresenter.saveDataTask(title,description,email);
    }

    @Override
    public void setPresenter(NewTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToTaskList() {
        Intent intent = new Intent(activity, DashboardActivity.class);
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
}
