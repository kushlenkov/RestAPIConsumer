package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Translator {
    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("Введите предложение на русском языке:");
        Scanner scanner = new Scanner(System.in);
        String sentenceToTranslate = scanner.nextLine();

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://translate.api.cloud.yandex.net/translate/v2/translate";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization",  "Bearer " + "t1.9euelZqVmJXKjpCLnZ7IncqLz43HmO3rnpWamJzPmZqNkJCKm5KLys-OyZTl8_clfy9O-e9uQgUO_t3z92UtLU75725CBQ7-zef1656Vms2Qk4uXjZyJnJHNzoubj8rL7_zN5_XrnpWamZCZmZeLnZGPk5mQzozJj57v_cXrnpWazZCTi5eNnImckc3Oi5uPyss.BaOUNl5H3NlaebrzUQYILmb0owRvYrdxcgY-9AvUVvs5T_7-ryVqps_hTlVGgty5IRJ9X3IgwnOtqQyiZPi3Aw");

        Map<String, String> jsonData = new HashMap<>();
        jsonData.put("folderId", "b1gmchddo5skpiaqs3f1");
        jsonData.put("targetLanguageCode", "en");
        jsonData.put("texts", "[" + sentenceToTranslate + "]");

        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonData, headers);

        YandexResponse yandexResponse = restTemplate.postForObject(url, request, YandexResponse.class);

        // Парсим полученный JSON с помощью Jackson
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode jsonNode = mapper.readTree(response);

        // Ипользуем класс
        System.out.println("Перевод: " + yandexResponse.getTranslations().get(0).getText());
    }
}
