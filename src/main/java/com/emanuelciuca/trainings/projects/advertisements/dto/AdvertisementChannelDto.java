package com.emanuelciuca.trainings.projects.advertisements.dto;

import java.util.Objects;

public class AdvertisementChannelDto {

    public Long id;
    public String name;
    public String type;

    private AdvertisementChannelDto() {
    }

    public static AdvertisementChannelDto advertisementChannelDto() {
        return new AdvertisementChannelDto();
    };

    public AdvertisementChannelDto withId(Long id) {
        this.id = id;
        return this;
    }

    public AdvertisementChannelDto withName(String name) {
        this.name = name;
        return this;
    }

    public AdvertisementChannelDto withType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdvertisementChannelDto that = (AdvertisementChannelDto) o;
        return Objects.equals(id, that.id) &&
                name.equals(that.name) &&
                type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type);
    }
}
