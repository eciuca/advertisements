package com.emanuelciuca.trainings.projects.advertisements.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Objects;

import static com.emanuelciuca.trainings.projects.advertisements.model.Advertisement.ADVERTISEMENTS_TABLE;

@Entity
@Table(name = ADVERTISEMENTS_TABLE)
public class Advertisement {

    public static final String ADVERTISEMENTS_TABLE = "ADVERTISEMENTS";

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public Advertisement setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertisement that = (Advertisement) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
