package pl.edu.pwsztar.domain.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.dto.ComandDto;
import pl.edu.pwsztar.domain.entity.StateOfCurrentRule;
@Component
public class ComandDtoToStateOfCurrentRule {
    public StateOfCurrentRule mapComandDtoToStateOfCurrentRule(ComandDto comandDto){
        StateOfCurrentRule stateOfCurrentRule = new StateOfCurrentRule();
        stateOfCurrentRule.setExpireTime(comandDto.getExpire() == null ? 60 : comandDto.getExpire());//
        stateOfCurrentRule.setEnginePower(comandDto.getEnginePower());
        stateOfCurrentRule.setLedFrequency(comandDto.getLedFrequency());
        stateOfCurrentRule.setLedLimitedValue(comandDto.getLedLimitedValue());
        stateOfCurrentRule.setUserId(comandDto.getUserId());
        stateOfCurrentRule.setCommandid(comandDto.getComandId());
        return  stateOfCurrentRule;
    }

}
