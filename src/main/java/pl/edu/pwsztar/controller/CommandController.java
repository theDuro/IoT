package pl.edu.pwsztar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.pwsztar.domain.dto.ComandDto;
import pl.edu.pwsztar.domain.dto.CreateComandDto;
import pl.edu.pwsztar.domain.dto.UserLoginDto;
import pl.edu.pwsztar.domain.dto.UserRegistrationDto;
import pl.edu.pwsztar.domain.entity.StateOfCurrentRule;
import pl.edu.pwsztar.service.serviceImpl.ComandService;
import pl.edu.pwsztar.service.serviceImpl.LogerService;
import pl.edu.pwsztar.service.serviceImpl.UserService;

@Controller
public class CommandController {

    private final ComandService comandService;
    private final UserService userService;
    private final LogerService logerService;
    @Autowired
    public CommandController(ComandService comandService,UserService userService,LogerService logerService) {
        this.comandService = comandService;
        this.userService = userService;
        this.logerService =logerService;
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

    @GetMapping("/gui/registration")
    public String  registrationView(Model model){
        model.addAttribute("userRegistrationDto", new UserRegistrationDto());

        return "commands/registration";

    }

    @PostMapping("/gui/registration")
    public String  registration(@ModelAttribute UserRegistrationDto userRegistrationDto){
        userService.addUser(userRegistrationDto);

        return "redirect:/gui/commands";

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

    @GetMapping("/gui/logs")
    public String showLogs(Model model) {
        model.addAttribute("logs",logerService.getAllLogs());

        return "commands/logs";
    }

    @GetMapping("/gui/remove-logs")
    public String removeLogs() {
        logerService.removeLogs();
        return "redirect:/gui/adminComands";
    }

    @GetMapping("/gui/login")
    public String showLoginView(Model model) {
        model.addAttribute("userLoginDto", new UserLoginDto());

        return "commands/login";
    }

    @GetMapping("/gui/adminComands")
    public String showAdminPanel(Model model) {
        model.addAttribute("commands", comandService.findAll());

        return "commands/list_admin";
    }

    @GetMapping("/gui/users")
    public String showUsersView(Model model) {
        model.addAttribute("users", userService.getAllUsers());

        return "commands/users";
    }

    @GetMapping("/gui/users/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userService.delteUserById(id);

        return "redirect:/gui/users";
    }

    @GetMapping("/gui/device-state")
    public String showDeviceStateView(Model model) {
        model.addAttribute("stateOfCurrentRule", new StateOfCurrentRule(1L,2F, 3F,4F,5L,6));

        return "commands/device-state";
    }

}
