package com.search.location.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

public abstract class IntegrationTestHelper {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected <T> BDDMockito.BDDMyOngoingStubbing<T> given(T methodCall) {
        return BDDMockito.given(methodCall);
    }

    protected MockHttpServletRequestBuilder post(String url) {
        return MockMvcRequestBuilders.post(url);
    }

    protected MockHttpServletRequestBuilder get(String url) {
        return MockMvcRequestBuilders.get(url);
    }

    protected MockHttpServletRequestBuilder patch(String url) {
        return MockMvcRequestBuilders.patch(url);
    }

    protected StatusResultMatchers status() {
        return MockMvcResultMatchers.status();
    }

    protected JsonPathResultMatchers jsonPath(String expr) {
        return MockMvcResultMatchers.jsonPath(expr);
    }

    protected ResultHandler print() {
        return MockMvcResultHandlers.print();
    }

    protected <T> T verify(T mock) {
        return Mockito.verify(mock);
    }
}
