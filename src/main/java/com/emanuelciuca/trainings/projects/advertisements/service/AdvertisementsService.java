package com.emanuelciuca.trainings.projects.advertisements.service;

import com.emanuelciuca.trainings.projects.advertisements.model.Advertisement;
import com.emanuelciuca.trainings.projects.advertisements.repository.AdvertisementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdvertisementsService {

    private final AdvertisementsRepository advertisementsRepository;

    @Autowired
    public AdvertisementsService(AdvertisementsRepository advertisementsRepository) {
        this.advertisementsRepository = advertisementsRepository;
    }

    public Optional<Advertisement> getAdvertisementById(Long id) {
        return advertisementsRepository.findById(id);
    }
}
