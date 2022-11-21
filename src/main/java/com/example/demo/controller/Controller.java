package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DataResult;
import com.example.demo.service.DataService;

@RestController
public class Controller {

  private static final Logger log = LoggerFactory.getLogger(Controller.class);

  private final DataService dataService;

  public Controller(DataService dataService) {
    this.dataService = dataService;
  }

  @GetMapping(value = {"/data", "/data/{imageNo}"})
  public DataResult getData(
      @PathVariable(value = "imageNo", required = false) String imageNo,
      HttpServletRequest httpServletRequest) {
    return dataService.getData(imageNo);
  }

  @GetMapping(value = {"/gc"})
  public void gc() {
    System.gc();
  }
}
