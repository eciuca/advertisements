package com.emanuelciuca.trainings.projects.advertisements.controller;

import com.emanuelciuca.trainings.projects.advertisements.model.Advertisement;
import com.emanuelciuca.trainings.projects.advertisements.service.AdvertisementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> getAdvertisementById(@PathVariable Long id) {
        Advertisement advertisement = advertisementService.getAdvertisementById(id);

        String response = "advertisement: " + advertisement.getId();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}