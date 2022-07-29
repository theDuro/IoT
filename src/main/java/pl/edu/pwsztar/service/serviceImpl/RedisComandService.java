package pl.edu.pwsztar.service.serviceImpl;

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
    void updateRedisComand(Comand comand,int expireTime);
    void addRedisComand(Comand comand, int expireTime);
    void addRedisHolderComand(Comand comand, int expireTime);
    void setActualHoldingIdForIoT(Long id);
    Integer getTimeFromRedisRule(long id);

    void activateRedisValueExpire(Long id);
    Long getExpireTime();
    StateOfCurrentRule getCurentRoleWithExpireTime();



}
