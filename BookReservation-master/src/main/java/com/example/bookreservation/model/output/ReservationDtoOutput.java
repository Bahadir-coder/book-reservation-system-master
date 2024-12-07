package com.example.bookreservation.model.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReservationDtoOutput {
    private String userFinCode;
    private String userName;
    private String userSurname;
    private String bookName;
    private String bookCode;
    private String reservationType;
    private String reservationCode;
    private ZonedDateTime createdDate;
    private ZonedDateTime expiryDate;
}
