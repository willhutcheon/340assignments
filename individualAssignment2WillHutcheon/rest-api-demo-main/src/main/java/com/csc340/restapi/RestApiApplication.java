package com.csc340.restapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
        getLyrics();
        System.exit(0);
    }

    public static void getLyrics() {
        try {
            String url = "https://api.lyrics.ovh/v1/Toto/Africa";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();
            String websiteLyrics = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(websiteLyrics);
            String lyrics = root.findValue("lyrics").asText();
            System.out.println("Lyrics: " + lyrics);
        } catch (JsonProcessingException ex) {
            System.out.println("ERROR: " + ex.toString());
        }
    }
}
