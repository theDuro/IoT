package pl.edu.pwsztar.domain;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pwsztar.domain.dto.ComandDto;
import pl.edu.pwsztar.domain.dto.CreateComandDto;
import pl.edu.pwsztar.domain.entity.Comand;
import pl.edu.pwsztar.domain.repository.RedisLogerDao;
import pl.edu.pwsztar.service.serviceImpl.ComandService;
import pl.edu.pwsztar.service.serviceImpl.LogerService;
import pl.edu.pwsztar.service.serviceImpl.RedisComandService;
import pl.edu.pwsztar.service.serviceImpl.UserService;

import java.util.List;

public class ComandFasade {
    private final ComandService comandService;
    private final UserService userService;
    private final RedisComandService redisComandService;
    private final LogerService logerService;

    @Autowired
    public ComandFasade(ComandService comandService, UserService userService,RedisComandService redisComandService,LogerService logerService) {

        this.comandService = comandService;
        this.userService = userService;
        this.redisComandService = redisComandService;
        this.logerService = logerService;
    }
    public List<ComandDto> getAllComands(){
        return comandService.findAll();
    }

    public void createComand(CreateComandDto createComandDto,Integer expire){
        Comand comand = comandService.addComand(createComandDto);
        redisComandService.addRedisComand(comand,expire);
    }

    public void delteComand(Long id){
        comandService.deleteComand(id);
        redisComandService.delteById(id);
    }




}
