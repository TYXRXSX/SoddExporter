package by.gago.exporter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class EssenceDto {
    @JsonProperty("fund_code")
    private String fundCode;

    @JsonProperty("inventory_code")
    private String inventoryCode;

    @JsonProperty("file_code")
    private String fileCode; // может быть null, поэтому String

    @JsonProperty("archive_code")
    private String archiveCode;

    private String key;
    private String title;


    @JsonProperty("content")
    private String description;

    private List<Integer> years;

    private String level;

    @JsonProperty("essence_id")
    private UUID essenceId;

    @JsonProperty("parent_id")
    private UUID parentId;

    @JsonProperty("has_children")
    private boolean hasChildren;

    @JsonProperty("has_digital_objects")
    private boolean hasDigitalObjects;

    // В JSON встречаются и пустые массивы, и null — используем List<String>,
    // допускающий null.
    @JsonProperty("ekdi_list")
    private List<String> ekdiList;

    @JsonProperty("ekdi_names")
    private List<String> ekdiNames;
}
