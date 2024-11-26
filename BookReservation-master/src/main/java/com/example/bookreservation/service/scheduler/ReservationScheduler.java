package com.example.bookreservation.service.scheduler;

import com.example.bookreservation.service.ReservationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReservationScheduler {

    private final ReservationService reservationService;

    public ReservationScheduler(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Scheduled(fixedRate = 360000)
    public void scheduleReservationDeletion() {
        reservationService.deleteReservationAutomatic();
    }
}