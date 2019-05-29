package com.metaopsis.icsapi.v3.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metaopsis.icsapi.v3.dom.*;
import org.apache.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;

public class LoadStateService {
    private User user = null;
    final static Logger logger = Logger.getLogger(ImportService.class);
    private ObjectMapper mapper;
    private RestTemplate rest;
    private String _INFASESSIONID;

    public LoadStateService(User user)
    {
        this.user = user;
        this._INFASESSIONID = user.getUserInfo().getSessionId();
        logger.debug(user.toString());


        // Set ObjectMapper
        this.mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Set RestTemplate
        this.rest = new RestTemplate();
        this.rest.setErrorHandler(new CustomResponseErrorHandler());
    }

    public UploadResponse upload(File file) throws InformaticaCloudException
    {
        logger.info(this.getClass().getName()+"::upload::enter");
        if (!file.exists())
            throw new InformaticaCloudException("File " + file.getAbsolutePath() + " cannot be found!");
        UploadResponse response;
        try {
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("package", new FileSystemResource(file));

            HttpEntity<MultiValueMap<String, Object>> requestEntity = null;
            ResponseEntity<String> responseEntity = null;
            requestEntity = new HttpEntity<>(body, this.buildHttpHeaders("application/json","multipart/form-data"));

            responseEntity = rest.exchange(user.getProducts()[0].getBaseApiUrl() + "/public/core/v3/loadState/package", HttpMethod.POST, requestEntity, String.class);


            logger.info("Informatica Cloud V3 LoadState Upload " + responseEntity.getStatusCode().toString());
            if (responseEntity.getStatusCode().is2xxSuccessful())
            {
                response = mapper.readValue(responseEntity.getBody(), UploadResponse.class);
            } else {
                throw new InformaticaCloudException("Package Upload Failed");
            }

        } catch(Exception e) {
            throw new InformaticaCloudException(e.getMessage());
        }
        logger.info(this.getClass().getName()+"::upload::enter");

        return response;
    }

    public StartResponse start(String id) throws InformaticaCloudException
    {
        return this.start(id, null);
    }

    public StartResponse start(String id, StartRequest request) throws InformaticaCloudException
    {
        logger.info(this.getClass().getName()+"::start::enter");
        HttpEntity<String> requestEntity = null;
        ResponseEntity<String> responseEntity = null;
        Writer jsonWriter = new StringWriter();
        StartResponse response = null;
        try {
            mapper.writeValue(jsonWriter, request);
            jsonWriter.flush();
            requestEntity = new HttpEntity<String>((request == null ? "" : jsonWriter.toString()), this.buildHttpHeaders("application/json","application/json"));

            responseEntity = rest.exchange(user.getProducts()[0].getBaseApiUrl()+"/public/core/v3/loadState/" + id, HttpMethod.POST, requestEntity, String.class);

            logger.info("Informatica Cloud V3 LoadState Start " + responseEntity.getStatusCode().toString());

            if (responseEntity.getStatusCode().is2xxSuccessful())
            {
                response = mapper.readValue(responseEntity.getBody(), StartResponse.class);
            } else {

                logger.error(responseEntity.toString());
                throw new InformaticaCloudException(responseEntity.toString());
            }
        } catch(Exception e)
        {
            throw new InformaticaCloudException(e.getMessage());
        }
        logger.info(this.getClass().getName()+"::start::exit");
        return response;
    }

    public StatusResponse status(String id, boolean showDetail) throws InformaticaCloudException
    {
        logger.info(this.getClass().getName()+"::status::enter");

        HttpEntity<String> requestEntity = null;
        ResponseEntity<String> responseEntity = null;
        StatusResponse response = null;
        try {
            requestEntity = new HttpEntity<String>("", this.buildHttpHeaders("application/json", "application/json"));

            responseEntity = rest.exchange(user.getProducts()[0].getBaseApiUrl() + "/public/core/v3/loadState/" + id + (showDetail ? "?expand=objects" : ""), HttpMethod.GET, requestEntity, String.class);

            logger.info("Informatica Cloud V3 LoadState Status " + responseEntity.getStatusCode().toString());
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                response = mapper.readValue(responseEntity.getBody(), StatusResponse.class);
            } else {
                logger.error(responseEntity.toString());
                throw new InformaticaCloudException(responseEntity.toString());
            }
        } catch(Exception e)
        {
            throw new InformaticaCloudException(e.getMessage());
        }

        logger.info(this.getClass().getName()+"::status::exit");
        return response;
    }

    private HttpHeaders buildHttpHeaders(String accept, String contentType)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", accept);
        headers.add("Content-Type", contentType);
        headers.add("INFA-SESSION-ID", this._INFASESSIONID);

        return headers;
    }
}
