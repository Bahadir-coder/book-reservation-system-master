package com.example.bookreservation.model.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookDtoInput {
    private List<String> authorNames;
    private List<String> authorSurnames;
    private List<String> authorFinCodes;
    private String bookName;
    private String bookGenre;
    private String bookCode;
    private Double bookPrice;
    private String bookType;
}
