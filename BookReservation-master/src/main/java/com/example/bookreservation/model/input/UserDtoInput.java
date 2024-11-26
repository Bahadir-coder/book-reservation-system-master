package com.example.bookreservation.model.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDtoInput {
    private String userName;
    private String userSurname;
    private String userFinCode;
    private String userTel;
    private String userEmail;
}
