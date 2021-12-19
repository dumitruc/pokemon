package com.dumitruc.training.pokemon.services;

import com.dumitruc.training.pokemon.exceptions.UnableToObtainInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class ExternalCallsServiceImpl implements ExternalCallsService {

    public static final Logger logger = LoggerFactory.getLogger(ExternalCallsServiceImpl.class);

    @Override
    public String getUrl(URI uri) {

        ResponseEntity<String> responseEntity;
        RestTemplate restTemplate = new RestTemplate();

        try {
            responseEntity = restTemplate.getForEntity(uri, String.class);
        }catch(Exception ex){
            logger.error("{}\n{}",ex.getMessage(),ex);
            throw new UnableToObtainInfo("Error accessing external service.");
        }

        return StringUtils.trim(responseEntity.getBody());
    }
}
