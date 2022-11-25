package com.example.demo.controller;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  private static final Logger log = LoggerFactory.getLogger(Controller.class);
  private static final Random RANDOM = new Random();

  private int lastOrder = -1;
  private String data;

  @GetMapping(value = {"/get-data"})
  public String getImage(@PathVariable(value = "imageNo", required = false) String imageNo) {
    if (data == null) {
      var sb = new StringBuilder();
      for (int i = 0; i < 3 * 1024 * 1024; i++) {
        sb.append('a' + RANDOM.nextInt(10));
      }
      data = sb.toString();
    }
    var num = ++lastOrder % 4;
    if (num == 0) {
      return data;
    }
    var sb = new StringBuilder();
    for (int i = 0; i <= num; i++) {
      sb.append(data);
    }
    return sb.toString();
  }

  @GetMapping(value = {"/gc"})
  public void gc() {
    System.gc();
  }
}
