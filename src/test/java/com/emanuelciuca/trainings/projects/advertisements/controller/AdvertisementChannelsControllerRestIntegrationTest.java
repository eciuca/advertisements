package com.emanuelciuca.trainings.projects.advertisements.controller;

import com.emanuelciuca.trainings.projects.advertisements.RestIntegrationTest;
import com.emanuelciuca.trainings.projects.advertisements.dto.AdvertisementChannelDto;
import com.emanuelciuca.trainings.projects.advertisements.dto.AdvertisementChannelMapper;
import com.emanuelciuca.trainings.projects.advertisements.model.AdvertisementChannel;
import com.emanuelciuca.trainings.projects.advertisements.model.ChannelType;
import com.emanuelciuca.trainings.projects.advertisements.repository.AdvertisementChannelsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static com.emanuelciuca.trainings.projects.advertisements.controller.AdvertisementChannelsController.API_ADVERTISEMENT_CHANNELS;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AdvertisementChannelsControllerRestIntegrationTest extends RestIntegrationTest {

    @Autowired
    private AdvertisementChannelMapper mapper;

    @Autowired
    private AdvertisementChannelsRepository repository;

    @Autowired
    private TestRestTemplate restTemplate;

    @AfterEach
    public void after() {
        repository.deleteAll();
    }

    @Test
    public void givenExistingId_whenGetAdvertisementChannelById_thenReturnAdvertisementChannel() {
        // GIVEN
        AdvertisementChannel expectedAdvertisementChannel = createAdvertisementChannel("title 123", ChannelType.TV);
        AdvertisementChannelDto expectedResult = mapper.toDto(expectedAdvertisementChannel);

        // WHEN
        String relativePath = API_ADVERTISEMENT_CHANNELS + "/" + expectedAdvertisementChannel.getId();
        ResponseEntity<AdvertisementChannelDto> response = this.restTemplate
                .getForEntity(url(relativePath), AdvertisementChannelDto.class);

        // THEN
        assertEquals(expectedResult, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test()
    public void givenNonExistingId_whenGetAdvertisementChannelById_thenStatusCodeIsInternalServerError() {
        // GIVEN
        long nonExistingId = 1L;

        // WHEN
        String relativePath = API_ADVERTISEMENT_CHANNELS + "/" + nonExistingId;

        HttpStatus statusCode = this.restTemplate
                .getForEntity(url(relativePath), AdvertisementChannelDto.class)
                .getStatusCode();

        // THEN
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, statusCode);
    }

    @Test
    public void givenAdvertisementChannelDetails_whenPostRequestIsReceived_ThenCreateNewAdvertisementChannel() {
        // GIVEN
        AdvertisementChannelDto details = AdvertisementChannelDto.advertisementChannelDto()
                .withName("new advertisement")
                .withType(ChannelType.TV.name());

        // WHEN
        ResponseEntity<AdvertisementChannelDto> response = this.restTemplate
                .postForEntity(API_ADVERTISEMENT_CHANNELS, details, AdvertisementChannelDto.class);

        // THEN
        AdvertisementChannelDto newAdvertisementChannelDto = response.getBody();

        Optional<AdvertisementChannel> newAdvertisementChannel = this.repository.findById(newAdvertisementChannelDto.id);

        Assertions.assertTrue(newAdvertisementChannel.isPresent());
        Assertions.assertEquals(details.withId(newAdvertisementChannelDto.id), newAdvertisementChannelDto);
    }

    @Test
    public void givenNewAdvertisementChannelDetails_WhenPostRequestIsReceived_ThenUpdateAdvertisementChannel() {
        // GIVEN
        AdvertisementChannel existingAdvertisementChannel = createAdvertisementChannel("name 123", ChannelType.TV);
        AdvertisementChannelDto newAdvertisementChannelDetails = mapper.toDto(existingAdvertisementChannel).withName("name 124");

        // WHEN
        String relativePath = API_ADVERTISEMENT_CHANNELS + "/" + newAdvertisementChannelDetails.id;
        this.restTemplate.put(relativePath, newAdvertisementChannelDetails);

        // THEN
        Optional<AdvertisementChannel> updatedAdvertisementChannel = this.repository.findById(newAdvertisementChannelDetails.id);

        Assertions.assertTrue(updatedAdvertisementChannel.isPresent());
        Assertions.assertEquals(newAdvertisementChannelDetails.name, updatedAdvertisementChannel.get().getName());
    }


    @Test
    public void givenExistingAdvertisementChannel_WhenDeleteRequestIsReceived_ThenAdvertisementChannelIsDeleted() {
        // GIVEN
        AdvertisementChannel existingAdvertisementChannel = createAdvertisementChannel("name 123", ChannelType.TV);

        // WHEN
        String relativePath = API_ADVERTISEMENT_CHANNELS + "/" + existingAdvertisementChannel.getId();
        this.restTemplate.delete(relativePath, existingAdvertisementChannel.getId());

        // THEN
        Optional<AdvertisementChannel> updatedAdvertisementChannel = this.repository.findById(existingAdvertisementChannel.getId());

        Assertions.assertFalse(updatedAdvertisementChannel.isPresent());
    }

    private AdvertisementChannel createAdvertisementChannel(String name, ChannelType type) {
        AdvertisementChannel expectedAdvertisementChannel = new AdvertisementChannel();
        expectedAdvertisementChannel.setName(name);
        expectedAdvertisementChannel.setType(type);

        return repository.save(expectedAdvertisementChannel);
    }
}
