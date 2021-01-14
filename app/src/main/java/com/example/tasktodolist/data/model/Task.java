package com.example.tasktodolist.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Task {
    @SerializedName("task_id")
    @Expose
    private String task_id;

    @SerializedName("task_title")
    @Expose
    private String task_title;

    @SerializedName("task_description")
    @Expose
    private String task_description;

    @SerializedName("is_checked")
    @Expose
    private String is_checked;

    @SerializedName("email")
    @Expose
    private String email;

    public Task() {}

//    public Task(String task_id, String task_title, String task_description, String user_id) {
//        this.task_id = task_id;
//        this.task_title = task_title;
//        this.task_description = task_description;
//        this.user_id = user_id;
//    }

    public Task(String task_title, String task_description, String email) {
        this.task_title = task_title;
        this.task_description = task_description;
        this.email = email;
    }

    public Task(String task_title, String task_description, String is_checked, String email) {
        this.task_title = task_title;
        this.task_description = task_description;
        this.is_checked = is_checked;
        this.email = email;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getTask_title() {
        return task_title;
    }

    public void setTask_title(String task_title) {
        this.task_title = task_title;
    }

    public String getTask_description() {
        return task_description;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIs_checked() {
        return is_checked;
    }

    public void setIs_checked(String is_checked) {
        this.is_checked = is_checked;
    }
}
