package pl.edu.pwsztar.service.serviceImpl;

import pl.edu.pwsztar.domain.dto.ComandDto;
import pl.edu.pwsztar.domain.entity.Comand;
import pl.edu.pwsztar.domain.entity.RuleWithTime;
import pl.edu.pwsztar.domain.entity.StateOfCurrentRule;

import java.util.List;
import java.util.Optional;

public interface RedisComandService {
    List<RuleWithTime> redisGetAllComands();
    RuleWithTime findById(long id);
    void delteById(Long id);
    void addRedisComand(RuleWithTime ruleWithTime);
    void updateRedisComand(Comand comand);
    void addRedisComand(Comand comand);
    void addRedisHolderComand(Comand comand);
    Integer getTimeFromRedisRule(long id);

    void activateRedisValueExpire(Long id);
    Long getExpireTime();
    StateOfCurrentRule getCurentRoleWithExpireTime();
    StateOfCurrentRule activateIotTimer(ComandDto comandDto, Long expire);



}
