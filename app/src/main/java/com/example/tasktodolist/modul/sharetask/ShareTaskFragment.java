package com.example.tasktodolist.modul.sharetask;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.tasktodolist.R;
import com.example.tasktodolist.base.BaseFragment;
import com.example.tasktodolist.modul.dashboard.DashboardActivity;
import com.example.tasktodolist.modul.newtask.NewTaskContract;
import com.google.android.material.textfield.TextInputEditText;

public class ShareTaskFragment extends BaseFragment<ShareTaskActivity, ShareTaskContract.Presenter> implements ShareTaskContract.View {

    TextInputEditText etReceivedEmail;
    Button btnShare;
    String title;
    String description;

    public ShareTaskFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_share_task, container, false);
        mPresenter = new ShareTaskPresenter(this);
        mPresenter.start();
        etReceivedEmail = fragmentView.findViewById(R.id.inputReceivedEmail);
        btnShare = fragmentView.findViewById(R.id.btnShareTask);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtShareClick();
            }
        });
        setTitle("Share Task");
        return fragmentView;
    }

    public void setBtShareClick(){
        String email = etReceivedEmail.getText().toString();
        mPresenter.saveDataTask(title,description,email);
    }

    @Override
    public void setPresenter(ShareTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToTaskList() {
        Intent intent = new Intent(activity, DashboardActivity.class);
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void setTaskTitle(String title) {
        this.title = title;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
