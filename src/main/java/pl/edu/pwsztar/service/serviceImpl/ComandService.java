package pl.edu.pwsztar.service.serviceImpl;

import pl.edu.pwsztar.domain.dto.ComandDto;
import pl.edu.pwsztar.domain.dto.CreateComandDto;

import java.util.List;

public interface ComandService {

    List<ComandDto> findAll();
    ComandDto findById(Long id);
    void addComand(CreateComandDto createComandDto);
    void deleteComand(Long id);
    void updateComand(CreateComandDto ComandDto, Long id)  throws NullPointerException;
    ComandDto getComandDtoToIot();
}
