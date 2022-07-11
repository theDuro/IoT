package pl.edu.pwsztar.domain;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pwsztar.service.serviceImpl.ComandService;
import pl.edu.pwsztar.service.serviceImpl.RedisComandService;
import pl.edu.pwsztar.service.serviceImpl.UserService;

public class ComandFasade {
    private final ComandService comandService;
    private final UserService userService;
    private final RedisComandService redisComandService;

    @Autowired
    public ComandFasade(ComandService comandService, UserService userService,RedisComandService redisComandService) {

        this.comandService = comandService;
        this.userService = userService;
        this.redisComandService = redisComandService;
    }
    
}
