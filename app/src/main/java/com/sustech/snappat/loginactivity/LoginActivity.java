package com.sustech.snappat.loginactivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.sustech.snappat.R;
import com.sustech.snappat.data.UserInfo;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

  //    Secret 82ae7dc9a211c5b2a536215b
  //    AppKey 3263ee4b636e523d84313a8a
  private EditText editTextPhoneNumber;
  private EditText verifyCode;
  private Button sendCode;
  private SharedPreferences sharedPreferences;

  private Boolean isSend = false;
  private int counter = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    editTextPhoneNumber = (EditText) findViewById(R.id.phonenumber);
    verifyCode = (EditText) findViewById(R.id.verifycode);
    Button sendCode = (Button) findViewById(R.id.sendcode_button);
    Button button = (Button) findViewById(R.id.login_button);
    sendCode.setOnClickListener(this);
    button.setOnClickListener(this);
    sharedPreferences = getBaseContext()
        .getSharedPreferences(getString(R.string.app_preference), Context.MODE_PRIVATE);

    // used for debug
    editTextPhoneNumber.setText("13028871392");
    verifyCode.setText("123456");

  }

  public void log(String info) {
    Toast.makeText(LoginActivity.this,
        info, Toast.LENGTH_LONG).show();
  }

  public UserInfo getUserInfo() {
    return new UserInfo(123L, "12313", 12313222);
  }

  private boolean checkVerifyCode() {
    return true;
  }

  private void sendVerifyCode() {

  }

  private void startCounter() {

  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.login_button:
        // Check input
        if (isSend || verifyCode.getText().toString().length() != 6) {
          log("Please wait for verify code and Input right code");
          return;
        }
        boolean isPassed = checkVerifyCode();
        if (!isPassed) {
          log("Wrong Code");
          return;
        }
        UserInfo userInfo = getUserInfo();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(getString(R.string.user_id), userInfo.id);
        editor.putInt(getString(R.string.user_phone), userInfo.phonenumber);
        editor.putString(getString(R.string.user_name), userInfo.username);
        editor.putBoolean(getString(R.string.pref_login), true);
        editor.apply();
        Toast.makeText(LoginActivity.this,
            "Login Sucessfully", Toast.LENGTH_LONG).show();
        finish();
        break;
      case R.id.sendcode_button:
        if (editTextPhoneNumber.getText().toString().length() != 13) {
          log("Wrong Phone Number!");
          return;
        }
        sendVerifyCode();
        startCounter();
        break;
      default:
        break;
    }
  }
}