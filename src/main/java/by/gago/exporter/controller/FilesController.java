package by.gago.exporter.controller;

import by.gago.exporter.entity.FilesDAO;
import by.gago.exporter.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FilesController {

    private final FileService fileService;


    @GetMapping("/")
    public String getFiles(Model model){
        List<FilesDAO> filesDAOS = fileService.getAllFiles();
        model.addAttribute("items", filesDAOS);
        return "main";
    }
}
