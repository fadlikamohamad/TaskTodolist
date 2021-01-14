package com.example.tasktodolist.data.source.remote.response;

import com.example.tasktodolist.data.model.Task;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataResponse {
    private Boolean status;
    private Boolean message;

    @SerializedName("data")
    List<Task> allTask;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getMessage() {
        return message;
    }

    public void setMessage(Boolean message) {
        this.message = message;
    }

    public List<Task> getAllTask() {
        return allTask;
    }

    public void setAllTask(List<Task> allTask) {
        this.allTask = allTask;
    }
}
