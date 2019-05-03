package com.metaopsis.icsapi.v3.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metaopsis.icsapi.v3.dom.User;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class LogoutService {
    private Url url = null;
    final static Logger logger = Logger.getLogger(LogoutService.class);
    private ObjectMapper mapper;
    private RestTemplate rest;
    private HttpHeaders headers;

    public LogoutService(Url url)
    {
        this.url = url;

        // Set HttpHeaders for request
        this.headers = new HttpHeaders();
        this.headers.add("Content-Type", "application/json");
        this.headers.add("Accept", "application/json");

        // Set ObjectMapper
        this.mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Set RestTemplate
        this.rest = new RestTemplate();
        this.rest.setErrorHandler(new CustomResponseErrorHandler());
    }

    public void logout(User user) throws InformaticaCloudException {
        logger.debug(user.toString());
        logger.info(this.getClass().getName()+"::logout::enter");

        HttpEntity<String> requestEntity = null;
        ResponseEntity<String> responseEntity = null;

        try {
            this.headers.add("INFA-SESSION-ID", user.getUserInfo().getSessionId());
            requestEntity = new HttpEntity<String>(null, headers);

            responseEntity = rest.exchange(url.toString(), HttpMethod.POST, requestEntity, String.class);

            if (responseEntity.getStatusCode().is2xxSuccessful())
            {
                logger.info("Informatica Cloud V3 Logout 200 Success");
            } else {
                logger.info("Informatica Cloud V3 Logout " + responseEntity.getStatusCode().toString());
                logger.error(responseEntity.toString());
                throw new InformaticaCloudException(responseEntity.toString());
            }
        } catch(Exception e)
        {
            throw new InformaticaCloudException(e.getMessage());
        }

        logger.info(this.getClass().getName()+"::logout::exit");
    }
}
