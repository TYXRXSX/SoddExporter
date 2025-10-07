package by.gago.exporter.service;

import by.gago.exporter.entity.FilesDAO;
import by.gago.exporter.repository.FilesRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {

    private final FilesRepo filesRepo;

    public List<FilesDAO> filesList(){
        return filesRepo.findAll();
    }

}
