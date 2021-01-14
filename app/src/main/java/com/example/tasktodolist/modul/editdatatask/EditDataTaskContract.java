package com.example.tasktodolist.modul.editdatatask;

import com.example.tasktodolist.base.BasePresenter;
import com.example.tasktodolist.base.BaseView;
import com.example.tasktodolist.data.model.Task;

public interface EditDataTaskContract {
    interface View extends BaseView<Presenter> {
        void redirectToTaskList();
        void showData(Task task);
        void setId(String id);
    }

    interface Presenter extends BasePresenter {
        void saveData(String title, String description);
        void loadData(String id);
    }
}
