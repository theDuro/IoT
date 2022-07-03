package pl.edu.pwsztar.domain.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.entity.Comand;
import pl.edu.pwsztar.domain.entity.RuleWithTime;
@Component
public class ComandToRuleWithTime {
    public RuleWithTime comandToRuleWithTime(Comand comand,int expireTime){
        RuleWithTime ruleWithTime = new RuleWithTime();
        ruleWithTime.setId(comand.getComandId());
        ruleWithTime.setLedLimitedValue(comand.getLedLimitedValue());
        ruleWithTime.setLedFrequency(comand.getLedFrequency());
        ruleWithTime.setEnginePower(comand.getEnginePower());
        ruleWithTime.setExpireTime(expireTime);

        return ruleWithTime;
    }
}
