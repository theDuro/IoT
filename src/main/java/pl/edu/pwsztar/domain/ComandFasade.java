package pl.edu.pwsztar.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.domain.dto.ComandDto;
import pl.edu.pwsztar.domain.dto.CreateComandDto;
import pl.edu.pwsztar.domain.dto.UserToShowDto;
import pl.edu.pwsztar.domain.entity.Comand;
import pl.edu.pwsztar.domain.entity.StateOfCurrentRule;
import pl.edu.pwsztar.domain.mapper.StateOfCurendRuleToComandDtoMapper;
import pl.edu.pwsztar.service.serviceImpl.ComandService;
import pl.edu.pwsztar.service.serviceImpl.LogerService;
import pl.edu.pwsztar.service.serviceImpl.RedisComandService;
import pl.edu.pwsztar.service.serviceImpl.UserService;

import java.util.List;
import java.util.Optional;
@Service
public class ComandFasade {
    private final ComandService comandService;
    private final RedisComandService redisComandService;
    private final LogerService logerService;
    private final StateOfCurendRuleToComandDtoMapper stateOfCurendRuleToComandDtoMapper;


    @Autowired
    public ComandFasade(
            ComandService comandService
            ,RedisComandService redisComandService
            ,LogerService logerService
            ,StateOfCurendRuleToComandDtoMapper stateOfCurendRuleToComandDtoMapper
    ) {

        this.comandService = comandService;
        this.redisComandService = redisComandService;
        this.stateOfCurendRuleToComandDtoMapper = stateOfCurendRuleToComandDtoMapper;
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

    public void updateComand(CreateComandDto createComandDto,Long id){
        logerService.saveLog("update comand id:"+ id);
        Comand comand =comandService.updateComand(createComandDto, id);
    }

    public ComandDto activateAndGetcomandForIot(){
        logerService.saveLog("Activate new rule on IoT platform ");
        if(comandService.isComandDataListIsEmpty()){
          return stateOfCurendRuleToComandDtoMapper
                  .mapTocomandDto(redisComandService.getCurentRoleWithExpireTime());
        }
        else {
            ComandDto comandDto = comandService.getComandDtoToIot();
            redisComandService.activateIotTimer(comandDto);
            return comandService.getComandDtoToIot();
        }
    }
    public ComandDto findComandById(Long id){

        return comandService.findById(id);
    }

    public void delteLogs(){
        logerService.removeLogs();
    }

    public StateOfCurrentRule getCurentRoleWithExpireTime(){
        return redisComandService.getCurentRoleWithExpireTime();
    }


    public List<String> getAllLogs(){
        return logerService.getAllLogs();
    }








}
