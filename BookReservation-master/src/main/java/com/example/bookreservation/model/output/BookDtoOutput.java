package com.example.bookreservation.model.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookDtoOutput {
    private List<String> authorNames;
    private List<String> authorSurnames;
    private String bookName;
    private String bookGenre;
    private String bookCode;
}
