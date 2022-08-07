package pl.edu.pwsztar.service.serviceImpl;

import pl.edu.pwsztar.domain.dto.ComandDto;
import pl.edu.pwsztar.domain.entity.Comand;
import pl.edu.pwsztar.domain.entity.StateOfCurrentRule;

public interface RedisComandService { ;
    Long getExpireTime();
    StateOfCurrentRule getCurentRoleWithExpireTime();
    void activateIotTimer(ComandDto comandDto);



}
