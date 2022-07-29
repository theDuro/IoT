package pl.edu.pwsztar.domain.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.entity.Comand;
import pl.edu.pwsztar.domain.entity.RuleWithTime;
@Component
public class ComandToRuleWithTime {
    public RuleWithTime comandToRuleWithTime(Comand comand,Integer expireTime){
        if(expireTime == null){
            expireTime = 60;
        }
        RuleWithTime ruleWithTime = new RuleWithTime();
        ruleWithTime.setId(comand.getComandId());
        System.out.print(comand.getComandId());
        ruleWithTime.setLedLimitedValue(comand.getLedLimitedValue());
        ruleWithTime.setLedFrequency(comand.getLedFrequency());
        ruleWithTime.setEnginePower(comand.getEnginePower());
        ruleWithTime.setExpireTime(expireTime);

        return ruleWithTime;
    }
}
