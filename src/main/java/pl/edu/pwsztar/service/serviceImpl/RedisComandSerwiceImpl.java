package pl.edu.pwsztar.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.pwsztar.logic.TimeHolder;
import pl.edu.pwsztar.domain.entity.Comand;
import pl.edu.pwsztar.domain.entity.RuleWithTime;
import pl.edu.pwsztar.domain.mapper.ComandDtoToRedisComand;
import pl.edu.pwsztar.domain.mapper.ComandToRuleWithTime;
import pl.edu.pwsztar.domain.repository.RuleWithTimeDao;

import java.util.List;

@Repository
public class RedisComandSerwiceImpl implements RedisComandService{
    private final RuleWithTimeDao ruleWithTimeDao;
    private final ComandDtoToRedisComand comandDtoToRedisComand;
    private final TimeHolder timeHolder;
    private final ComandToRuleWithTime comandToRuleWithTime;
    @Autowired
    public RedisComandSerwiceImpl(RuleWithTimeDao ruleWithTimeDao, ComandDtoToRedisComand comandDtoToRedisComand,TimeHolder timeHolder,ComandToRuleWithTime comandToRuleWithTime){
        this.ruleWithTimeDao=ruleWithTimeDao;
        this.comandDtoToRedisComand = comandDtoToRedisComand;
        this.timeHolder = timeHolder;
        this.comandToRuleWithTime = comandToRuleWithTime;
    }

    @Override
    public RuleWithTime findById(long id) {
        return ruleWithTimeDao.findRuleById(id);
    }

    @Override
    public void delteById(Long id) {
        ruleWithTimeDao.deleteRule(id);
    }

    @Override
    public void addRedisComand(RuleWithTime ruleWithTime) {
        ruleWithTimeDao.save(ruleWithTime);
        System.out.print(ruleWithTime.getId() + "/////////////////////////////////////////////////");
    }

    @Override
    public void updateRedisComand(Comand comand, int expireTime) {
        ruleWithTimeDao.save(comandToRuleWithTime.comandToRuleWithTime(comand,expireTime));

    }

    @Override
    public void addRedisComand(Comand comand, int expireTime) {
      ruleWithTimeDao.save(comandToRuleWithTime.comandToRuleWithTime(comand,expireTime));
    }
    public List<RuleWithTime> redisGetAllComands(){
        return ruleWithTimeDao.findAll();
    }




    @Override
    public Integer getTimeFromRedisRule(long id) {
        return ruleWithTimeDao.findRuleById(id).getExpireTime();
    }
}


