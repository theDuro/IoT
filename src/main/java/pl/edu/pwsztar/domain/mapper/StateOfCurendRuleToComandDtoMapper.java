package pl.edu.pwsztar.domain.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.dto.ComandDto;
import pl.edu.pwsztar.domain.entity.StateOfCurrentRule;
@Component
public class StateOfCurendRuleToComandDtoMapper {
    private final static int BASE_EXPIRE_VALUE = 60;
    public ComandDto mapTocomandDto(StateOfCurrentRule stateOfCurrentRule){
        ComandDto comandDto = new ComandDto();
        comandDto.setExpire(BASE_EXPIRE_VALUE);
        comandDto.setLedLimitedValue(stateOfCurrentRule.getLedLimitedValue());
        comandDto.setLedFrequency(stateOfCurrentRule.getLedFrequency());
        comandDto.setEnginePower(stateOfCurrentRule.getEnginePower());

   return comandDto;
    }


}
