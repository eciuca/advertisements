package com.emanuelciuca.trainings.projects.advertisements.controller;

import com.emanuelciuca.trainings.projects.advertisements.RestIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;


public class AdvertisementsControllerRestIntegrationTest extends RestIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void givenId_whenGetAdvertisementById_thenReturnAdvertisement() throws Exception {
        long id = 1;
        String expectedResult = "advertisement: " + id;

        String actual = this.restTemplate
                .getForEntity(url(AdvertisementsController.API_ADVERTISEMENTS + "/" + id), String.class)
                .getBody();

        assertThat(actual).isEqualTo(expectedResult);
    }
}
