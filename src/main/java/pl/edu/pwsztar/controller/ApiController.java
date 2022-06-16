package pl.edu.pwsztar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwsztar.domain.dto.ComandDto;
import pl.edu.pwsztar.domain.dto.CreateComandDto;
import pl.edu.pwsztar.domain.dto.UserLoginDto;
import pl.edu.pwsztar.domain.dto.UserRegistrationDto;

import pl.edu.pwsztar.service.serviceImpl.ComandService;
import pl.edu.pwsztar.service.serviceImpl.UserService;

import java.util.List;

@RestController
public class ApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);


    private final ComandService comandService;
    private final UserService userService;

    @Autowired
    public ApiController(ComandService comandService, UserService userService) {

        this.comandService = comandService;
        this.userService = userService;
    }
/*
    @CrossOrigin
    @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> login(@RequestBody UserLoginDto userLoginDto) {
        LOGGER.info("create comand: {}");
        //todo miał być dologowania



        return new ResponseEntity<>( HttpStatus.CREATED);
    }
    */

    @CrossOrigin
    @GetMapping(value = "/comands", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ComandDto>> getComands() {
        LOGGER.info("find all comands");

        List<ComandDto> comandDto = comandService.findAll();
        return new ResponseEntity<>(comandDto, HttpStatus.OK);
        //komenda dla wszystkich bez seciurity
    }





    @CrossOrigin
    @PostMapping(value = "/comands", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createComand(@RequestBody CreateComandDto createComandDto) {
        LOGGER.info("create comand: {}", createComandDto);
        comandService.addComand(createComandDto);
        //todo do seciurity

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin
    @DeleteMapping(value = "/delte/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createComand(@PathVariable("id") Long id) {
        comandService.deleteComand(id);
        //todo dododania seciurity

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin
    @PostMapping(value = "/comandupdate/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> updateComand(@PathVariable("id") Long id, @RequestBody CreateComandDto createComandDto) {
        try {
            comandService.updateComand(createComandDto, id);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin
    @PostMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> addNewUser(@RequestBody UserRegistrationDto userRegistrationDto) {
      userService.addNewUser(userRegistrationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
      //todo  to miał byc do loginu
    }




    @CrossOrigin
    @GetMapping(value = "/getNickFromId/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getUsserNameById(@PathVariable("id") Long id) {
        String name = userService.getUserNamebyId(id);
        return new ResponseEntity<>(name, HttpStatus.OK);
        //todo tomiało być do loginu

    }

    @CrossOrigin
    @GetMapping(value = "/comandForIot", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ComandDto> getComandForIot() {
        ComandDto comandDto = comandService.getComandDtoToIot();
        return new ResponseEntity<>(comandDto, HttpStatus.OK);
        //to niejest dla fronendu


    }

}




