package com.sustech.snappat.LoginActivityLogicTest;


import com.sustech.snappat.Data.UserInfo;
import com.sustech.snappat.LoginActivityUITest.LoginActivity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class LoginActivityLogicTest {
    LoginActivity loginactivity;

    @Before
    public void beforeTest(){
        loginactivity = new LoginActivity();
    }

    @Test
    public void getUserInfoTest(){
        UserInfo userinfo = loginactivity.getUserInfo();
        assertEquals(userinfo.username, "12313");
    }
}
