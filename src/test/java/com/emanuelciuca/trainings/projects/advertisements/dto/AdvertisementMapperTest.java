package com.emanuelciuca.trainings.projects.advertisements.dto;

import com.emanuelciuca.trainings.projects.advertisements.UnitTest;
import com.emanuelciuca.trainings.projects.advertisements.model.Advertisement;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdvertisementMapperTest extends UnitTest {

    private final AdvertisementMapper mapper = new AdvertisementMapper();

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

}
