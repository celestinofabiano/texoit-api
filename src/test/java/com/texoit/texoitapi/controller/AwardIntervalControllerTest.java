package com.texoit.texoitapi.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AwardIntervalControllerTest {

    private TestRestTemplate testRestTemplate = new TestRestTemplate();

    private HttpHeaders httpHeaders = new HttpHeaders();

    @LocalServerPort
    private int port;

    @Test
    public void deveRetornarStatusOkEJsonValidoQuandoConsultarAwardsInterval() {
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, httpHeaders);
        String url = "http://localhost:" + port + "/awards-interval";
        ResponseEntity<String> responseEntity = testRestTemplate.exchange( url, HttpMethod.GET, httpEntity, String.class);
        String expectedBody =
                "{\"min\":[{\"producer\":\"Joel Silver\",\"interval\":1,\"previousWin\":1990,\"followingWin\":1991}],\"max\":[{\"producer\":\"Matthew Vaughn\",\"interval\":13,\"previousWin\":2002,\"followingWin\":2015}]}";

        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
        assertThat(responseEntity.getBody()).isEqualTo(expectedBody);
    }

}