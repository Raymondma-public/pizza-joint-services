package com.pizzajoint.orderpersistenceservice.requests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class OrderInsertionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void callOrderInsert_withAllValidInformation_201() throws Exception {
        String payload = "{\n" +
                "    \"name\": \"Pizza A\",\n" +
                "    \"quantity\": 2,\n" +
                "    \"price\": 50\n" +
                "}";
        mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON).content(payload))
                .andExpect(status().isCreated());
    }


    @Test
    void callOrderInsert_withoutName_400() throws Exception {
        String payload = "{\n" +
                "    \"quantity\": 2,\n" +
                "    \"price\": 50\n" +
                "}";
        mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON).content(payload))
                .andExpect(status().isBadRequest());
    }

    @Test
    void callOrderInsert_withoutQuantity_400() throws Exception {
        String payload = "{\n" +
                "    \"name\": \"Pizza A\",\n" +
                "    \"price\": 50\n" +
                "}";
        mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON).content(payload))
                .andExpect(status().isBadRequest());
    }

    @Test
    void callOrderInsert_withoutPrice_400() throws Exception {
        String payload = "{\n" +
                "    \"name\": \"Pizza A\",\n" +
                "    \"quantity\": 2\n" +
                "}";
        mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON).content(payload))
                .andExpect(status().isBadRequest());
    }

    @Test
    void callOrderInsert_withoutPayload_400() throws Exception {
        mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void callOrderInsert_noContentType_415() throws Exception {
        mockMvc.perform(post("/order"))
                .andExpect(status().isUnsupportedMediaType());
    }

}
