package com.example.demo.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.dto.DataResult;
import com.example.demo.dto.DataResultCode;

@Service
public class DataServiceImpl implements DataService {

  private static final Logger log = LoggerFactory.getLogger(DataServiceImpl.class);

  @Value("${app.source.url}")
  private String sourceUrl;

  @Autowired private WebClient webClient;

  public DataResult getData(String imageNo) {
    try {
      var httpHeaders = new HashMap<String, String>();
      var responseStr =
          webClient
              .get()
              .uri(sourceUrl + (StringUtils.hasText(imageNo) ? "/" + imageNo : ""))
              .headers(headers -> headers.setAll(httpHeaders))
              .retrieve()
              .bodyToMono(String.class)
              .block();

      if (StringUtils.hasText(responseStr)) {
        var result = new DataResult();
        result.setCode(DataResultCode.OK);
        result.setData(responseStr);
        return result;
      }
    } catch (Exception e) {
      log.error("Failed...", e);
      var result = new DataResult();
      result.setCode(DataResultCode.ERROR);
      result.setMessage(e.getMessage());
      return result;
    }
    return null;
  }
}
