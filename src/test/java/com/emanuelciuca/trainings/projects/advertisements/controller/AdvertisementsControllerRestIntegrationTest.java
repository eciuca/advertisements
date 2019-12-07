package com.emanuelciuca.trainings.projects.advertisements.controller;

import com.emanuelciuca.trainings.projects.advertisements.RestIntegrationTest;
import com.emanuelciuca.trainings.projects.advertisements.dto.AdvertisementDto;
import com.emanuelciuca.trainings.projects.advertisements.model.Advertisement;
import com.emanuelciuca.trainings.projects.advertisements.repository.AdvertisementsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class AdvertisementsControllerRestIntegrationTest extends RestIntegrationTest {

    @Autowired
    private AdvertisementsRepository repository;

    @Autowired
    private TestRestTemplate restTemplate;

    @AfterEach
    public void after() {
        repository.deleteAll();
    }

    @Test
    public void givenExistingId_whenGetAdvertisementById_thenReturnAdvertisement() {
        Advertisement expectedAdvertisement = repository.saveAndFlush(new Advertisement());

        AdvertisementDto expectedResult = AdvertisementDto
                .advertisementDto()
                .withId(expectedAdvertisement.getId());

        String relativePath = AdvertisementsController.API_ADVERTISEMENTS + "/" + expectedAdvertisement.getId();
        AdvertisementDto actual = this.restTemplate
                .getForEntity(url(relativePath), AdvertisementDto.class)
                .getBody();

        assertThat(actual).isEqualTo(expectedResult);
    }
}
