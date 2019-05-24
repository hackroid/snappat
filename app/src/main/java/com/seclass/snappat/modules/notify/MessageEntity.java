package com.seclass.snappat.modules.notify;

public class MessageEntity {
  public Integer msgType;
  public String msgComment;
  public String msgSender;
  public Integer msgRead;

  public MessageEntity() {}

  public MessageEntity(Integer type, Integer read, String comment, String sender) {
    this.msgType = type;
    this.msgRead = read;
    this.msgComment = comment;
    this.msgSender = sender;
  }

  public Integer getMsgType() {
    return msgType;
  }

  public void setMsgType(Integer msgType) {
    this.msgType = msgType;
  }

  public String getMsgComment() {
    return msgComment;
  }

  public void setMsgComment(String msgComment) {
    this.msgComment = msgComment;
  }

  public String getMsgSender() {
    return msgSender;
  }

  public void setMsgSender(String msgSender) {
    this.msgSender = msgSender;
  }

  public Integer getMsgRead() {
    return msgRead;
  }

  public void setMsgRead(Integer msgRead) {
    this.msgRead = msgRead;
  }
}
