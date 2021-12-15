package com.dumitruc.training.pokemon.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class ExternalCallsServiceImpl implements ExternalCallsService {
    @Override
    public String getUrl(URI uri) {

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(uri, String.class);

        return response;
    }
}
