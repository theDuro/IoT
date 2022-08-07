package pl.edu.pwsztar.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.pwsztar.domain.dto.ComandDto;
import pl.edu.pwsztar.domain.entity.StateOfCurrentRule;
import pl.edu.pwsztar.domain.mapper.ComandDtoToStateOfCurrentRule;
import pl.edu.pwsztar.domain.repository.RuleWithTimeVairebleDao;
import pl.edu.pwsztar.logic.TimeHolder;
import pl.edu.pwsztar.domain.entity.Comand;
import pl.edu.pwsztar.domain.entity.RuleWithTime;
import pl.edu.pwsztar.domain.mapper.ComandDtoToRedisComand;
import pl.edu.pwsztar.domain.mapper.ComandToRuleWithTime;
import pl.edu.pwsztar.domain.repository.HashedRuleWitchTimeDao;

import java.util.List;
import java.util.Optional;

@Repository
public class RedisComandSerwiceImpl implements RedisComandService{
    private final HashedRuleWitchTimeDao hashedRuleWitchTimeDao;
    private final ComandDtoToRedisComand comandDtoToRedisComand;
    private final TimeHolder timeHolder;
    private final ComandToRuleWithTime comandToRuleWithTime;
    private final RuleWithTimeVairebleDao ruleWithTimeVairebleDao;
    private final  RuleWithTime ruleWithTime;
    private final ComandDtoToStateOfCurrentRule mapComandDtoToStateOfCurrentRule;

    @Autowired
    public RedisComandSerwiceImpl(HashedRuleWitchTimeDao hashedRuleWitchTimeDao
            , ComandDtoToRedisComand comandDtoToRedisComand
            , TimeHolder timeHolder
            , ComandToRuleWithTime comandToRuleWithTime
            , RuleWithTimeVairebleDao ruleWithTimeVairebleDao
            , RuleWithTime ruleWithTime
            , ComandDtoToStateOfCurrentRule mapComandDtoToStateOfCurrentRule
    ){
        this.hashedRuleWitchTimeDao = hashedRuleWitchTimeDao;
        this.comandDtoToRedisComand = comandDtoToRedisComand;
        this.timeHolder = timeHolder;
        this.comandToRuleWithTime = comandToRuleWithTime;
        this.ruleWithTimeVairebleDao = ruleWithTimeVairebleDao;
        this.ruleWithTime = ruleWithTime;
        this.mapComandDtoToStateOfCurrentRule = mapComandDtoToStateOfCurrentRule;
    }

    public RuleWithTime findById(long id) {

        return   hashedRuleWitchTimeDao.findRuleById(id);
    }



    @Override
    public void delteById(Long id) {
        hashedRuleWitchTimeDao.deleteRule(id);
    }

    @Override
    public void addRedisComand(RuleWithTime ruleWithTime) {
        hashedRuleWitchTimeDao.save(ruleWithTime);

    }



    @Override
    public void updateRedisComand(Comand comand) {
        hashedRuleWitchTimeDao.save(comandToRuleWithTime.comandToRuleWithTime(comand));

    }

    @Override
    public void addRedisComand(Comand comand) {
      hashedRuleWitchTimeDao.save(comandToRuleWithTime.comandToRuleWithTime(comand));
    }

    @Override
    public void addRedisHolderComand(Comand comand) {
        ruleWithTimeVairebleDao.setHoldinRuleWithTime(comandToRuleWithTime.comandToRuleWithTime(comand));
    }



    public List<RuleWithTime> redisGetAllComands(){
        return hashedRuleWitchTimeDao.findAll();
    }




    @Override
    public Integer getTimeFromRedisRule(long id) {

        return hashedRuleWitchTimeDao.findRuleById(id).getExpireTime();
    }



    @Override
    public void activateRedisValueExpire(Long id) {
        if(id != null) {
            RuleWithTime ruleWithTime = hashedRuleWitchTimeDao.findRuleById(id);
            ruleWithTimeVairebleDao.activateRuleandStartExpire(ruleWithTime);
        }
    }

    @Override
    public Long getExpireTime() {
        return ruleWithTimeVairebleDao.getTimeExpire();
    }

    @Override
    public StateOfCurrentRule getCurentRoleWithExpireTime() {
       return ruleWithTimeVairebleDao.getActualRule();
    }

    @Override
    public StateOfCurrentRule activateIotTimer(ComandDto comandDto, Long expire) {
        return null;
    }


}


