package com.emanuelciuca.trainings.projects.advertisements.repository;

import com.emanuelciuca.trainings.projects.advertisements.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementsRepository extends JpaRepository<Advertisement, Long> {
}
