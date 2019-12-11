package com.emanuelciuca.trainings.projects.advertisements.controller;

import com.emanuelciuca.trainings.projects.advertisements.dto.AdvertisementDto;
import com.emanuelciuca.trainings.projects.advertisements.dto.AdvertisementMapper;
import com.emanuelciuca.trainings.projects.advertisements.exception.NotFoundException;
import com.emanuelciuca.trainings.projects.advertisements.model.Advertisement;
import com.emanuelciuca.trainings.projects.advertisements.service.AdvertisementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.emanuelciuca.trainings.projects.advertisements.controller.AdvertisementsController.API_ADVERTISEMENTS;

@RestController
@RequestMapping(API_ADVERTISEMENTS)
public class AdvertisementsController {

    public static final String API_ADVERTISEMENTS = "/api/advertisements";

    private final AdvertisementMapper advertisementMapper;
    private final AdvertisementsService advertisementService;

    @Autowired
    public AdvertisementsController(AdvertisementMapper advertisementMapper, AdvertisementsService advertisementService) {
        this.advertisementMapper = advertisementMapper;
        this.advertisementService = advertisementService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvertisementDto> getAdvertisementById(@PathVariable Long id) {
        Advertisement advertisement = advertisementService.getAdvertisementById(id)
                .orElseThrow(NotFoundException::new);
        AdvertisementDto response = advertisementMapper.toDto(advertisement);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AdvertisementDto> createAdvertisement(@RequestBody AdvertisementDto details) {
        Advertisement advertisement = advertisementMapper.toEntity(details);
        advertisement = advertisementService.createAdvertisement(advertisement);
        AdvertisementDto response = advertisementMapper.toDto(advertisement);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateAdvertisement(@PathVariable Long id, @RequestBody AdvertisementDto newDetails) {
        Advertisement advertisement = advertisementMapper.toEntity(newDetails);
        advertisementService.updateAdvertisement(id, advertisement);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAdvertisement(@PathVariable Long id) {
        advertisementService.deleteAdvertisement(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
