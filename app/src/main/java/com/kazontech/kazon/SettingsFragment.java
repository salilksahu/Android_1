package com.kazontech.kazon;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SettingsFragment extends Fragment {
    ConstraintLayout c_layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_settings, container, false);
        Button btnChangePassword = (Button) view.findViewById(R.id.btnChangePassword);
        Button cancelPasswordUpd = (Button) view.findViewById(R.id.cancelPasswordUpd);

        c_layout = (ConstraintLayout) view.findViewById(R.id.cont4);
        c_layout.setVisibility(View.INVISIBLE);

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                ChangePassword();
            }
        });

        cancelPasswordUpd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                CancelChangePassword();
            }
        });

        return view;
    }

    public void ChangePassword()
    {
        c_layout.setVisibility(View.VISIBLE);
    }

    public void CancelChangePassword()
    {
        c_layout.setVisibility(View.INVISIBLE);
    }
}