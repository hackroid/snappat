package com.sustech.snappat.data;
/**
 * User information.
 *
 * <p>User information, include id, username, phone number</p>
 *
 * @since 1.0
 * */
public class UserInfo {

  public Long id;
  public String username;
  public int phonenumber;

  /**
   * Get user information.
   *
   * <p>Set user information with id, user name, phone number </p>
   * @since 1.0
   * */
  public UserInfo(Long id, String username, int phonenumber) {
    this.id = id;
    this.username = username;
    this.phonenumber = phonenumber;
  }
}
