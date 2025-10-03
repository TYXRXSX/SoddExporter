package by.gago.exporter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PageResponseDto {
    private int number;
    private int size;

    @JsonProperty("total_pages")
    private int totalPages;

    @JsonProperty("total_elements")
    private int totalElements;

    private List<EssenceDto> content;

    private boolean first;

    @JsonProperty("number_of_elements")
    private int numberOfElements;

    private boolean last;
}

