package com.example.tasktodolist.modul.sharetask;

import android.os.Bundle;
import android.view.View;

import com.example.tasktodolist.base.BaseFragmentHolderActivity;
import com.example.tasktodolist.modul.newtask.NewTaskFragment;

public class ShareTaskActivity extends BaseFragmentHolderActivity {
    ShareTaskFragment shareTaskFragment;

    @Override
    protected void initializeFragment() {
        initializeView();
        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        ivIcon.setVisibility(View.VISIBLE);
        shareTaskFragment = new ShareTaskFragment();
        Bundle bundle = getIntent().getExtras();
        shareTaskFragment.setTaskTitle(bundle.getString("taskTitle"));
        shareTaskFragment.setDescription(bundle.getString("taskDescription"));
        setCurrentFragment(shareTaskFragment, false);
    }
}
