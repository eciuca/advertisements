package com.emanuelciuca.trainings.projects.advertisements.dto;

import com.emanuelciuca.trainings.projects.advertisements.model.Advertisement;
import org.springframework.stereotype.Component;

@Component
public class AdvertisementMapper {

    public Advertisement toEntity(AdvertisementDto advertisementDto) {
        Advertisement advertisement = new Advertisement();
        advertisement.setId(advertisementDto.id);
        advertisement.setTitle(advertisementDto.title);

        return advertisement;
    }

    public AdvertisementDto toDto(Advertisement advertisement) {
        return AdvertisementDto.advertisementDto()
                .withId(advertisement.getId())
                .withTitle(advertisement.getTitle());
    }
}
