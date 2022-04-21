package com.pizzajoint.ordergenerationservice.contracts;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzajoint.ordergenerationservice.models.requests.PizzaOrderRequestPayload;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@SpringBootTest
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "OrderPersistenceService")
public class OrderGenerationServiceTest{

    @Autowired
    static ObjectMapper objectMapper = new ObjectMapper();
    static PizzaOrderRequestPayload pizzaOrderRequestPayload;
    static String pizzaOrderPayloadJsonString;

    @BeforeAll
    static void beforeAll(){
        pizzaOrderRequestPayload = new PizzaOrderRequestPayload();
        pizzaOrderRequestPayload.setName("raymond");
        pizzaOrderRequestPayload.setPrice(12.25);
        pizzaOrderRequestPayload.setQuantity(1);
        try {
            pizzaOrderPayloadJsonString = objectMapper.writeValueAsString(pizzaOrderRequestPayload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Pact(provider="OrderPersistenceService", consumer = "OrderGenerationService")
    public RequestResponsePact createPactAddOrderRequest(PactDslWithProvider builder) {
        System.out.println("createPact");
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return builder
                .given("add order request")
                .uponReceiving("add order request")
                .method("POST")
                .headers(headers)
                .path("/order")
                .body(pizzaOrderPayloadJsonString)
                .willRespondWith()
                .status(201)
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "createPactAddOrderRequest")
    public void givenGet_whenSendRequest_shouldReturn201(MockServer mockServer) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        String url = mockServer.getUrl() + "/order";
        System.out.println(url);
        // when
        ResponseEntity<String> postResponse = new RestTemplate().exchange(url, HttpMethod.POST, new HttpEntity<>(pizzaOrderPayloadJsonString,httpHeaders), String.class);

        // then
        assertEquals(201,postResponse.getStatusCode().value());
    }


    @Pact(provider="OrderPersistenceService", consumer = "OrderGenerationService")
    public RequestResponsePact createPactAddOrderRequestWithoutBody(PactDslWithProvider builder) {
        System.out.println("createPact");
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return builder

                .given("add order request without body")
                .uponReceiving("add order request without body")
                .method("POST")
                .headers(headers)
                .path("/order")
                .willRespondWith()
                .body("")
                .status(400)

                .toPact();
    }


    @Test
    @PactTestFor(pactMethod = "createPactAddOrderRequestWithoutBody")
    public void givenGet_whenSendRequest_shouldReturn400(MockServer mockServer) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        String url = mockServer.getUrl() + "/order";
        System.out.println(url);
        // when
        HttpClientErrorException httpException= assertThrows(HttpClientErrorException.class,()->{
            ResponseEntity<String> postResponse = new RestTemplate().exchange(url, HttpMethod.POST, new HttpEntity<>(httpHeaders), String.class);
        });
        assertEquals(400,httpException.getStatusCode().value());
    }

}
