package com.emanuelciuca.trainings.projects.advertisements.controller;

import com.emanuelciuca.trainings.projects.advertisements.dto.AdvertisementDto;
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

    private final AdvertisementsService advertisementService;

    @Autowired
    public AdvertisementsController(AdvertisementsService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvertisementDto> getAdvertisementById(@PathVariable Long id) {
        Advertisement advertisement = advertisementService.getAdvertisementById(id)
                .orElseThrow(NotFoundException::new);

        AdvertisementDto response =
                AdvertisementDto.advertisementDto()
                        .withId(advertisement.getId())
                        .withTitle(advertisement.getTitle());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AdvertisementDto> createAdvertisement(@RequestBody AdvertisementDto details) {
        Advertisement advertisement = new Advertisement();
        advertisement.setTitle(details.title);

        advertisement = advertisementService.createAdvertisement(advertisement);

        AdvertisementDto response = AdvertisementDto.advertisementDto()
                .withId(advertisement.getId())
                .withTitle(advertisement.getTitle());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
