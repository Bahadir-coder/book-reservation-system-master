package com.example.bookreservation.model.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookDtoInput {
    private String authorName;
    private String authorSurname;
    private String authorFinCode;
    private String bookName;
    private String bookGenre;
    private String bookCode;
}
