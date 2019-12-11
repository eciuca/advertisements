package com.emanuelciuca.trainings.projects.advertisements.controller;

import com.emanuelciuca.trainings.projects.advertisements.RestIntegrationTest;
import com.emanuelciuca.trainings.projects.advertisements.dto.AdvertisementDto;
import com.emanuelciuca.trainings.projects.advertisements.dto.AdvertisementMapper;
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
    private AdvertisementMapper mapper;

    @Autowired
    private TestRestTemplate restTemplate;

    @AfterEach
    public void after() {
        repository.deleteAll();
    }

    @Test
    public void givenExistingId_whenGetAdvertisementById_thenReturnAdvertisement() {
        // GIVEN
        Advertisement expectedAdvertisement = createAdvertisement("title 123");

        AdvertisementDto expectedResult = mapper.toDto(expectedAdvertisement);

        // WHEN
        String relativePath = API_ADVERTISEMENTS + "/" + expectedAdvertisement.getId();
        ResponseEntity<AdvertisementDto> response = this.restTemplate
                .getForEntity(url(relativePath), AdvertisementDto.class);

        // THEN
        assertEquals(expectedResult, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test()
    public void givenNonExistingId_whenGetAdvertisementById_thenStatusCodeIsInternalServerError() {
        // GIVEN
        long nonExistingId = 1L;

        // WHEN
        String relativePath = API_ADVERTISEMENTS + "/" + nonExistingId;

        HttpStatus statusCode = this.restTemplate
                .getForEntity(url(relativePath), AdvertisementDto.class)
                .getStatusCode();

        // THEN
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, statusCode);
    }

    @Test
    public void givenAdvertisementDetails_whenPostRequestIsReceived_ThenCreateNewAdvertisement() {
        // GIVEN
        AdvertisementDto details = AdvertisementDto.advertisementDto()
                .withTitle("new advertisement");

        // WHEN
        ResponseEntity<AdvertisementDto> response = this.restTemplate
                .postForEntity(API_ADVERTISEMENTS, details, AdvertisementDto.class);

        // THEN
        AdvertisementDto newAdvertisementDto = response.getBody();

        Optional<Advertisement> newAdvertisement = this.repository.findById(newAdvertisementDto.id);

        Assertions.assertTrue(newAdvertisement.isPresent());
        Assertions.assertEquals(details.withId(newAdvertisementDto.id), newAdvertisementDto);
    }

    @Test
    public void givenNewAdvertisementDetails_WhenPostRequestIsReceived_ThenUpdateAdvertisement() {
        // GIVEN
        Advertisement existingAdvertisement = createAdvertisement("title 123");

        AdvertisementDto newAdvertisementDetails = mapper.toDto(existingAdvertisement).withTitle("title 124");

        // WHEN
        String relativePath = API_ADVERTISEMENTS + "/" + newAdvertisementDetails.id;
        this.restTemplate.put(relativePath, newAdvertisementDetails);

        // THEN
        Optional<Advertisement> updatedAdvertisement = this.repository.findById(newAdvertisementDetails.id);

        Assertions.assertTrue(updatedAdvertisement.isPresent());
        Assertions.assertEquals(newAdvertisementDetails.title, updatedAdvertisement.get().getTitle());
    }


    @Test
    public void givenExistingAdvertisement_WhenDeleteRequestIsReceived_ThenAdvertisementIsDeleted() {
        // GIVEN
        Advertisement existingAdvertisement = createAdvertisement("title 123");

        // WHEN
        String relativePath = API_ADVERTISEMENTS + "/" + existingAdvertisement.getId();
        this.restTemplate.delete(relativePath, existingAdvertisement.getId());

        // THEN
        Optional<Advertisement> updatedAdvertisement = this.repository.findById(existingAdvertisement.getId());

        Assertions.assertFalse(updatedAdvertisement.isPresent());
    }

    private Advertisement createAdvertisement(String title) {
        Advertisement expectedAdvertisement = new Advertisement();
        expectedAdvertisement.setTitle(title);

        return repository.save(expectedAdvertisement);
    }
}
