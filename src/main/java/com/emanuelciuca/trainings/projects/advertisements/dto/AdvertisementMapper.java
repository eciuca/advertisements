package com.emanuelciuca.trainings.projects.advertisements.dto;

import com.emanuelciuca.trainings.projects.advertisements.model.Advertisement;
import com.emanuelciuca.trainings.projects.advertisements.model.AdvertisementChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AdvertisementMapper {

    private final AdvertisementChannelMapper advertisementChannelMapper;

    @Autowired
    public AdvertisementMapper(AdvertisementChannelMapper advertisementChannelMapper) {
        this.advertisementChannelMapper = advertisementChannelMapper;
    }

    public Advertisement toEntity(AdvertisementDto advertisementDto) {
        Set<AdvertisementChannel> advertisementChannels = Optional
                .ofNullable(advertisementDto.channels)
                .orElse(Collections.emptySet())
                .stream()
                .map(advertisementChannelMapper::toEntity)
                .collect(Collectors.toSet());

        Advertisement advertisement = new Advertisement();
        advertisement.setId(advertisementDto.id);
        advertisement.setTitle(advertisementDto.title);
        advertisement.setChannels(advertisementChannels);

        return advertisement;
    }

    public AdvertisementDto toDto(Advertisement advertisement) {
        Set<AdvertisementChannelDto> advertisementChannels = Optional
                .ofNullable(advertisement.getChannels())
                .orElse(Collections.emptySet())
                .stream()
                .map(advertisementChannelMapper::toDto)
                .collect(Collectors.toSet());

        return AdvertisementDto.advertisementDto()
                .withId(advertisement.getId())
                .withTitle(advertisement.getTitle())
                .withChannels(advertisementChannels);
    }
}
