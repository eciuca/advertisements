package com.emanuelciuca.trainings.projects.advertisements.controller;

import com.emanuelciuca.trainings.projects.advertisements.dto.AdvertisementChannelDto;
import com.emanuelciuca.trainings.projects.advertisements.dto.AdvertisementChannelMapper;
import com.emanuelciuca.trainings.projects.advertisements.exception.NotFoundException;
import com.emanuelciuca.trainings.projects.advertisements.model.AdvertisementChannel;
import com.emanuelciuca.trainings.projects.advertisements.service.AdvertisementChannelsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.emanuelciuca.trainings.projects.advertisements.controller.AdvertisementChannelsController.API_ADVERTISEMENT_CHANNELS;

@RestController
@RequestMapping(API_ADVERTISEMENT_CHANNELS)
public class AdvertisementChannelsController {

    public static final String API_ADVERTISEMENT_CHANNELS = "/api/advertisement-channels";

    private final AdvertisementChannelMapper advertisementChannelMapper;
    private final AdvertisementChannelsService advertisementChannelsService;

    public AdvertisementChannelsController(AdvertisementChannelMapper advertisementChannelMapper, AdvertisementChannelsService advertisementChannelsService) {
        this.advertisementChannelMapper = advertisementChannelMapper;
        this.advertisementChannelsService = advertisementChannelsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvertisementChannelDto> getAdvertisementById(@PathVariable Long id) {
        AdvertisementChannel advertisement = advertisementChannelsService.getAdvertisementById(id)
                .orElseThrow(NotFoundException::new);
        AdvertisementChannelDto response = advertisementChannelMapper.toDto(advertisement);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AdvertisementChannelDto> createAdvertisement(@RequestBody AdvertisementChannelDto details) {
        AdvertisementChannel advertisement = advertisementChannelMapper.toEntity(details);
        advertisement = advertisementChannelsService.createAdvertisementChannel(advertisement);
        AdvertisementChannelDto response = advertisementChannelMapper.toDto(advertisement);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateAdvertisement(@PathVariable Long id, @RequestBody AdvertisementChannelDto newDetails) {
        AdvertisementChannel advertisement = advertisementChannelMapper.toEntity(newDetails);
        advertisementChannelsService.updateAdvertisementChannel(id, advertisement);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAdvertisement(@PathVariable Long id) {
        advertisementChannelsService.deleteAdvertisementChannel(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
