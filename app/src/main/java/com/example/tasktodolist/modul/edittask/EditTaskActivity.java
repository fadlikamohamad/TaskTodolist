package com.example.tasktodolist.modul.edittask;

import android.os.Bundle;
import android.view.View;

import com.example.tasktodolist.base.BaseFragmentHolderActivity;

public class EditTaskActivity extends BaseFragmentHolderActivity {
    EditTaskFragment editTaskFragment;

    @Override
    protected void initializeFragment() {
        initializeView();
        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        ivIcon.setVisibility(View.VISIBLE);
        editTaskFragment = new EditTaskFragment();
        //String id = getIntent().getExtras().getString("TaskId");
        //String id = "6";
        //editTaskFragment.setId(id);
        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("taskId");
        String title = bundle.getString("taskTitle");
        String description = bundle.getString("taskDescription");
        editTaskFragment.setId(id);
        editTaskFragment.setTaskTitle(title);
        editTaskFragment.setDescription(description);
        setCurrentFragment(editTaskFragment, false);
    }
}
