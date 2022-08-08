package pl.edu.pwsztar.service.serviceImpl;

import pl.edu.pwsztar.domain.dto.ComandDto;
import pl.edu.pwsztar.domain.dto.CreateComandDto;
import pl.edu.pwsztar.domain.entity.Comand;

import java.util.List;

public interface ComandService {

    List<ComandDto> findAll();
    ComandDto findById(Long id);
    Comand addComand(CreateComandDto createComandDto);
    void deleteComand(Long id);
    Comand updateComand(CreateComandDto ComandDto, Long id)  throws NullPointerException;
    ComandDto getComandDtoToIot();
    boolean isComandDataListIsEmpty();


}
