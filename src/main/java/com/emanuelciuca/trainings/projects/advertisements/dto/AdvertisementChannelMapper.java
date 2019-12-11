package com.emanuelciuca.trainings.projects.advertisements.dto;

import com.emanuelciuca.trainings.projects.advertisements.model.AdvertisementChannel;
import com.emanuelciuca.trainings.projects.advertisements.model.ChannelType;
import org.springframework.stereotype.Component;

@Component
public class AdvertisementChannelMapper {

    public AdvertisementChannel toEntity(AdvertisementChannelDto advertisementDto) {
        AdvertisementChannel advertisementChannel = new AdvertisementChannel();
        advertisementChannel.setId(advertisementDto.id);
        advertisementChannel.setName(advertisementDto.name);
        advertisementChannel.setType(ChannelType.valueOf(advertisementDto.type));

        return advertisementChannel;
    }

    public AdvertisementChannelDto toDto(AdvertisementChannel advertisementChannel) {
        return AdvertisementChannelDto.advertisementChannelDto()
                .withId(advertisementChannel.getId())
                .withName(advertisementChannel.getName())
                .withType(advertisementChannel.getType().name());
    }
}
