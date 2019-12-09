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

import java.util.Optional;

import static com.emanuelciuca.trainings.projects.advertisements.controller.AdvertisementsController.API_ADVERTISEMENTS;
import static org.junit.jupiter.api.Assertions.assertEquals;


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
        Advertisement expectedAdvertisement = new Advertisement();
        expectedAdvertisement.setTitle("title 123");
        expectedAdvertisement = repository.saveAndFlush(expectedAdvertisement);

        AdvertisementDto expectedResult = AdvertisementDto
                .advertisementDto()
                .withId(expectedAdvertisement.getId())
                .withTitle(expectedAdvertisement.getTitle());

        String relativePath = API_ADVERTISEMENTS + "/" + expectedAdvertisement.getId();
        ResponseEntity<AdvertisementDto> response = this.restTemplate
                .getForEntity(url(relativePath), AdvertisementDto.class);


        assertEquals(expectedResult, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test()
    public void givenNonExistingId_whenGetAdvertisementById_thenStatusCodeIsInternalServerError() {
        long nonExistingId = 1L;

        String relativePath = API_ADVERTISEMENTS + "/" + nonExistingId;

        HttpStatus statusCode = this.restTemplate
                .getForEntity(url(relativePath), AdvertisementDto.class)
                .getStatusCode();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, statusCode);
    }

    @Test
    public void givenAdvertisementDetails_whenPostRequestIsReceived_ThenCreateNewAdvertisement() {
        AdvertisementDto details = AdvertisementDto.advertisementDto()
                .withTitle("new advertisement");

        ResponseEntity<AdvertisementDto> response = this.restTemplate
                .postForEntity(API_ADVERTISEMENTS, details, AdvertisementDto.class);

        AdvertisementDto newAdvertisementDto = AdvertisementDto.advertisementDto()
                .withId(response.getBody().id)
                .withTitle(response.getBody().title);

        Optional<Advertisement> newAdvertisement = this.repository.findById(newAdvertisementDto.id);

        Assertions.assertTrue(newAdvertisement.isPresent());
        Assertions.assertEquals(details, newAdvertisementDto);
    }
}
