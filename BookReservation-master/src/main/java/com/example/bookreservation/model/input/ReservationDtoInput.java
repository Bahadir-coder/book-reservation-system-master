package com.example.bookreservation.model.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReservationDtoInput {
    private String userFinCode;
    private String bookCode;
    private String reservationType;
    private String reservationCode;
    private ZonedDateTime createdDate;
    private ZonedDateTime expiryDate;
}
