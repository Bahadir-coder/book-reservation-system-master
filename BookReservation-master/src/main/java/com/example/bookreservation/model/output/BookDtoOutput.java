package com.example.bookreservation.model.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookDtoOutput {
    private String authorName;
    private String authorSurname;
    private String bookName;
    private String bookGenre;
    private String bookCode;
}
