package com.emanuelciuca.trainings.projects.advertisements.service;

import com.emanuelciuca.trainings.projects.advertisements.model.Advertisement;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementsService {

    public Advertisement getAdvertisementById(Long id) {
        Advertisement advertisement = new Advertisement();
        advertisement.setId(id);

        return advertisement;
    }
}
