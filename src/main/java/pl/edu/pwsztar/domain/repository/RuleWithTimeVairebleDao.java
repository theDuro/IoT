package pl.edu.pwsztar.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import pl.edu.pwsztar.domain.dto.ComandDto;
import pl.edu.pwsztar.domain.entity.StateOfCurrentRule;
import pl.edu.pwsztar.domain.mapper.ComandDtoToStateOfCurrentRule;

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


            public StateOfCurrentRule getActualRuleToShow(){
               Integer expire = Math.toIntExact(template.getExpire(VALUE_KEY));
               StateOfCurrentRule stateOfCurrentRule = (StateOfCurrentRule) template.opsForValue().get(VALUE_KEY);
                stateOfCurrentRule.setExpireTime(expire);
               return stateOfCurrentRule;
                //do wyswietlenia na stronie
            }

    public void activateIoTVariable(ComandDto comandDto){
                setValue(comandDtoToStateOfCurrentRule.mapComandDtoToStateOfCurrentRule(comandDto));
                addExpireTime(Long.valueOf(comandDto.getExpire()));
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







    public Long getTimeExpire(){
        return template.getExpire(VALUE_KEY);
    }

    public StateOfCurrentRule getActualRule(){
        StateOfCurrentRule stateOfCurrentRule= (StateOfCurrentRule) template.opsForValue().get(VALUE_KEY);
        stateOfCurrentRule.setExpireTime(Math.toIntExact(template.getExpire(VALUE_KEY)));
        return stateOfCurrentRule;
    }






}
