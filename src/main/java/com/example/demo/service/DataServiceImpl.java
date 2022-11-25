package com.example.demo.service;

import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.DataResult;
import com.example.demo.dto.DataResultCode;

@Service
public class DataServiceImpl implements DataService {

  private static final Logger log = LoggerFactory.getLogger(DataServiceImpl.class);

  @Value("${app.source.url}")
  private String sourceUrl;

  public DataResult getData(String imageNo) {
    try {
      var content = "{}";
      HttpHeaders headers = new HttpHeaders();
      headers.setContentLength(content.getBytes(StandardCharsets.UTF_8).length);
      var rf = new SimpleClientHttpRequestFactory();
      rf.setBufferRequestBody(false);
      ResponseEntity<String> httpResponse =
          new RestTemplate(rf)
              .getForEntity(
                  sourceUrl + (StringUtils.hasText(imageNo) ? "/" + imageNo : ""), String.class);
      var responseStr = httpResponse.getBody();

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
