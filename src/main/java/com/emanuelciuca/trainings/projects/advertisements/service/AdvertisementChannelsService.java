package com.emanuelciuca.trainings.projects.advertisements.service;

import com.emanuelciuca.trainings.projects.advertisements.model.AdvertisementChannel;
import com.emanuelciuca.trainings.projects.advertisements.repository.AdvertisementChannelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AdvertisementChannelsService {

    private final AdvertisementChannelsRepository advertisementChannelsRepository;

    @Autowired
    public AdvertisementChannelsService(AdvertisementChannelsRepository advertisementChannelsRepository) {
        this.advertisementChannelsRepository = advertisementChannelsRepository;
    }

    public Optional<AdvertisementChannel> getAdvertisementById(Long id) {
        return advertisementChannelsRepository.findById(id);
    }

    public AdvertisementChannel createAdvertisementChannel(AdvertisementChannel advertisement) {
        return advertisementChannelsRepository.save(advertisement);
    }

    public AdvertisementChannel updateAdvertisementChannel(Long id, AdvertisementChannel advertisement) {
        advertisementChannelsRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);

        advertisement.setId(id);
        return advertisementChannelsRepository.save(advertisement);
    }

    public void deleteAdvertisementChannel(Long id) {
        AdvertisementChannel existingAdvertisementChannel = advertisementChannelsRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);

        advertisementChannelsRepository.delete(existingAdvertisementChannel);
    }
}
