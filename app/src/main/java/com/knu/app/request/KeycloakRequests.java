package com.knu.app.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class KeycloakRequests {
    static public String generateMasterAccessToken() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String accessToken = null;

        try {
            HttpPost httpPost = new HttpPost("http://localhost:9001/realms/master/protocol/openid-connect/token");
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            String formData = "grant_type=client_credentials&" +
                    "client_id=admin-cli&" +
                    "client_secret=lCEudbGrjWgNYXq2OukacBvbtrbs8HfQ";
            HttpEntity entity = new StringEntity(formData);
            httpPost.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                // Handle the response
                if (response.getCode() == 200) {
                    // If the response status code is 200 (OK), you can print the response content.
                    HttpEntity responseEntity = response.getEntity();

                    if (responseEntity != null) {
                        String responseContent = EntityUtils.toString(responseEntity);

//                        System.out.println("Response Content:");
//                        System.out.println(responseContent);

                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(responseContent);
                        accessToken = jsonNode.get("access_token").asText();
                    }

                } else {
                    // Handle other status codes if needed
                    System.err.println("HTTP Request failed with status code: " + response.getCode());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return accessToken;
    }

    static public Boolean register(
            String token,
            String firstName,
            String lastName,
            String username,
            String email,
            String password
    ) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        Boolean success = Boolean.FALSE;

        try {
            HttpPost httpPost = new HttpPost("http://localhost:9001/admin/realms/iot/users");
            httpPost.setHeader("Authorization", "Bearer " + token);
            httpPost.setHeader("Content-Type", "application/json");

            String jsonData =
                    "{" +
                        "\"enabled\": true, " +
                        "\"username\": \""    + username + "\", " +
                        "\"email\": \""       + email + "\", " +
                        "\"firstName\": \""   + firstName + "\", " +
                        "\"lastName\": \""    + lastName + "\", " +
                        "\"credentials\": [{" +
                            "\"type\": \"password\", " +
                            "\"value\": \""   + password + "\", " +
                            "\"temporary\": false" +
                        "}]" +
                    "}";

            HttpEntity entity = new StringEntity(jsonData);
            httpPost.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                // Handle the response
                if (response.getCode() == 201) {
                    success = Boolean.TRUE;
                } else {
                    // Handle other status codes if needed
                    System.err.println("HTTP Request failed with status code: " + response.getCode());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return success;
    }

    static public List<String> login(
            String username,
            String password
    ) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        List<String> tokens = null;

        try {
            HttpPost httpPost = new HttpPost("http://localhost:9001/realms/iot/protocol/openid-connect/token");
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            String formData = "grant_type=password" +
                    "&client_id=iot-rest-api" +
                    "&client_secret=p5G4R7VbZcAD3ggL0QkSLcpsDcyLsXfR" +
                    "&username=" + username +
                    "&password=" + password;
            HttpEntity entity = new StringEntity(formData);
            httpPost.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                // Handle the response
                if (response.getCode() == 200) {
                    // If the response status code is 200 (OK), you can print the response content.
                    HttpEntity responseEntity = response.getEntity();

                    if (responseEntity != null) {
                        String responseContent = EntityUtils.toString(responseEntity);

                        System.out.println("Response Content:");
                        System.out.println(responseContent);

                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(responseContent);
                        String accessToken = jsonNode.get("access_token").asText();
                        tokens = new ArrayList<>();
                        tokens.add(accessToken);
//                        System.out.println("Access Token:");
//                        System.out.println(accessToken);
                        String refreshToken = jsonNode.get("refresh_token").asText();
//                        System.out.println("Refresh Token:");
//                        System.out.println(refreshToken);
                        tokens.add(refreshToken);
                    }

                } else {
                    // Handle other status codes if needed
                    System.err.println("HTTP Request failed with status code: " + response.getCode());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return tokens;
    }

    static public String getEmail(String token) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String email = null;

        try {
            HttpPost httpPost = new HttpPost("http://localhost:9001/realms/iot/protocol/openid-connect/token/introspect");
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            String formData = "grant_type=password" +
                    "&client_id=iot-rest-api" +
                    "&client_secret=p5G4R7VbZcAD3ggL0QkSLcpsDcyLsXfR" +
                    "&token=" + token;
            HttpEntity entity = new StringEntity(formData);
            httpPost.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                // Handle the response
                if (response.getCode() == 200) {
                    // If the response status code is 200 (OK), you can print the response content.
                    HttpEntity responseEntity = response.getEntity();

                    if (responseEntity != null) {
                        String responseContent = EntityUtils.toString(responseEntity);

                        System.out.println("Response Content:");
                        System.out.println(responseContent);

                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(responseContent);
                        Boolean active = jsonNode.get("active").asBoolean();

                        if (active) {
                            email = jsonNode.get("email").asText();
                        }
                    }

                } else {
                    // Handle other status codes if needed
                    System.err.println("HTTP Request failed with status code: " + response.getCode());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return email;
    }
}
