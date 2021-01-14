package com.example.tasktodolist.modul.newtask;

import android.os.Bundle;
import android.view.View;

import com.example.tasktodolist.base.BaseFragmentHolderActivity;

public class NewTaskActivity extends BaseFragmentHolderActivity {
    NewTaskFragment newTaskFragment;

    @Override
    protected void initializeFragment() {
        initializeView();
        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        ivIcon.setVisibility(View.VISIBLE);
        newTaskFragment = new NewTaskFragment();
        Bundle bundle = getIntent().getExtras();
        newTaskFragment.setEmail(bundle.getString("email"));
        setCurrentFragment(newTaskFragment, false);
    }
}
