package com.emanuelciuca.trainings.projects.advertisements.dto;

import java.util.Objects;

public class AdvertisementDto {

    public Long id;

    private AdvertisementDto() { }

    public static AdvertisementDto advertisementDto() {
        return new AdvertisementDto();
    }

    public AdvertisementDto withId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdvertisementDto that = (AdvertisementDto) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
