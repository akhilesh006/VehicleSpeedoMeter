package com.app.eularmotor.speedometer.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.eularmotor.R;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private TextView vehicleSpeed;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        vehicleSpeed = view.findViewById(R.id.vehicle_speed);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.getCurrentSpeed().observe(this,speed ->{
            //Display on UI
            vehicleSpeed.setText(String.valueOf(speed.getSpeed()));
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        mViewModel.getCurrentSpeed().removeObservers(this);
    }
}