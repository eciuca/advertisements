package com.emanuelciuca.trainings.projects.advertisements.dto;

import com.emanuelciuca.trainings.projects.advertisements.UnitTest;
import com.emanuelciuca.trainings.projects.advertisements.model.Advertisement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdvertisementMapperTest extends UnitTest {

    private AdvertisementMapper mapper;

    @BeforeEach
    public void before() {
        AdvertisementChannelMapper advertisementChannelMapper = new AdvertisementChannelMapper();
        mapper = new AdvertisementMapper(advertisementChannelMapper);
    }

    @Test
    void givenAdvertisementDto_whenToEntity_ThenAdvertisementIsCreatedCorrectly() {
        // GIVEN
        long expectedId = 123L;
        String expectedTitle = "title";
        AdvertisementDto expected = AdvertisementDto.advertisementDto()
                .withId(expectedId)
                .withTitle(expectedTitle);

        // WHEN
        Advertisement actual = mapper.toEntity(expected);

        // THEN
        assertThat(actual.getId()).isEqualTo(expectedId);
        assertThat(actual.getTitle()).isEqualTo(expectedTitle);
    }

    @Test
    void givenAdvertisement_whenToDto_ThenAdvertisementDtoIsCreatedCorrectly() {
        // GIVEN
        long expectedId = 123L;
        String expectedTitle = "title";

        Advertisement expected = new Advertisement();
        expected.setId(expectedId);
        expected.setTitle(expectedTitle);

        // WHEN
        AdvertisementDto actual = mapper.toDto(expected);

        // THEN
        assertThat(actual.id).isEqualTo(expectedId);
        assertThat(actual.title).isEqualTo(expectedTitle);
    }

}
