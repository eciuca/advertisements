package com.emanuelciuca.trainings.projects.advertisements.dto;

import java.util.Objects;
import java.util.Set;

public class AdvertisementDto {

    public Long id;
    public String title;
    public Set<AdvertisementChannelDto> channels;

    private AdvertisementDto() {
    }

    public static AdvertisementDto advertisementDto() {
        return new AdvertisementDto();
    }

    public AdvertisementDto withId(Long id) {
        this.id = id;
        return this;
    }

    public AdvertisementDto withTitle(String title) {
        this.title = title;
        return this;
    }

    public AdvertisementDto withChannels(Set<AdvertisementChannelDto> channels) {
        this.channels = channels;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdvertisementDto that = (AdvertisementDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
