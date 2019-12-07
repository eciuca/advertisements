package com.emanuelciuca.trainings.projects.advertisements.controller;

import com.emanuelciuca.trainings.projects.advertisements.RestIntegrationTest;
import com.emanuelciuca.trainings.projects.advertisements.dto.AdvertisementDto;
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
        AdvertisementDto expectedResult = AdvertisementDto.advertisementDto().withId(id);

        AdvertisementDto actual = this.restTemplate
                .getForEntity(url(AdvertisementsController.API_ADVERTISEMENTS + "/" + id), AdvertisementDto.class)
                .getBody();

        assertThat(actual).isEqualTo(expectedResult);
    }
}
