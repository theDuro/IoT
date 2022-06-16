package pl.edu.pwsztar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.pwsztar.domain.dto.ComandDto;
import pl.edu.pwsztar.domain.dto.CreateComandDto;
import pl.edu.pwsztar.service.serviceImpl.ComandService;

@Controller
public class CommandController {

    private final ComandService comandService;

    public CommandController(ComandService comandService) {
        this.comandService = comandService;
    }

    @GetMapping("/gui/commands")
    public String showCategoriesList(Model model) {
        model.addAttribute("commands", comandService.findAll());

        return "commands/list";
    }

    @GetMapping("/gui/commands/add")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("createComandDto", new CreateComandDto());

        return "commands/save";
    }

    @PostMapping("/gui/commands/save")
    public String processCategoryForm(@ModelAttribute CreateComandDto createComandDto) {

        comandService.addComand(createComandDto);

        return "redirect:/gui/commands";
    }

    @GetMapping("/gui/commands/{commandId}/edit")
    public String showEditCategoryForm(@PathVariable Long commandId, Model model) {

        ComandDto comandDto = comandService.findById(commandId);

        CreateComandDto createComandDto = new CreateComandDto();

        createComandDto.setComandId(comandDto.getComandId());
        createComandDto.setEnginePower(comandDto.getEnginePower());
        createComandDto.setLedFrequency(comandDto.getLedFrequency());
        createComandDto.setLedLimitedValue(comandDto.getLedLimitedValue());
        createComandDto.setUserId(comandDto.getUserId());
        model.addAttribute("createComandDto", createComandDto);

        return "commands/save";
    }

    @GetMapping("/gui/commands/{commandId}/delete")
    public String deleteCategory(@PathVariable Long commandId) {
        comandService.deleteComand(commandId);

        return "redirect:/gui/commands";
    }

}
