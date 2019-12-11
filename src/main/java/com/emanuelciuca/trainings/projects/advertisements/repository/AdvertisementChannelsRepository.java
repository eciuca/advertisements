package com.emanuelciuca.trainings.projects.advertisements.repository;

import com.emanuelciuca.trainings.projects.advertisements.model.AdvertisementChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementChannelsRepository extends JpaRepository<AdvertisementChannel, Long> {
}
