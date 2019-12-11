package com.emanuelciuca.trainings.projects.advertisements.dto;

import com.emanuelciuca.trainings.projects.advertisements.UnitTest;
import com.emanuelciuca.trainings.projects.advertisements.model.Advertisement;
import com.emanuelciuca.trainings.projects.advertisements.model.AdvertisementChannel;
import com.emanuelciuca.trainings.projects.advertisements.model.ChannelType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdvertisementChannelMapperTest extends UnitTest {

    private final AdvertisementChannelMapper mapper = new AdvertisementChannelMapper();

    @Test
    void givenAdvertisementChannelDto_whenToEntity_ThenAdvertisementChannelIsCreatedCorrectly() {
        // GIVEN
        long expectedId = 123L;
        String expectedName = "title";
        ChannelType expectedType = ChannelType.TV;

        AdvertisementChannelDto expected = AdvertisementChannelDto.advertisementChannelDto()
                .withId(expectedId)
                .withName(expectedName)
                .withType(expectedType.name());

        // WHEN
        AdvertisementChannel actual = mapper.toEntity(expected);

        // THEN
        assertThat(actual.getId()).isEqualTo(expectedId);
        assertThat(actual.getName()).isEqualTo(expectedName);
        assertThat(actual.getType()).isEqualTo(expectedType);
    }

    @Test
    void givenAdvertisement_whenToDto_ThenAdvertisementChannelDtoIsCreatedCorrectly() {
        // GIVEN
        long expectedId = 123L;
        String expectedName = "title";
        ChannelType expectedType = ChannelType.TV;

        AdvertisementChannel expected = new AdvertisementChannel();
        expected.setId(expectedId);
        expected.setName(expectedName);
        expected.setType(expectedType);

        // WHEN
        AdvertisementChannelDto actual = mapper.toDto(expected);

        // THEN
        assertThat(actual.id).isEqualTo(expectedId);
        assertThat(actual.name).isEqualTo(expectedName);
        assertThat(actual.type).isEqualTo(expectedType.name());
    }

}
