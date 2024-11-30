package com.example.bookreservation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DeletedDto {
    private String bookName;
    private String bookGenre;
    private String bookCode;
}
