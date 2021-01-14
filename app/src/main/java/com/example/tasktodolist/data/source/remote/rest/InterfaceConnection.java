package com.example.tasktodolist.data.source.remote.rest;

import com.example.tasktodolist.data.model.Task;
import com.example.tasktodolist.data.source.remote.response.DataResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface InterfaceConnection {
    @GET("tasks")
    Call<DataResponse> getTask();

    @GET("tasks/{email}")
    Call<DataResponse> getTaskByUser(@Path("email") String email);

    @GET("tasks/checked/{email}")
    Call<DataResponse> getCheckedTask(@Path("email") String email);

    @FormUrlEncoded
    @POST("tasks/insert/{email}")
    Call<DataResponse> insertTask(
            @Field("task_title") String task_title,
            @Field("task_description") String task_description,
            @Path("email") String email
    );

    @DELETE("tasks/delete/{task_id}")
    Call<DataResponse> deleteTask(@Path("task_id") String task_id);

    @GET("tasks/detail/{task_id}")
    Call<DataResponse> getTaskDetail(@Path("task_id") String task_id);

    @FormUrlEncoded
    @PUT("tasks/update/{task_id}")
    Call<DataResponse> updateTask(@Field("task_title") String task_title,
                                  @Field("task_description") String task_description,
                                  @Path("task_id") String task_id
    );

    @FormUrlEncoded
    @PUT("tasks/update/checked/{task_id}")
    Call<DataResponse> updateCheckedTask(@Path("task_id") String task_id);
}
