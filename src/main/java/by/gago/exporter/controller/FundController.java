package by.gago.exporter.controller;

import by.gago.exporter.service.FileParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class FundController {

    private final FileParserService fileSearchService;


    @GetMapping("/files")
    public String getFiles(Model model){
        Map<String, List<String>> items = fileSearchService.findFiles();
        model.addAttribute("items", items);
        return "main"; // имя HTML-шаблона
    }
}
