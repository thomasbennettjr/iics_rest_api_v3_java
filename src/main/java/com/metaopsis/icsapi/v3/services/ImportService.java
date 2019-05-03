package com.metaopsis.icsapi.v3.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metaopsis.icsapi.dom.*;
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

import java.io.*;

public class ImportService {
    private User user = null;
    final static Logger logger = Logger.getLogger(ImportService.class);
    private ObjectMapper mapper;
    private RestTemplate rest;
    private String _INFASESSIONID;

    public ImportService(User user)
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

    public UploadResponse upload(File file, boolean relaxChecksum) throws InformaticaCloudException
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

            responseEntity = rest.exchange(user.getProducts()[0].getBaseApiUrl() + "/public/core/v3/import/package" + (relaxChecksum ? "?relaxChecksum=True" : ""), HttpMethod.POST, requestEntity, String.class);


            logger.info("Informatica Cloud V3 Import Upload " + responseEntity.getStatusCode().toString());
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

            responseEntity = rest.exchange(user.getProducts()[0].getBaseApiUrl()+"/public/core/v3/import/" + id, HttpMethod.POST, requestEntity, String.class);

            logger.info("Informatica Cloud V3 Import Start " + responseEntity.getStatusCode().toString());

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

            responseEntity = rest.exchange(user.getProducts()[0].getBaseApiUrl() + "/public/core/v3/import/" + id + (showDetail ? "?expand=objects" : ""), HttpMethod.GET, requestEntity, String.class);

            logger.info("Informatica Cloud V3 Import Status " + responseEntity.getStatusCode().toString());
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

    public void getStatusLog(String id, File file) throws InformaticaCloudException
    {
        logger.info(this.getClass().getName()+"::statusLog::enter");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            HttpEntity<String> requestEntity = null;
            ResponseEntity<String> responseEntity = null;
            requestEntity = new HttpEntity<String>("", this.buildHttpHeaders("text/plain","application/json"));

            responseEntity = rest.exchange(user.getProducts()[0].getBaseApiUrl() + "/public/core/v3/import/" + id + "/log", HttpMethod.GET, requestEntity, String.class);

            logger.info("Informatica Cloud V3 Import Status Log " + responseEntity.getStatusCode().toString());
            if (responseEntity.getStatusCode().is2xxSuccessful())
            {
                writer.write(responseEntity.getBody());
                writer.newLine();
                writer.close();
            } else {
                logger.error(responseEntity.getBody());
                throw new InformaticaCloudException(responseEntity.getBody());
            }

        } catch(Exception e) {
            throw new InformaticaCloudException(e.getMessage());
        }

        logger.info(this.getClass().getName()+"::statusLog::exit");
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
