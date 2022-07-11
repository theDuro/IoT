package pl.edu.pwsztar.service.serviceImpl;

import pl.edu.pwsztar.domain.dto.CreateComandDto;
import pl.edu.pwsztar.domain.entity.Comand;
import pl.edu.pwsztar.domain.entity.RuleWithTime;

import java.util.List;

public interface RedisComandService {
    List<RuleWithTime> redisGetAllComands();
    RuleWithTime findById(long id);
    void delteById(Long id);
    void addRedisComand(RuleWithTime ruleWithTime);
    void updateRedisComand(Comand comand,int expireTime);
    void addRedisComand(Comand comand, int expireTime);
    Integer getTimeFromRedisRule(long id);

}
