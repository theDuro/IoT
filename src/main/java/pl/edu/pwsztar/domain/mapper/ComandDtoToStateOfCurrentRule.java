package pl.edu.pwsztar.domain.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.dto.ComandDto;
import pl.edu.pwsztar.domain.entity.StateOfCurrentRule;
@Component
public class ComandDtoToStateOfCurrentRule {
    public StateOfCurrentRule mapComandDtoToStateOfCurrentRule(ComandDto comandDto,Integer expireTime){
        StateOfCurrentRule stateOfCurrentRule = new StateOfCurrentRule();
        stateOfCurrentRule.setExpireTime(expireTime == null ? 60 : expireTime);//
        stateOfCurrentRule.setEnginePower(comandDto.getEnginePower());
        stateOfCurrentRule.setLedFrequency(comandDto.getLedFrequency());
        stateOfCurrentRule.setLedLimitedValue(comandDto.getLedLimitedValue());
        stateOfCurrentRule.setUserId(comandDto.getUserId());
        return  stateOfCurrentRule;
    }

}
