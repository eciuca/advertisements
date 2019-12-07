package com.emanuelciuca.trainings.projects.advertisements.dto;

import com.emanuelciuca.trainings.projects.advertisements.UnitTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdvertisementDtoTest extends UnitTest {

    @Test
    public void givenTwoEqualAdvertisements_whenCompared_theResultIsTrue() {
        AdvertisementDto advertisementDto1 = AdvertisementDto
                .advertisementDto()
                .withId(1L)
                .withTitle("title 1");

        AdvertisementDto advertisementDto2 = AdvertisementDto
                .advertisementDto()
                .withId(1L)
                .withTitle("title 1");

        boolean comparisonResult = advertisementDto1.equals(advertisementDto2);

        assertTrue(comparisonResult);
    }

    @Test
    public void givenTwoAdvertisementsWithDifferentIds_whenCompared_theResultIsFalse() {
        AdvertisementDto advertisementDto1 = AdvertisementDto
                .advertisementDto()
                .withId(1L);

        AdvertisementDto advertisementDto2 = AdvertisementDto
                .advertisementDto()
                .withId(2L);

        boolean comparisonResult = advertisementDto1.equals(advertisementDto2);

        assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoAdvertisementsWithDifferentTitles_whenCompared_theResultIsFalse() {
        AdvertisementDto advertisementDto1 = AdvertisementDto
                .advertisementDto()
                .withTitle("title 1");

        AdvertisementDto advertisementDto2 = AdvertisementDto
                .advertisementDto()
                .withTitle("title 2");

        boolean comparisonResult = advertisementDto1.equals(advertisementDto2);

        assertFalse(comparisonResult);
    }
}
