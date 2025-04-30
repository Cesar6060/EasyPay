package com.easypay.framework.core.api;

import com.easypay.framework.config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base API Client class for all API interactions.
 * Provides common functionality for API testing.
 */
public class BaseApiClient {
    private static final Logger logger = LoggerFactory.getLogger(BaseApiClient.class);
    protected final ConfigManager config = ConfigManager.getInstance();
    protected final String baseUrl;
    protected RequestSpecification requestSpec;
    protected ResponseSpecification responseSpec;

    public BaseApiClient() {
        this.baseUrl = config.getProperty("api.base.url");

        // Initialize request specification
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + config.getProperty("api.auth.token"))
                .log(LogDetail.ALL)
                .build();

        // Initialize response specification
        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        logger.info("Initialized API client with base URL: {}", baseUrl);
    }

    /**
     * Perform a GET request
     * 
     * @param endpoint the API endpoint
     * @return Response object
     */
    protected Response get(String endpoint) {
        logger.info("Performing GET request to endpoint: {}", endpoint);
        return RestAssured.given(requestSpec)
                .get(endpoint)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    /**
     * Perform a POST request with a request body
     * 
     * @param endpoint    the API endpoint
     * @param requestBody object to be serialized as request body
     * @return Response object
     */
    protected Response post(String endpoint, Object requestBody) {
        logger.info("Performing POST request to endpoint: {}", endpoint);
        return RestAssured.given(requestSpec)
                .body(requestBody)
                .post(endpoint)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    /**
     * Perform a PUT request with a request body
     * 
     * @param endpoint    the API endpoint
     * @param requestBody object to be serialized as request body
     * @return Response object
     */
    protected Response put(String endpoint, Object requestBody) {
        logger.info("Performing PUT request to endpoint: {}", endpoint);
        return RestAssured.given(requestSpec)
                .body(requestBody)
                .put(endpoint)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    /**
     * Perform a DELETE request
     * 
     * @param endpoint the API endpoint
     * @return Response object
     */
    protected Response delete(String endpoint) {
        logger.info("Performing DELETE request to endpoint: {}", endpoint);
        return RestAssured.given(requestSpec)
                .delete(endpoint)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }
}