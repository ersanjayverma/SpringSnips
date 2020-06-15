package com.github.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getAll() throws MalformedURLException {
        ResponseEntity<String> response = restTemplate.getForEntity(
                new URL("http://localhost:" + randomServerPort + "/api/users/search").toString(), String.class);
        assertEquals("[{\"id\":10001,\"name\":\"Ranga\",\"age\":25,\"email\":\"abc@test.com\"},{\"id\":10002,\"name\":\"Ravi\",\"age\":28,\"email\":\"xyz@test.com\"}]", response.getBody());

        response = restTemplate.getForEntity(
                new URL("http://localhost:" + randomServerPort + "/api/users/search?name=Ranga").toString(), String.class);
        assertEquals("[{\"id\":10001,\"name\":\"Ranga\",\"age\":25,\"email\":\"abc@test.com\"}]", response.getBody());

    }
}