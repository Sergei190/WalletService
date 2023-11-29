package ru.project.sergei.walletservice;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WalletServiceApplication.class)
@ActiveProfiles("test")
class WalletServiceApplicationTests {

    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    protected <T> T stringToJson(String json, Class<T> classToConvert)
            throws JsonParseException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserialxizationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.readValue(json, classToConvert);
    }

}