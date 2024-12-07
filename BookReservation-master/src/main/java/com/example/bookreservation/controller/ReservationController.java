package com.example.bookreservation.controller;

import com.example.bookreservation.model.input.ReservationDtoInput;
import com.example.bookreservation.model.output.ReservationDtoOutput;
import com.example.bookreservation.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/find/all")
    public List<ReservationDtoOutput> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/find/by/{reservationCode}")
    public ReservationDtoOutput findByCode(@PathVariable String reservationCode) {
        return reservationService.findByCode(reservationCode);
    }

    @PostMapping("/save")
    public void saveReservation(@RequestBody ReservationDtoInput reservationDtoInput) {
        reservationService.saveReservation(reservationDtoInput);
    }

    @DeleteMapping("/delete/manual")
    public void deleteManual(@RequestParam String reservationCode) {
        reservationService.deleteManual(reservationCode);
    }

    @GetMapping("/get/greater/than/by/{createdDate}")
    public List<ReservationDtoOutput> getGreaterThanByCreatedDate(@PathVariable ZonedDateTime createdDate) {
        return reservationService.getGreaterThanByCreatedDate(createdDate);
    }

    @GetMapping("/get/greater/than/by/expiryDate")
    public List<ReservationDtoOutput> getGreaterThanByExpiryDate(@RequestParam ZonedDateTime expiryDate) {
        return reservationService.getGreaterThanByExpiryDate(expiryDate);
    }
}
