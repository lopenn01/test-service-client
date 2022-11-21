package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class DataResult {

  private static final long serialVersionUID = 3646146050989884771L;

  @JsonUnwrapped private DataResultCode code;

  private String message;

  private String data;

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public DataResultCode getCode() {
    return code;
  }

  public void setCode(DataResultCode code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }
}
