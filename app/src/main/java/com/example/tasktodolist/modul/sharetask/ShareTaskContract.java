package com.example.tasktodolist.modul.sharetask;

import com.example.tasktodolist.base.BasePresenter;
import com.example.tasktodolist.base.BaseView;

public interface ShareTaskContract {
    interface View extends BaseView<ShareTaskContract.Presenter> {
        void redirectToTaskList();
        void setTaskTitle(String title);
        void setDescription(String description);
    }

    interface Presenter extends BasePresenter {
        void saveDataTask(String task_title, String task_description, String email);
    }
}
