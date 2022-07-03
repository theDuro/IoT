package pl.edu.pwsztar.domain.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.dto.ComandDto;
import pl.edu.pwsztar.domain.dto.CreateComandDto;
import pl.edu.pwsztar.domain.entity.RuleWithTime;

@Component
public class ComandDtoToRedisComand {
    public RuleWithTime maapingComandDotToRuleWithTime(CreateComandDto createComandDto, long id){
        RuleWithTime ruleWithTime = new RuleWithTime();
        ruleWithTime.setEnginePower(createComandDto.getEnginePower());
        ruleWithTime.setLedFrequency(createComandDto.getLedFrequency());
        ruleWithTime.setLedLimitedValue(createComandDto.getLedLimitedValue());
        ruleWithTime.setId(id);
        return ruleWithTime;

    }


}
