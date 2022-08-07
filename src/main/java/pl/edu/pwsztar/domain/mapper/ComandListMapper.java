package pl.edu.pwsztar.domain.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.dto.ComandDto;
import pl.edu.pwsztar.domain.entity.Comand;

import java.util.ArrayList;
import java.util.List;

@Component
public class ComandListMapper {

    public List<ComandDto> mapToDto(List<Comand> comands) {
        List<ComandDto> comandsDto = new ArrayList<>();

        for(Comand comand: comands) {
           ComandDto comandDto = new ComandDto();
           comandDto.setComandId(comand.getComandId());
           comandDto.setEnginePower(comand.getEnginePower());
           comandDto.setLedFrequency(comand.getLedFrequency());
           comandDto.setLedLimitedValue(comand.getLedLimitedValue());
           comandDto.setUserId(comand.getUserId());
           comandDto.setExpire(comand.getExpire());

            comandsDto.add(comandDto);

        }

        return comandsDto;
    }
}
