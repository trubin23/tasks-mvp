package com.example.trubin23.tasks_mvp.addedittask;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.trubin23.tasks_mvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Andrey on 04.02.2018.
 */

public class AddEditTaskFragment extends Fragment implements AddEditTaskContract.View {

    AddEditTaskContract.Presenter mPresenter;

    @BindView(R.id.add_task_title)
    EditText mTitle;

    @BindView(R.id.add_task_description)
    EditText mDescription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.addtask_frag, container, false);
        ButterKnife.bind(this, root);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab_edit_task_done);
        fab.setOnClickListener(v -> mPresenter.saveTask(
                mTitle.getText().toString(), mDescription.getText().toString()));

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull AddEditTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setTitle(@NonNull String title) {
        mTitle.setText(title);
    }

    @Override
    public void setDescription(@NonNull String description) {
        mDescription.setText(description);
    }

    @Override
    public void showEmptyTaskError() {
        Snackbar.make(mTitle, R.string.empty_task_message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showTasksList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }
}
