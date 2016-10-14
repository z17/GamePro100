package service_client.clients;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import service_client.result.Result;

import java.util.Collections;

abstract class Client {
    private final RestTemplate rest;
    private final static String GATEWAY_PATH = "http://localhost:8080/services";

    Client() {
        rest = new RestTemplate(Collections.singletonList(new MappingJackson2HttpMessageConverter()));
    }

    protected <T extends Result> T get(final String path, final Class<T> type) {
        return rest.getForObject(GATEWAY_PATH  + path, type);
    }

    protected <T extends Result, E> T post(final String path, final E object, final Class<T> type) {
        return rest.postForObject(GATEWAY_PATH + path, object, type);
    }
}
