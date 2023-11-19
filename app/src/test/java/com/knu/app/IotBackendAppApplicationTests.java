package com.knu.app;

import com.knu.app.controller.dto.ClientAuthTokenDto;
import com.knu.app.controller.dto.ClientLoginDto;
import com.knu.app.controller.dto.ClientRegisterDto;
import com.knu.app.util.KeycloakRequests;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IotBackendAppApplicationTests {

    @Test
	void testKeycloakIsAccessibleMasterAccessToken() {
        assertNotNull(KeycloakRequests.generateMasterAccessToken());
	}

    @Test
    void testKeycloakRegisterClient() {
        String masterAccessToken = KeycloakRequests.generateMasterAccessToken();

        assertNotNull(masterAccessToken);

        String firstName = "Test";
        String lastName = "Test";
        String username = "test";
        String email = "test@gmail.com";
        String password = "TestTest1!";

        var registerResponseEntity= KeycloakRequests.register(
                masterAccessToken,
                new ClientRegisterDto(firstName, lastName, email, username, password)
        );

        assertEquals(registerResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));
    }

    @Test
	void testKeycloakLoginClient() {
        var loginResponseEntity = KeycloakRequests.login(
                new ClientLoginDto("test","TestTest1!")
        );

        assertEquals(loginResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));
	}

    @Test
	void testIntrospectClientToken() {
        var loginResponseEntity = KeycloakRequests.login(
                new ClientLoginDto("diamond","ThisIsTestPassword1!")
        );

        assertEquals(loginResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));

        String email = KeycloakRequests.getEmail(((ClientAuthTokenDto) loginResponseEntity.getBody()).accessToken());

        assertNotNull(email);
	}

    // return 204
    // return 400
    @Test
	void testLogoutClient() {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpPost httpPost = new HttpPost("http://localhost:9001/realms/iot/protocol/openid-connect/logout");
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            String formData = "grant_type=password&" +
                    "client_id=iot-rest-api&" +
                    "client_secret=p5G4R7VbZcAD3ggL0QkSLcpsDcyLsXfR&" +
                    "refresh_token=eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJjYmM0NTczNC05ZWEyLTQ1N2EtODg4MS02ZTg4N2E3NDdhODIifQ.eyJleHAiOjE2OTk1MTc1NzgsImlhdCI6MTY5OTUxNTc3OCwianRpIjoiNzdkNjE4ZTctOTdhMS00ZTg0LWJhYjQtNjc2ODhlNDhkMDIxIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDAxL3JlYWxtcy9pb3QiLCJhdWQiOiJodHRwOi8vbG9jYWxob3N0OjkwMDEvcmVhbG1zL2lvdCIsInN1YiI6ImYwYzMxMTIzLTc3ZTktNDk4NS1iNmY2LTljYTZiMmExZjhhZCIsInR5cCI6IlJlZnJlc2giLCJhenAiOiJpb3QtcmVzdC1hcGkiLCJzZXNzaW9uX3N0YXRlIjoiMDIxOGYwMzYtNTM0MS00M2FkLWEzMDEtNDEzZWY1OGYyMWEzIiwic2NvcGUiOiJlbWFpbCBwcm9maWxlIiwic2lkIjoiMDIxOGYwMzYtNTM0MS00M2FkLWEzMDEtNDEzZWY1OGYyMWEzIn0.UV6mfWeyd5Aejm9CQAmT9tj98n1epiJMk0krvuWIYVc";
            HttpEntity entity = new StringEntity(formData);
            httpPost.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                // Handle the response
                if (response.getCode() == 204) {
                    System.out.println("Client was logout successfully");
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
	}
}
