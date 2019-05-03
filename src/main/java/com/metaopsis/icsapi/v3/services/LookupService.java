package com.metaopsis.icsapi.v3.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metaopsis.icsapi.dom.*;
import com.metaopsis.icsapi.v3.dom.Lookup;
import com.metaopsis.icsapi.v3.dom.User;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import java.io.StringWriter;
import java.io.Writer;

public class LookupService {
    private User user = null;
    final static Logger logger = Logger.getLogger(LookupService.class);
    private ObjectMapper mapper;
    private RestTemplate rest;
    private HttpHeaders headers;

    public LookupService(User user)
    {
        this.user = user;

        // Set HttpHeaders for request
        this.headers = new HttpHeaders();
        this.headers.add("Content-Type", "application/json");
        this.headers.add("Accept", "application/json");
        this.headers.add("INFA-SESSION-ID", user.getUserInfo().getSessionId());

        // Set ObjectMapper
        this.mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Set RestTemplate
        this.rest = new RestTemplate();
        this.rest.setErrorHandler(new CustomResponseErrorHandler());
    }

    public Lookup doLookup(Lookup lookup) throws InformaticaCloudException
    {
        logger.debug(lookup.toString());
        logger.info(this.getClass().getName()+"::lookup::enter");
        Lookup result = null;
        Writer jsonWriter = new StringWriter();
        HttpEntity<String> requestEntity = null;
        ResponseEntity<String> responseEntity = null;
        try {

            mapper.writeValue(jsonWriter, lookup);
            jsonWriter.flush();

            requestEntity = new HttpEntity<String>(jsonWriter.toString(), headers);

            responseEntity = rest.exchange(user.getProducts()[0].getBaseApiUrl()+"/public/core/v3/lookup", HttpMethod.POST, requestEntity, String.class);

            if (responseEntity.getStatusCode().is2xxSuccessful())
            {
                logger.info("Informatica Cloud V3 Lookup 200 Success");
                result = mapper.readValue(responseEntity.getBody(), Lookup.class);
            } else {
                logger.info("Informatica Cloud V3 Lookup " + responseEntity.getStatusCode().toString());
                logger.error(responseEntity.toString());
                throw new InformaticaCloudException(responseEntity.toString());
            }
        } catch(Exception e)
        {
            throw new InformaticaCloudException(e.getMessage());
        }

        logger.info(this.getClass().getName()+"::lookup::exit");

        return result;
    }
}
