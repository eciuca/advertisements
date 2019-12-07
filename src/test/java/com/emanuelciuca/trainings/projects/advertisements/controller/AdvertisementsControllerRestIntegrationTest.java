package com.emanuelciuca.trainings.projects.advertisements.controller;

import com.emanuelciuca.trainings.projects.advertisements.RestIntegrationTest;
import com.emanuelciuca.trainings.projects.advertisements.dto.AdvertisementDto;
import com.emanuelciuca.trainings.projects.advertisements.model.Advertisement;
import com.emanuelciuca.trainings.projects.advertisements.repository.AdvertisementsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


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
        ResponseEntity<AdvertisementDto> response = this.restTemplate
                .getForEntity(url(relativePath), AdvertisementDto.class);


        Assertions.assertEquals(expectedResult, response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test()
    public void givenNonExistingId_whenGetAdvertisementById_thenStatusCodeIsInternalServerError() {
        Long nonExistingId = 1L;

        String relativePath = AdvertisementsController.API_ADVERTISEMENTS + "/" + nonExistingId;

        HttpStatus statusCode = this.restTemplate
                .getForEntity(url(relativePath), AdvertisementDto.class)
                .getStatusCode();

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, statusCode);
    }
}
