package com.metaopsis.icsapi.v3.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.metaopsis.icsapi.v3.dom.ObjectResponse;
import com.metaopsis.icsapi.v3.dom.Query;
import com.metaopsis.icsapi.v3.dom.User;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ObjectsService {
    private User user = null;
    final static Logger logger = Logger.getLogger(ObjectsService.class);
    private ObjectMapper mapper;
    private RestTemplate rest;
    private HttpHeaders headers;

    public ObjectsService(User user)
    {
        this.user = user;
        logger.debug(user.toString());
        // Set HttpHeaders for request
        this.headers = new HttpHeaders();
        this.headers.add("Content-Type", "application/json");
        this.headers.add("Accept", "application/json");
        this.headers.add("INFA-SESSION-ID",user.getUserInfo().getSessionId());

        // Set ObjectMapper
        this.mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Set RestTemplate
        this.rest = new RestTemplate();
        this.rest.setErrorHandler(new CustomResponseErrorHandler());
    }

    public ObjectResponse getObjects(Query query) throws InformaticaCloudException
    {
        logger.info(this.getClass().getName()+"::objects::enter");
        logger.debug(query.toString());
        HttpEntity<String> requestEntity = null;
        ResponseEntity<String> responseEntity = null;
        ObjectResponse objectResponse = null;
        try
        {
            requestEntity = new HttpEntity<String>("", headers);

            responseEntity = rest.exchange(user.getProducts()[0].getBaseApiUrl()+"/public/core/v3/objects"+query.toString(), HttpMethod.GET, requestEntity, String.class);

            if (responseEntity.getStatusCode().is2xxSuccessful())
            {
                logger.info("Informatica Cloud V3 getObjects 200 Success");
                objectResponse = mapper.readValue(responseEntity.getBody(), ObjectResponse.class);
            } else {
                logger.info("Informatica Cloud V3 getObjects " + responseEntity.getStatusCode().toString());
                logger.error(responseEntity.toString());
                throw new InformaticaCloudException(responseEntity.toString());
            }

        } catch(Exception e)
        {
            throw new InformaticaCloudException(e.getMessage());
        }

        logger.info(this.getClass().getName()+"::objects::exit");

        return objectResponse;
    }
}
