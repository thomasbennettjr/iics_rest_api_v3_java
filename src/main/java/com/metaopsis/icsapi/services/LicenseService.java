package com.metaopsis.icsapi.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metaopsis.icsapi.dom.License;
import com.metaopsis.icsapi.dom.User;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.StringWriter;
import java.io.Writer;

public class LicenseService {
    private User user = null;
    final static Logger logger = Logger.getLogger(LicenseService.class);
    private ObjectMapper mapper;
    private RestTemplate rest;
    private HttpHeaders headers;

    public LicenseService(User user)
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

    public License getLicenses() throws InformaticaCloudException
    {
        logger.info(this.getClass().getName()+"::getLicenses::enter");
        License license = null;
        HttpEntity<String> requestEntity = null;
        ResponseEntity<String> responseEntity = null;
        try {

            requestEntity = new HttpEntity<String>("", headers);

            responseEntity = rest.exchange(user.getProducts()[0].getBaseApiUrl()+"/public/core/v3/license/org/"+user.getUserInfo().getOrgId(), HttpMethod.GET, requestEntity, String.class);

            if (responseEntity.getStatusCode().is2xxSuccessful())
            {
                logger.info("Informatica Cloud V3 getLicense 200 Success");
                license = mapper.readValue(responseEntity.getBody(), License.class);
            } else {
                logger.info("Informatica Cloud V3 getLicense " + responseEntity.getStatusCode().toString());
                logger.error(responseEntity.toString());
                throw new InformaticaCloudException(responseEntity.toString());
            }
        } catch(Exception e)
        {
            throw new InformaticaCloudException(e.getMessage());
        }
        logger.info(this.getClass().getName()+"::getLicenses::exit");
        return license;
    }

    public boolean putLicenses(License license) throws InformaticaCloudException
    {
        logger.info(this.getClass().getName()+"::putLicenses::enter");
        Writer jsonWriter = new StringWriter();
        HttpEntity<String> requestEntity = null;
        ResponseEntity<String> responseEntity = null;
        boolean result = false;
        try {
            mapper.writeValue(jsonWriter, license);
            jsonWriter.flush();
            requestEntity = new HttpEntity<String>(jsonWriter.toString(), headers);

            responseEntity = rest.exchange(user.getProducts()[0].getBaseApiUrl()+"/public/core/v3/license/org/"+user.getUserInfo().getOrgId(), HttpMethod.PUT, requestEntity, String.class);

            if (responseEntity.getStatusCode().is2xxSuccessful())
            {
                logger.info("Informatica Cloud V3 putLicense 200 Success");
                result = true;
            } else {
                logger.info("Informatica Cloud V3 putLicense " + responseEntity.getStatusCode().toString());
                logger.error(responseEntity.toString());
                throw new InformaticaCloudException(responseEntity.toString());
            }
        } catch(Exception e)
        {
            throw new InformaticaCloudException(e.getMessage());
        }

        logger.info(this.getClass().getName()+"::putLicenses::exit");
        return result;
    }
}
