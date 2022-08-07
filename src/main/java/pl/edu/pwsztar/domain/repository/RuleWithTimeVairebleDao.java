package pl.edu.pwsztar.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import pl.edu.pwsztar.domain.dto.ComandDto;
import pl.edu.pwsztar.domain.entity.RuleWithTime;
import pl.edu.pwsztar.domain.entity.StateOfCurrentRule;
import pl.edu.pwsztar.domain.mapper.ComandDtoToStateOfCurrentRule;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
public class RuleWithTimeVairebleDao {
    public static final String VALUE_KEY  = "ValueOfActualIot";
    private  final RedisTemplate template;
    private final ComandDtoToStateOfCurrentRule comandDtoToStateOfCurrentRule;
            @Autowired
    RuleWithTimeVairebleDao(RedisTemplate template,ComandDtoToStateOfCurrentRule comandDtoToStateOfCurrentRule){
        this.template = template;
        this.comandDtoToStateOfCurrentRule = comandDtoToStateOfCurrentRule;
    }
    public void setIotExpire(long id){
                StateOfCurrentRule stateOfCurrentRule = (StateOfCurrentRule) template.opsForValue().get(id);
                template.opsForValue().set(VALUE_KEY,stateOfCurrentRule);
                template.expire(VALUE_KEY,stateOfCurrentRule.getExpireTime(),TimeUnit.SECONDS);
                template.delete(stateOfCurrentRule.getCommandid());
            }

            public StateOfCurrentRule getActualRuleToShow(){
               Integer expire = Math.toIntExact(template.getExpire(VALUE_KEY));
               StateOfCurrentRule stateOfCurrentRule = (StateOfCurrentRule) template.opsForValue().get(VALUE_KEY);
                stateOfCurrentRule.setExpireTime(expire);
               return stateOfCurrentRule;
                //do wyswietlenia na stronie
            }



    private void setValue(StateOfCurrentRule stateOfCurrentRule){
        template.opsForValue().set(VALUE_KEY,stateOfCurrentRule );
    }

    private void addExpireTime(Long time){
        template.expire(VALUE_KEY,time, TimeUnit.SECONDS);
    }

    public void  turnOffExpire(){
                StateOfCurrentRule stateOfCurrentRule = (StateOfCurrentRule) template.opsForValue().get(VALUE_KEY);
                setValue(stateOfCurrentRule);
    }



    public void activateRuleandStartExpire(RuleWithTime ruleWithTime){

       StateOfCurrentRule stateOfCurrentRule = new StateOfCurrentRule();
       stateOfCurrentRule.setLedLimitedValue(ruleWithTime.getLedLimitedValue());
       stateOfCurrentRule.setExpireTime(ruleWithTime.getExpireTime()==null ? 30 : ruleWithTime.getExpireTime());
       stateOfCurrentRule.setLedFrequency(stateOfCurrentRule.getLedFrequency());
       stateOfCurrentRule.setEnginePower(stateOfCurrentRule.getEnginePower());

       setValue(stateOfCurrentRule);
       addExpireTime(ruleWithTime.getExpireTime()==null ? 30L : ruleWithTime.getExpireTime());
    }



    public Long getTimeExpire(){
        return template.getExpire(VALUE_KEY);
    }

    public StateOfCurrentRule getActualRule(){
        StateOfCurrentRule stateOfCurrentRule= (StateOfCurrentRule) template.opsForValue().get(VALUE_KEY);
        stateOfCurrentRule.setExpireTime(Math.toIntExact(template.getExpire(VALUE_KEY)));
        return stateOfCurrentRule;
    }






}
