package by.gago.exporter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchRequestDto {
    @JsonProperty("flag_fund_names")
    private boolean flagFundNames;

    @JsonProperty("flag_fund_old_names")
    private boolean flagFundOldNames;

    @JsonProperty("flag_docs_annotations")
    private boolean flagDocsAnnotations;

    @JsonProperty("flag_fund_founders")
    private boolean flagFundFounders;

    @JsonProperty("flag_files")
    private boolean flagFiles;

    @JsonProperty("flag_inventory")
    private boolean flagInventory;

    @JsonProperty("flag_delo")
    private boolean flagDelo;

    @JsonProperty("flag_docs")
    private boolean flagDocs;

    @JsonProperty("file_code")
    private String fileCode; // может быть пустой строкой

    @JsonProperty("fund_code")
    private String fundCode;

    @JsonProperty("inventory_code")
    private String inventoryCode;

    @JsonProperty("sentence_search")
    private String sentenceSearch;

    @JsonProperty("archive_codes")
    private List<String> archiveCodes;

    @JsonProperty("has_storage_data")
    private Boolean hasStorageData;

    @JsonProperty("ekdi_list")
    private List<String> ekdiList;

    @JsonProperty("year_from")
    private Integer yearFrom;

    @JsonProperty("year_to")
    private Integer yearTo;

    @JsonProperty("page_size")
    private int pageSize;

    @JsonProperty("page")
    private int page;

    @JsonProperty("flag_search_request")
    private boolean flagSearchRequest;
}
