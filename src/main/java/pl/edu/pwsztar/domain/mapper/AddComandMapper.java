package pl.edu.pwsztar.domain.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.dto.CreateComandDto;
import pl.edu.pwsztar.domain.entity.Comand;

//niedokonczone
@Component
public class AddComandMapper {
    public Comand createComandDtoToComandDto(CreateComandDto createComandDto) {

        Comand comand = new Comand();
        comand.setEnginePower(corectTheEmiterSetings(createComandDto.getEnginePower()));
        comand.setLedFrequency(corectTheEmiterSetings(createComandDto.getLedFrequency()));
        comand.setLedLimitedValue(corectTheEmiterSetings((createComandDto.getLedLimitedValue())));
        comand.setUserId(createComandDto.getUserId());
        comand.setExpire(createComandDto.getExpire()==null ? 60 :createComandDto.getExpire());


        return comand;
    }
    private Float corectTheEmiterSetings(Float setting){
        if(setting ==null) return null;
        if(setting >20.0){
            return 20.0F;
        }
        if (setting<0F){
            return 0F;
        }
        return setting;
    }

}
