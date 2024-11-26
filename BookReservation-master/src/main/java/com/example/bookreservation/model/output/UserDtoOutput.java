package com.example.bookreservation.model.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDtoOutput {
    private String userName;
    private String userSurname;
    private String userEmail;
}
