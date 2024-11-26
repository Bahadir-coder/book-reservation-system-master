package com.example.bookreservation.controller;

import com.example.bookreservation.model.input.ReservationDtoInput;
import com.example.bookreservation.model.output.ReservationDtoOutput;
import com.example.bookreservation.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/reservation")
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
}
