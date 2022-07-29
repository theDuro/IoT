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

import pl.edu.pwsztar.domain.entity.Comand;
import pl.edu.pwsztar.domain.entity.RuleWithTime;
import pl.edu.pwsztar.domain.entity.StateOfCurrentRule;
import pl.edu.pwsztar.domain.entity.User_;
import pl.edu.pwsztar.service.serviceImpl.ComandService;
import pl.edu.pwsztar.service.serviceImpl.RedisComandService;
import pl.edu.pwsztar.service.serviceImpl.UserService;

import java.util.List;

@RestController
public class ApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);



    private final ComandService comandService;
    private final UserService userService;
    private final RedisComandService redisComandService;

    @Autowired
    public ApiController(ComandService comandService, UserService userService,RedisComandService redisComandService) {

        this.comandService = comandService;
        this.userService = userService;
        this.redisComandService = redisComandService;
    }


    @CrossOrigin
    @GetMapping(value = "/comands", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ComandDto>> getComands() {
        LOGGER.info("find all comands");

        List<ComandDto> comandDto = comandService.findAll();
        return new ResponseEntity<>(comandDto, HttpStatus.OK);

    }





    @CrossOrigin
    @PostMapping(value = "/comands/{expire}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createComand(@RequestBody CreateComandDto createComandDto,@PathVariable("expire") Integer expire) {
        LOGGER.info("create comand: {}", createComandDto);
         Comand comand = comandService.addComand(createComandDto);
         redisComandService.addRedisComand(comand,expire);
    //todo to test

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @CrossOrigin
    @GetMapping(value = "/redis",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<RuleWithTime>> redis(){
        redisComandService.redisGetAllComands();


        return new ResponseEntity<>(redisComandService.redisGetAllComands(),HttpStatus.OK);
    }


    @CrossOrigin
    @PostMapping(value = "/redisComand",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createRedisComand(@RequestBody RuleWithTime ruleWithTime){
        redisComandService.addRedisComand(ruleWithTime);
        //todo schoud be not permited
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @CrossOrigin
    @DeleteMapping(value = "/delte/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createComand(@PathVariable("id") Long id) {
        comandService.deleteComand(id);
        redisComandService.delteById(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin
    @PostMapping(value = "/comandupdate/{id}/{expire}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> updateComand(@PathVariable("id") Long id,@PathVariable("expire") Integer expire, @RequestBody CreateComandDto createComandDto) {
        try {
            Comand comand =comandService.updateComand(createComandDto, id);
            redisComandService.updateRedisComand(comand,expire);


        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }





    @CrossOrigin
    @GetMapping(value = "/getNickFromId/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getUsserNameById(@PathVariable("id") Long id) {
        String name = userService.getUserNamebyId(id);//todo not needet
        return new ResponseEntity<>(name, HttpStatus.OK);
        //todo tomiało być do loginu

    }

    @CrossOrigin
    @GetMapping(value = "/comandForIot", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ComandDto> getComandForIot() {
        ComandDto comandDto = comandService.getComandDtoToIot();
        redisComandService.activateRedisValueExpire(comandDto.getComandId());
        return new ResponseEntity<>(comandDto, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/IoTRule", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<StateOfCurrentRule> getRuleActualValue() {

        return new ResponseEntity(redisComandService.getCurentRoleWithExpireTime(), HttpStatus.OK);
    }



}




