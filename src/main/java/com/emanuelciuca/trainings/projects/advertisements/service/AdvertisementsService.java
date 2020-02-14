package com.emanuelciuca.trainings.projects.advertisements.service;

import com.emanuelciuca.trainings.projects.advertisements.model.Advertisement;
import com.emanuelciuca.trainings.projects.advertisements.repository.AdvertisementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AdvertisementsService {

    private final AdvertisementsRepository advertisementsRepository;

    @Autowired
    public AdvertisementsService(AdvertisementsRepository advertisementsRepository) {
        this.advertisementsRepository = advertisementsRepository;
    }

    public Optional<Advertisement> getAdvertisementById(Long id) {
        return advertisementsRepository.findById(id);
    }

    public Advertisement createAdvertisement(Advertisement advertisement) {
        return advertisementsRepository.save(advertisement);
    }

    public Advertisement updateAdvertisement(Long id, Advertisement advertisement) {
        Advertisement advertisementById = advertisementsRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);

        advertisementById.setTitle(advertisement.getTitle());
        advertisementById.setChannels(advertisement.getChannels());

        return advertisementsRepository.save(advertisement);
    }

    public void deleteAdvertisement(Long id) {
        Advertisement existingAdvertisement = advertisementsRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);

        advertisementsRepository.delete(existingAdvertisement);
    }
}
