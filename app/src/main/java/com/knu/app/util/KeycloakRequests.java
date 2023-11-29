package com.knu.app.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knu.app.dto.ClientAuthTokenDto;
import com.knu.app.dto.ClientLoginDto;
import com.knu.app.dto.ClientRegisterDto;
import com.knu.app.dto.ErrorDto;
import lombok.Getter;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
public class KeycloakRequests {
    @Getter
    static private String adminClientSecret;

    @Getter
    static private String iotClientSecret;

    @Getter
    static private String url;

    @Autowired
    private void setClientSecrets(
            @Value("${keycloak.admin.client.secret}") String newAdminClientSecret,
            @Value("${keycloak.iot.client.secret}") String newIotClientSecret,
            @Value("${keycloak.url}") String newUrl
    ){
        adminClientSecret = newAdminClientSecret;
        iotClientSecret = newIotClientSecret;
        url = newUrl;
    }

    static public String generateMasterAccessToken() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String accessToken = null;

        try {
            HttpPost httpPost = new HttpPost(url + "/realms/master/protocol/openid-connect/token");
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            String formData = "grant_type=client_credentials" +
                    "&client_id=admin-cli" +
                    "&client_secret=" + adminClientSecret;
            HttpEntity entity = new StringEntity(formData);
            httpPost.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                if (response.getCode() == 200) {
                    HttpEntity responseHttpEntity = response.getEntity();

                    if (responseHttpEntity != null) {
                        String responseContent = EntityUtils.toString(responseHttpEntity);
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(responseContent);
                        accessToken = jsonNode.get("access_token").asText();
                    }

                } else {
                    System.err.println("[ERROR]" + response.getCode());
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

    static public ResponseEntity<?> register(
            String masterAccessToken,
            ClientRegisterDto clientRegisterDto
    ) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpPost httpPost = new HttpPost(url + "/admin/realms/iot/users");
            httpPost.setHeader("Authorization", "Bearer " + masterAccessToken);
            httpPost.setHeader("Content-Type", "application/json");

            String jsonData =
                    "{" +
                        "\"enabled\": true, " +
                        "\"username\": \""    + clientRegisterDto.username() + "\", " +
                        "\"email\": \""       + clientRegisterDto.email() + "\", " +
                        "\"firstName\": \""   + clientRegisterDto.firstName() + "\", " +
                        "\"lastName\": \""    + clientRegisterDto.lastName() + "\", " +
                        "\"credentials\": [{" +
                            "\"type\": \"password\", " +
                            "\"value\": \""   + clientRegisterDto.password() + "\", " +
                            "\"temporary\": false" +
                        "}]" +
                    "}";

            HttpEntity entity = new StringEntity(jsonData);
            httpPost.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                if (response.getCode() == 201) {
                    return new ResponseEntity<>(
                            Mono.empty(),
                            HttpStatusCode.valueOf(200)
                    );
                } else if (response.getCode() == 409) {
                    HttpEntity responseHttpEntity = response.getEntity();

                    if (responseHttpEntity != null) {
                        String responseContent = EntityUtils.toString(responseHttpEntity);
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(responseContent);

                        String errorMessage = jsonNode.get("errorMessage").asText();

                        return new ResponseEntity<>(
                                Mono.just(new ErrorDto(Collections.singletonList(errorMessage))),
                                HttpStatusCode.valueOf(400)
                        );
                    }
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

        return new ResponseEntity<>(
                Mono.just(new ErrorDto(Collections.singletonList("Internal server error"))),
                HttpStatusCode.valueOf(500)
        );
    }


    static public ResponseEntity<?> login(
            ClientLoginDto clientLoginDto
    ) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpPost httpPost = new HttpPost(url + "/realms/iot/protocol/openid-connect/token");
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

            String formData = "grant_type=password" +
                    "&client_id=iot-rest-api" +
                    "&client_secret=" + iotClientSecret +
                    "&username=" + clientLoginDto.username() +
                    "&password=" + clientLoginDto.password();

            HttpEntity entity = new StringEntity(formData);
            httpPost.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                if (response.getCode() == 200) {
                    HttpEntity responseEntity = response.getEntity();

                    if (responseEntity != null) {
                        String responseContent = EntityUtils.toString(responseEntity);
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(responseContent);

                        String accessToken = jsonNode.get("access_token").asText();
                        String refreshToken = jsonNode.get("refresh_token").asText();

                        return new ResponseEntity<>(
                                new ClientAuthTokenDto(accessToken, refreshToken),
                                HttpStatusCode.valueOf(200)
                        );
                    }
                } else if (response.getCode() == 401) {
                    HttpEntity responseEntity = response.getEntity();

                    if (responseEntity != null) {
                        String responseContent = EntityUtils.toString(responseEntity);
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(responseContent);

                        String errorDescription = jsonNode.get("error_description").asText();

                        return new ResponseEntity<>(
                                Mono.just(new ErrorDto(Collections.singletonList(errorDescription))),
                                HttpStatusCode.valueOf(400)
                        );
                    }
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

        return new ResponseEntity<>(
                Mono.just(new ErrorDto(Collections.singletonList("Internal server error"))),
                HttpStatusCode.valueOf(500)
        );
    }

    static public ResponseEntity<?> logout(String refreshToken) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpPost httpPost = new HttpPost(url + "/realms/iot/protocol/openid-connect/logout");
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

            String formData = "grant_type=password&" +
                    "client_id=iot-rest-api&" +
                    "client_secret=" + iotClientSecret + "&" +
                    "refresh_token=" + refreshToken;

            HttpEntity entity = new StringEntity(formData);
            httpPost.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                if (response.getCode() == 204) {
                    return new ResponseEntity<>(Mono.empty(), HttpStatusCode.valueOf(200));
                } else if (response.getCode() == 400) {
                    HttpEntity responseEntity = response.getEntity();

                    if (responseEntity != null) {
                        String responseContent = EntityUtils.toString(responseEntity);
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(responseContent);

                        String errorDescription = jsonNode.get("error_description").asText();

                        return new ResponseEntity<>(
                                Mono.just(new ErrorDto(Collections.singletonList(errorDescription))),
                                HttpStatusCode.valueOf(400)
                        );
                    }
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

        return new ResponseEntity<>(
                Mono.just(new ErrorDto(Collections.singletonList("Internal server error"))),
                HttpStatusCode.valueOf(500)
        );
    }

    static public String getEmail(String accessToken) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String email = null;

        try {
            HttpPost httpPost = new HttpPost(url + "/realms/iot/protocol/openid-connect/token/introspect");
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

            String formData = "grant_type=password" +
                    "&client_id=iot-rest-api" +
                    "&client_secret=" + iotClientSecret +
                    "&token=" + accessToken;

            HttpEntity entity = new StringEntity(formData);
            httpPost.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                if (response.getCode() == 200) {
                    HttpEntity responseEntity = response.getEntity();

                    if (responseEntity != null) {
                        String responseContent = EntityUtils.toString(responseEntity);
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(responseContent);
                        Boolean active = jsonNode.get("active").asBoolean();

                        if (active) {
                            email = jsonNode.get("email").asText();
                        }

                        System.out.println(email);
                    }

                } else {
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
