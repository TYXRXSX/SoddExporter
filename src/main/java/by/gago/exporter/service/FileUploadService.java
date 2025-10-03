package by.gago.exporter.service;

import by.gago.exporter.dto.PageResponseDto;
import by.gago.exporter.dto.SearchRequestDto;
import by.gago.exporter.entity.FilesDAO;
import by.gago.exporter.property.AuthProperty;
import by.gago.exporter.repository.FilesRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import by.gago.exporter.gateway.SoddApiClient;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final AuthProperty authProperty;
    private final SoddApiClient soddApiClient;
    private final FilesRepo filesRepo;

    private boolean uploadFromPathAndEssenceId(String essenceId, String absolutePath) throws IOException {
        Path path = Path.of(absolutePath);
        if (!Files.isRegularFile(path)) {
            throw new IllegalArgumentException("File not found or not a regular file: " + absolutePath);
        }

        String filename = path.getFileName().toString();
        String contentType = Files.probeContentType(path); // может быть null
        byte[] content = Files.readAllBytes(path);

        MultipartFile multipartFile = new MockMultipartFile(
                "file",              // имя поля формы
                filename,            // оригинальное имя
                contentType,         // content-type (может быть null)
                content              // содержимое
        );

        String authHeader = authProperty.getAuthToken();
        String result = soddApiClient.uploadFile(essenceId, multipartFile);
        if (result.contains("-")) {
            FilesDAO filesDAO = filesRepo.findByFilePath(absolutePath);
            filesDAO.setIsExported(true);
            filesRepo.save(filesDAO);
        }
        return true;
    }

    private UUID searchEssenceByFundAndCase(String fundCode, String caseCode) {
        String authHeader = authProperty.getAuthToken();
        SearchRequestDto searchRequestDto = SearchRequestDto.builder()
                .fundCode(fundCode)
                .inventoryCode(caseCode)
                .archiveCodes(Arrays.asList("42"))
                .build();
        PageResponseDto pageResponseDto = soddApiClient.searchEssence(searchRequestDto);

        return pageResponseDto.getContent()
                .stream()
                .filter(x -> x.getLevel().contains("описи"))
                .findFirst()
                .get()
                .getEssenceId();
    }

    @PostConstruct
    public void exportAll(){
        filesRepo.findAll().forEach(x->{
           String essenceId = this.searchEssenceByFundAndCase(x.getFundNumber(),x.getInventoryNumber()).toString();
            try {
                this.uploadFromPathAndEssenceId(essenceId,x.getFilePath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
