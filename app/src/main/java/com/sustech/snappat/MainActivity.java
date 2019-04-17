package com.sustech.snappat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sustech.snappat.CameraActivity.CameraActivity;
import com.sustech.snappat.CameraActivity.DetectorActivity;
import com.sustech.snappat.LoginActivity.LoginActivity;

public class MainActivity extends AppCompatActivity {

  private TextView mTextMessage;

  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
    = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.navigation_home:
          mTextMessage.setText(R.string.title_home);
          return true;
        case R.id.navigation_dashboard:
          mTextMessage.setText(R.string.title_dashboard);
          return true;
        case R.id.navigation_notifications:
          mTextMessage.setText(R.string.title_notifications);
          return true;
      }
      return false;
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mTextMessage = (TextView) findViewById(R.id.message);
    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        Button used for test
    ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.container);
    final Context context = this;
    Button camera_button = new Button(getBaseContext());
    camera_button.setText("CameraButton");
    camera_button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        SharedPreferences sharedPreferences = getBaseContext()
          .getSharedPreferences(getString(R.string.app_preference), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(getString(R.string.pref_login), false);
        editor.commit();
        Intent camera_intent = new Intent(context, DetectorActivity.class);
        startActivity(camera_intent);
      }
    });
    constraintLayout.addView(camera_button);

    SharedPreferences sharedPreferences = getBaseContext()
      .getSharedPreferences(getString(R.string.app_preference), Context.MODE_PRIVATE);
    boolean islogin = sharedPreferences.getBoolean(getString(R.string.pref_login), false);
    if (!islogin) {
      Intent intent = new Intent(this, LoginActivity.class);
      startActivity(intent);
    } else {
//            TODO: load main screen content
    }
  }

}
