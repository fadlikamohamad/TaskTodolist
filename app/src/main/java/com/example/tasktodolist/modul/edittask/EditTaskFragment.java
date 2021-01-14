package com.example.tasktodolist.modul.edittask;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;

import com.example.tasktodolist.R;
import com.example.tasktodolist.base.BaseFragment;
import com.example.tasktodolist.data.model.Task;
import com.example.tasktodolist.modul.dashboard.DashboardActivity;
import com.example.tasktodolist.modul.sharetask.ShareTaskActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class EditTaskFragment extends BaseFragment<EditTaskActivity, EditTaskContract.Presenter> implements EditTaskContract.View {
    TextInputEditText etTaskTitle;
    TextInputEditText etTaskDescription;
    Button btnSave;
    Button btnShareWhatsapp;
    Button btnShareEmail;
    String id;
    String taskTitle;
    String description;

    public EditTaskFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_edit_data_task, container, false);
        mPresenter = new EditTaskPresenter(this);
        mPresenter.start();
        setTitle("Edit Task");
        //mPresenter.loadData(this.id);
        etTaskTitle = fragmentView.findViewById(R.id.editTaskTitle);
        etTaskDescription = fragmentView.findViewById(R.id.editTaskDescription);
        etTaskTitle.setText(taskTitle);
        etTaskDescription.setText(description);
        btnSave = fragmentView.findViewById(R.id.btnUpdateTask);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtSaveClick();
            }
        });
        btnShareWhatsapp = fragmentView.findViewById(R.id.btnShareWhatsapp);
        btnShareWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtShareWAClick();
            }
        });
        btnShareEmail = fragmentView.findViewById(R.id.btnShareEmail);
        btnShareEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtShareEmailClick();
            }
        });
        return fragmentView;
    }

    private void setBtShareEmailClick() {
        String title = etTaskTitle.getText().toString();
        String description = etTaskDescription.getText().toString();
        Bundle bundle = new Bundle();
        Intent intent = new Intent(activity, ShareTaskActivity.class);
        bundle.putString("taskTitle", title);
        bundle.putString("taskDescription", description);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void setBtShareWAClick() {
        String task_title = etTaskTitle.getText().toString();
        String task_description = etTaskDescription.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(activity)
                .setType(mimeType)
                .setChooserTitle("Share Task with: ")
                .setText("Task Title : " + task_title + ", Task Description : " + task_description)
                .startChooser();
    }

    public void setBtSaveClick(){
        String title = etTaskTitle.getText().toString();
        String description = etTaskDescription.getText().toString();
        mPresenter.saveData(id, title,description);
    }

    @Override
    public void setPresenter(EditTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToTaskList() {
        Intent intent = new Intent(activity, DashboardActivity.class);
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void showData(List<Task> task) {
        this.etTaskTitle.setText("Title");
        this.etTaskDescription.setText("Description");
    }

    @Override
    public void setId(String id) {
        this.id=id;
    }

    @Override
    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
