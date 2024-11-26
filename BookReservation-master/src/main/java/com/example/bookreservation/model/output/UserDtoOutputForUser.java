package com.example.bookreservation.model.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDtoOutputForUser {
    private Integer userID;
    private String userName;
    private String userSurname;
    private String userFinCode;
    private String userTel;
    private String userEmail;

}
