package by.gago.exporter.service;

import by.gago.exporter.entity.FilesDAO;
import by.gago.exporter.repository.FilesRepo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FileParserService {

    private final FilesRepo filesRepo;

    public Map<String, List<String>> findFiles() {
        File directory = new File("\\\\srv-hyper\\БД Электронная архивная опись");
        System.out.println("export started");
        Map<String, List<String>> folderContents = new HashMap<>();

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles(File::isDirectory);
            if (files != null) {
                for (File folder : files) {
                    if (folder.getName().contains("Описи фондов №№")) {
                        exploreFolder(folder, folderContents);
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid directory path");
        }
        System.out.println("export ended");

        return folderContents;
    }


    private void exploreFolder(File folder, Map<String, List<String>> folderContents) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    exploreFolder(file, folderContents);
                } else if (!file.getName().endsWith(".xls")) {
                    String[] parts = file.getName().split("_");
                    if (parts.length >= 3) {
                        String fundNumber = parts[2];
                        String caseNumber = parts[3].replaceAll("[^0-9]", ""); // Номер дела
                        String fileName = file.getAbsolutePath();
                        String formattedOutput = String.format("%s (%s, %s)", fileName, fundNumber, caseNumber);

                        try {
                            System.out.println(formattedOutput);
                            filesRepo.save(FilesDAO.builder()
                                    .filePath(fileName)
                                    .fundNumber(fundNumber)
                                    .inventoryNumber(caseNumber)
                                    .isExported(false)
                                    .build());
                        } catch (Exception e) {
                        }
                        folderContents.computeIfAbsent(folder.getName(), k -> new ArrayList<>()).add(formattedOutput);
                    }
                }
            }
        }
    }

}
