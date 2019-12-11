package com.emanuelciuca.trainings.projects.advertisements.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

import static com.emanuelciuca.trainings.projects.advertisements.model.AdvertisementChannel.ADVERTISEMENT_CHANNELS_TABLE;

@Entity
@Table(name = ADVERTISEMENT_CHANNELS_TABLE)
public class AdvertisementChannel {

    public static final String ADVERTISEMENT_CHANNELS_TABLE = "ADVERTISEMENT_CHANNELS";

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private ChannelType type;

    public Long getId() {
        return id;
    }

    public AdvertisementChannel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AdvertisementChannel setName(String name) {
        this.name = name;
        return this;
    }

    public ChannelType getType() {
        return type;
    }

    public AdvertisementChannel setType(ChannelType type) {
        this.type = type;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdvertisementChannel that = (AdvertisementChannel) o;
        return Objects.equals(id, that.id) &&
                name.equals(that.name) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type);
    }
}
