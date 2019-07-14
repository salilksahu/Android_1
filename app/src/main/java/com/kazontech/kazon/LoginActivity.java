package com.kazontech.kazon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

public class LoginActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static String androidId = "";
    ConstraintLayout c_layout_0;
    ConstraintLayout c_layout_1;
    ConstraintLayout c_layout_2;
    ConstraintLayout c_layout_3;
    ConstraintLayout.LayoutParams params0;
    ConstraintLayout.LayoutParams params1;
    ConstraintLayout.LayoutParams params2;
    ConstraintLayout.LayoutParams params3;

    ConstraintSet constraintSet;
    public Integer displayState = 0;
    private static String uniqueID = null;
    private static final String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        androidId = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);

        Intent intent = getIntent();
        if (intent.hasExtra(MainActivity.BACK_MESSAGE)) {
            String message = intent.getStringExtra(MainActivity.BACK_MESSAGE);
            EditText editText = (EditText) findViewById(R.id.email);
            editText.setText(message);
        }

        c_layout_0 = (ConstraintLayout) findViewById(R.id.cont_login0);
        c_layout_1 = (ConstraintLayout) findViewById(R.id.cont_login1);
        c_layout_2 = (ConstraintLayout) findViewById(R.id.cont_login2);
        c_layout_3 = (ConstraintLayout) findViewById(R.id.cont_login3);
        params0 = (ConstraintLayout.LayoutParams) c_layout_0.getLayoutParams();
        params1 = (ConstraintLayout.LayoutParams) c_layout_1.getLayoutParams();
        params2 = (ConstraintLayout.LayoutParams) c_layout_2.getLayoutParams();
        params3 = (ConstraintLayout.LayoutParams) c_layout_3.getLayoutParams();

        Button loginBtn = (Button) findViewById(R.id.login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                AttemptLogin();
                displayState = 0;
            }
        });

        Button doRegisterBtn = (Button) findViewById(R.id.doregister);
        doRegisterBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                StartRegistration();
                displayState = 1;
            }
        });

        Button registerBtn = (Button) findViewById(R.id.register);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                FinalRegistration();
                displayState = 2;
                SendMail();
            }
        });

        Button submitBtn = (Button) findViewById(R.id.submit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                SubmitRegistration();
                displayState = 3;
            }
        });

        DisplayLogin();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //do your stuff
            //finish();
            if (displayState == 0) {
                moveTaskToBack(true);
                return super.onKeyDown(keyCode, event);
            }
            if (displayState == 1) {
                DisplayLogin();
                displayState = 0;
                return false;
            }
            if (displayState == 2) {
                StartRegistration();
                displayState = 1;
                return false;
            }
            if (displayState == 3) {
                DisplayLogin();
                displayState = 2;
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void SendMail()
    {
        EditText editText = (EditText) findViewById(R.id.email);
        String mailId = editText.getText().toString();

        new SendMailTask().execute(androidId,mailId);
    }
    public void AttemptLogin()
    {
        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.email);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void DisplayLogin()
    {
        params1.height = 1;
        c_layout_1.setLayoutParams(params1);
        c_layout_1.setVisibility(View.INVISIBLE);
        params3.height = 1;
        c_layout_3.setLayoutParams(params3);
        c_layout_3.setVisibility(View.INVISIBLE);
        params2.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        c_layout_2.setLayoutParams(params2);
        c_layout_2.setVisibility(View.VISIBLE);

        EditText edtPassword = (EditText)findViewById(R.id.password);
        edtPassword.setHint("Password");
    }

    public void StartRegistration()
    {
        params0.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        c_layout_0.setLayoutParams(params0);
        c_layout_0.setVisibility(View.VISIBLE);
        params1.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        c_layout_1.setLayoutParams(params1);
        c_layout_1.setVisibility(View.VISIBLE);
        params2.height = 1;
        c_layout_2.setLayoutParams(params2);
        c_layout_2.setVisibility(View.INVISIBLE);
        params3.height = 1;
        c_layout_3.setLayoutParams(params3);
        c_layout_3.setVisibility(View.INVISIBLE);

        EditText edtPassword = (EditText)findViewById(R.id.password);
        edtPassword.setHint("Provide a new password");
    }

    public void FinalRegistration()
    {
        params0.height = 1;
        c_layout_0.setLayoutParams(params0);
        c_layout_0.setVisibility(View.INVISIBLE);
        params1.height = 1;
        c_layout_1.setLayoutParams(params1);
        c_layout_1.setVisibility(View.INVISIBLE);
        params2.height = 1;
        c_layout_2.setLayoutParams(params2);
        c_layout_2.setVisibility(View.INVISIBLE);
        params3.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        c_layout_3.setLayoutParams(params3);
        c_layout_3.setVisibility(View.VISIBLE);
    }

    public void SubmitRegistration()
    {
        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.email);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }


}
