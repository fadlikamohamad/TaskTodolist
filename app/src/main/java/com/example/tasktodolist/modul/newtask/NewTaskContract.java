package com.example.tasktodolist.modul.newtask;

import com.example.tasktodolist.base.BasePresenter;
import com.example.tasktodolist.base.BaseView;

public interface NewTaskContract {
    interface View extends BaseView<Presenter> {
        void redirectToTaskList();
        void setEmail(String email);
    }

    interface Presenter extends BasePresenter {
        void saveDataTask(String task_title, String task_description, String email);
    }
}
