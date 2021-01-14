package com.example.tasktodolist.modul.edittask;

import com.example.tasktodolist.base.BasePresenter;
import com.example.tasktodolist.base.BaseView;
import com.example.tasktodolist.data.model.Task;

import java.util.List;

public interface EditTaskContract {
    interface View extends BaseView<Presenter> {
        void redirectToTaskList();
        void showData(List<Task> task);
        void setId(String id);
        void setTaskTitle(String title);
        void setDescription(String description);
    }

    interface Presenter extends BasePresenter {
        void saveData(String id, String title, String description);
        void loadData(String id);
    }
}
