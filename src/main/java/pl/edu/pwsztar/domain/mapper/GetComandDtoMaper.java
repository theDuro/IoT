package pl.edu.pwsztar.domain.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.dto.ComandDto;
import pl.edu.pwsztar.domain.entity.Comand;
@Component
public class GetComandDtoMaper {
    public ComandDto mapToDto(Comand comand) {



            ComandDto comandDto = new ComandDto();
            comandDto.setComandId(comand.getComandId());
            comandDto.setEnginePower(comand.getEnginePower());
            comandDto.setLedFrequency(comand.getLedFrequency());
            comandDto.setLedLimitedValue(comand.getLedLimitedValue());
            comandDto.setUserId(comand.getUserId());
            comandDto.setExpire(comand.getExpire());



        return comandDto;
    }

}
