package com.emanuelciuca.trainings.projects.advertisements.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

import static com.emanuelciuca.trainings.projects.advertisements.model.Advertisement.ADVERTISEMENTS_TABLE;

@Entity
@Table(name = ADVERTISEMENTS_TABLE)
public class Advertisement {

    public static final String ADVERTISEMENTS_TABLE = "ADVERTISEMENTS";

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotNull
    private String title;

    @ManyToMany
    @JoinTable(
            name = "ADVERTISEMENTS_ADVERTISEMENT_CHANNELS",
            joinColumns = @JoinColumn(name = "advertisement_id"),
            inverseJoinColumns = @JoinColumn(name = "advertisement_channel_id"))
    private Set<AdvertisementChannel> channels;

    public Long getId() {
        return id;
    }

    public Advertisement setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Advertisement setTitle(String title) {
        this.title = title;
        return this;
    }

    public Set<AdvertisementChannel> getChannels() {
        return channels;
    }

    public Advertisement setChannels(Set<AdvertisementChannel> channels) {
        this.channels = channels;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertisement that = (Advertisement) o;
        return id.equals(that.id) &&
                title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
