package com.createUrlShoertner;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectAclRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Main implements RequestHandler<Map<String, Object>, Map<String, String>> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  private final S3Client s3Client = S3Client.builder().build();

  @Override
  public Map<String, String> handleRequest(Map<String, Object> input, Context context) {
    String body = input.get("body").toString();
    Map<String, String> bodyMap;
    Map<String, String> response = new HashMap<>();

    bodyMap = descerializerBody(body);

    UrlData urlData = buildUrlData(bodyMap);

    String shortUrlCode = UUID.randomUUID().toString().substring(0,8);

    try {
      String urlDataJson = objectMapper.writeValueAsString(urlData);

      PutObjectRequest request = PutObjectRequest.builder()
              .bucket("url-shortener-storage-22022000")
              .key(shortUrlCode + ".json")
              .build();

      s3Client.putObject(request, RequestBody.fromString(urlDataJson));

    } catch (Exception exception) {
      throw new RuntimeException("Error saving data to S3: " + exception.getMessage(), exception);
    }

    response.put("code", shortUrlCode);

    return response;
  }

  private UrlData buildUrlData(Map<String, String> bodyMap){
    String originalUrl = bodyMap.get("originalUrl");
    String expirationTime = bodyMap.get("expirationTime");
    Long expirationTimeInSeconds = Long.parseLong(expirationTime);

    return new UrlData(originalUrl, expirationTimeInSeconds);
  }

  private Map<String, String> descerializerBody(String body) {
    Map<String, String> bodyMap;
    try {
      bodyMap = objectMapper.readValue(body, Map.class);
    } catch (JsonProcessingException exception) {
      throw new RuntimeException("Error parsin JSON body: " + exception.getMessage(), exception);
    }
    return bodyMap;
  }


}