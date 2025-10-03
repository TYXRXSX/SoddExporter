package by.gago.exporter.gateway;

import by.gago.exporter.dto.PageResponseDto;
import by.gago.exporter.dto.SearchRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "file-upload-client", url = "http://127.0.0.1:8085")
public interface SoddApiClient {

    @PostMapping(value = "/api/v1/search/digital_object/inventory/{id}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadFile(@PathVariable("id") String id,
                      @RequestPart("file") MultipartFile file,
                      @RequestHeader("Authorization") String authHeader);

    @PostMapping(value = "/api/v1/search", consumes = "application/json", produces = "application/json")
    PageResponseDto searchEssence(
            @RequestBody SearchRequestDto request
    );


    //@RequestHeader("Authorization") String authHeader
}
