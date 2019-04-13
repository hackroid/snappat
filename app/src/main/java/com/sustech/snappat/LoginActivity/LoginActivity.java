package com.sustech.snappat.LoginActivity;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sustech.snappat.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button = (Button) findViewById(R.id.login_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences(getString(R.string.app_preference), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(getString(R.string.pref_login),true);
                editor.commit();
                Toast.makeText(LoginActivity.this,
                        "Login Sucessfully", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
