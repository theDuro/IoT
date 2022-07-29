package pl.edu.pwsztar.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import pl.edu.pwsztar.domain.entity.RuleWithTime;
import pl.edu.pwsztar.domain.entity.StateOfCurrentRule;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
public class RuleWithTimeVairebleDao {
    public static final String VALUE_KEY  = "ValueOfActualIot";
    public static final String VALUE_OF_ACTUAL_ID_FOR_IOT ="Actual_Id_Holder";
    private  final RedisTemplate template;
    @Autowired
    RuleWithTimeVairebleDao(RedisTemplate template){
        this.template = template;
    }

    public void setValueWitchIdKey(Long id,StateOfCurrentRule stateOfCurrentRule){
        template.opsForValue().set(id,stateOfCurrentRule);
    }

    private void setValue(StateOfCurrentRule stateOfCurrentRule){
        template.opsForValue().set(VALUE_KEY,stateOfCurrentRule );
    }

    private void addExpireTime(int time){
        template.expire(VALUE_KEY,time, TimeUnit.SECONDS);

    }
    public void setActualIdForIotIRedis(Long id){
        template.opsForValue().set(VALUE_OF_ACTUAL_ID_FOR_IOT,id);
    }

    public void setHoldinRuleWithTime(RuleWithTime ruleWithTime){
        ruleWithTime.setExpireTime(ruleWithTime.getExpireTime()==null ? 30 : ruleWithTime.getExpireTime());
        template.opsForValue().set(ruleWithTime.getId(),ruleWithTime);
    }


    public void activateRuleandStartExpire(RuleWithTime ruleWithTime){

       StateOfCurrentRule stateOfCurrentRule = new StateOfCurrentRule();
       stateOfCurrentRule.setLedLimitedValue(ruleWithTime.getLedLimitedValue());
       stateOfCurrentRule.setExpireTime(ruleWithTime.getExpireTime()==null ? 30 : ruleWithTime.getExpireTime());
       stateOfCurrentRule.setLedFrequency(stateOfCurrentRule.getLedFrequency());
       stateOfCurrentRule.setEnginePower(stateOfCurrentRule.getEnginePower());

       setValue(stateOfCurrentRule);
       addExpireTime(ruleWithTime.getExpireTime()==null ? 30 : ruleWithTime.getExpireTime());
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
