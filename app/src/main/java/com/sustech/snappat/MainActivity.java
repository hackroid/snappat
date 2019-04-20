package com.sustech.snappat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.sustech.snappat.cameraactivity.DetectorActivity;
import com.sustech.snappat.loginactivity.LoginActivity;

public class MainActivity extends AppCompatActivity {

  private transient TextView textMessage;

  private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
      = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
          switch (item.getItemId()) {
            case R.id.navigation_home:
              textMessage.setText(R.string.title_home);
              return true;
            case R.id.navigation_dashboard:
              textMessage.setText(R.string.title_dashboard);
              return true;
            case R.id.navigation_notifications:
              textMessage.setText(R.string.title_notifications);
              return true;
            default:
              return false;
          }
        }
      };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    textMessage = (TextView) findViewById(R.id.message);
    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

    //        Button used for test
    ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.container);
    final Context context = this;
    Button cameraButton = new Button(getBaseContext());
    cameraButton.setText("CameraButton");
    cameraButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        SharedPreferences sharedPreferences = getBaseContext()
            .getSharedPreferences(getString(R.string.app_preference), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(getString(R.string.pref_login), false);
        editor.commit();
        Intent cameraIntent = new Intent(context, DetectorActivity.class);
        startActivity(cameraIntent);
      }
    });
    constraintLayout.addView(cameraButton);

    SharedPreferences sharedPreferences = getBaseContext()
        .getSharedPreferences(getString(R.string.app_preference), Context.MODE_PRIVATE);
    boolean islogin = sharedPreferences.getBoolean(getString(R.string.pref_login), false);
    if (!islogin) {
      Intent intent = new Intent(this, LoginActivity.class);
      startActivity(intent);
    } else {
      //  TODO: load main screen content
    }
  }
}
