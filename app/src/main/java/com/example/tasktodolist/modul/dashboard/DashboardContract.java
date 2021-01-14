package com.example.tasktodolist.modul.dashboard;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tasktodolist.base.BasePresenter;
import com.example.tasktodolist.base.BaseView;
import com.example.tasktodolist.data.model.Task;

import java.util.ArrayList;

public interface DashboardContract {
    interface View extends BaseView<Presenter> {
        void gotoNewTask();
        void setEmail(String email);
    }

    interface Presenter extends BasePresenter {
        ArrayList<Task> getDataSet();
    }
}
