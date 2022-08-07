package pl.edu.pwsztar.domain;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pwsztar.domain.dto.ComandDto;
import pl.edu.pwsztar.domain.dto.CreateComandDto;
import pl.edu.pwsztar.domain.dto.UserToShowDto;
import pl.edu.pwsztar.domain.entity.Comand;
import pl.edu.pwsztar.domain.entity.StateOfCurrentRule;
import pl.edu.pwsztar.service.serviceImpl.ComandService;
import pl.edu.pwsztar.service.serviceImpl.LogerService;
import pl.edu.pwsztar.service.serviceImpl.RedisComandService;
import pl.edu.pwsztar.service.serviceImpl.UserService;

import java.util.List;
import java.util.Optional;

public class ComandFasade {
    private final ComandService comandService;
    private final UserService userService;
    private final RedisComandService redisComandService;
    private final LogerService logerService;


    @Autowired
    public ComandFasade(
            ComandService comandService
            ,UserService userService
            ,RedisComandService redisComandService
            ,LogerService logerService
            , StateOfCurrentRule mapComandDtoToStateOfCurrentRule
    ) {

        this.comandService = comandService;
        this.userService = userService;
        this.redisComandService = redisComandService;
        this.logerService = logerService;
    }
    public List<ComandDto> getAllComands(){
        return comandService.findAll();
    }

    public void createComand(CreateComandDto createComandDto){
        Comand comand = comandService.addComand(createComandDto);
        logerService.saveLog("add comand" + comand.toString());


    }

    public void delteComand(Long id){
        logerService.saveLog("delte coomand with id: "+id);
        comandService.deleteComand(id);
    }

    public void updateComand(Long id,Integer expire,CreateComandDto createComandDto){
        logerService.saveLog("update comand id:"+ id);
        Comand comand =comandService.updateComand(createComandDto, id);
    }

    public ComandDto activateAndGetcomandForIot(){

        ComandDto comandDto = comandService.getComandDtoToIot();
        return comandService.getComandDtoToIot();
    }

    public StateOfCurrentRule getCurentRoleWithExpireTime(){
        return redisComandService.getCurentRoleWithExpireTime();
    }
    public List<UserToShowDto> getUsersToShow(){
        logerService.saveLog("get users list");
        return userService.getAllUsers();
    }
    public void delteUserById(long id){
        logerService.saveLog("delte user by id: "+ id);
        userService.delteUserById(id);
    }

    public List<String> getAllLogs(){
        return logerService.getAllLogs();
    }








}
