package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class assignment extends Fragment {

    Context mContext;
    List<taskitem> mtaskitemList;
    RecyclerView rv_assignment;
    RecyclerViewAdapter mRecyclerViewAdapter;
    FragmentManager fragmentManager;
    View rootView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity().getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_assignment, container, false);

        rv_assignment = rootView.findViewById(R.id.rv_assignment);
        mtaskitemList = new ArrayList<>();

        fragmentManager = getActivity().getSupportFragmentManager();

        Cursor c = MainActivity.DB.read_assignment();
        if(c.getCount() != 0) {
            while(c.moveToNext()){
                mtaskitemList.add(new taskitem(c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4)));
            }
        }

        mRecyclerViewAdapter = new RecyclerViewAdapter(getContext(),mtaskitemList);
        rv_assignment.setLayoutManager(new GridLayoutManager(getContext(), 1));
        rv_assignment.setAdapter(mRecyclerViewAdapter);

        return rootView;
    }
}