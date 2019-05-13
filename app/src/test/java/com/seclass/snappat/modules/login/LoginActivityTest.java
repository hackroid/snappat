package com.seclass.snappat.modules.login;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LoginActivityTest {

  LoginActivity loginactivity;

  @Before
  public void beforeTest() {
    loginactivity = new LoginActivity();
  }

  @Test
  public void getUserInfoTest() {
    UserInfo userinfo = loginactivity.getUserInfo();
    assertEquals(userinfo.username, "12313");
  }
}