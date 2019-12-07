package com.emanuelciuca.trainings.projects.advertisements.controller;

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

    @GetMapping("/{id}")
    public ResponseEntity<String> getAdvertisementById(@PathVariable Long id) {
        String advertisement = "advertisement: " + id;

        return new ResponseEntity<>(advertisement, HttpStatus.OK);
    }
}
