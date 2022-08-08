package pl.edu.pwsztar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwsztar.domain.ComandFasade;
import pl.edu.pwsztar.domain.dto.ComandDto;
import pl.edu.pwsztar.domain.dto.CreateComandDto;
import pl.edu.pwsztar.domain.dto.UserLoginDto;
import pl.edu.pwsztar.domain.dto.UserRegistrationDto;
import pl.edu.pwsztar.domain.entity.StateOfCurrentRule;
import pl.edu.pwsztar.service.serviceImpl.ComandService;
import pl.edu.pwsztar.service.serviceImpl.LogerService;
import pl.edu.pwsztar.service.serviceImpl.RedisComandService;
import pl.edu.pwsztar.service.serviceImpl.UserService;

import java.util.List;

@Controller
public class CommandController {

    private final ComandService comandService;
    private final UserService userService;
    private final LogerService logerService;
    private final RedisComandService redisComandService;
    private final  ComandFasade comandFasade;
    @Autowired
    RedisTemplate template;

    @Autowired
    public CommandController(ComandService comandService,UserService userService,LogerService logerService,RedisComandService redisComandService,ComandFasade comandFasade) {
        this.comandService = comandService;
        this.userService = userService;
        this.logerService =logerService;
        this.redisComandService = redisComandService;
        this.comandFasade = comandFasade;

    }

    @GetMapping("/gui/commands")
    public String showCategoriesList(Model model) {
        //model.addAttribute("commands", comandService.findAll());
        model.addAttribute("commands", comandFasade.getAllComands());

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

        comandFasade.createComand(createComandDto);

        return "redirect:/gui/commands";
    }

    @GetMapping("/gui/commands/{commandId}/edit")
    public String showEditCategoryForm(@PathVariable Long commandId, @RequestParam String firstName, Model model) {

        ComandDto comandDto =  comandFasade.findComandById(commandId);

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
        comandFasade.delteComand(commandId);
        return "redirect:/gui/commands";
    }

    @GetMapping("/gui/logs")
    public String showLogs(Model model) {
        model.addAttribute("logs",comandFasade.getAllLogs());

        return "commands/logs";
    }

    @GetMapping("/gui/remove-logs")
    public String removeLogs() {
        comandFasade.delteLogs();
        return "redirect:/gui/adminComands";
    }

    @GetMapping("/gui/login")
    public String showLoginView(Model model) {
        model.addAttribute("userLoginDto", new UserLoginDto());

        return "commands/login";
    }

    @PostMapping("/gui/login")
    public String receiveLoginView(@ModelAttribute UserLoginDto userLoginDto) {
        return "commands/login";
    }

    @GetMapping("/gui/adminComands")
    public String showAdminPanel(Model model) {
        model.addAttribute("commands", comandFasade.getAllComands());

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
        Object o = template.opsForValue().get( "ValueOfActualIot");
        System.out.print(o+" Działa /n \n \n");
      //  model.addAttribute("stateOfCurrentRule", redisComandService.getCurentRoleWithExpireTime());
        model.addAttribute("stateOfCurrentRule", o);
        //todo zmienić z Object na
        return "commands/device-state";
    }
    @CrossOrigin
    @GetMapping(value = "/IoT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ComandDto> getComandForIot() {

        ComandDto comandDto = comandFasade.activateAndGetcomandForIot();
        return new ResponseEntity<>(comandDto, HttpStatus.OK);
    }

}
