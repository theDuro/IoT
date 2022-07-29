package pl.edu.pwsztar.service.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.domain.dto.ComandDto;
import pl.edu.pwsztar.domain.dto.CreateComandDto;
import pl.edu.pwsztar.domain.entity.Comand;
import pl.edu.pwsztar.domain.entity.StateOfCurrentRule;
import pl.edu.pwsztar.domain.mapper.AddComandMapper;
import pl.edu.pwsztar.domain.mapper.ComandListMapper;
import pl.edu.pwsztar.domain.mapper.GetComandDtoMaper;
import pl.edu.pwsztar.domain.repository.ComandRepository;

import java.util.List;

@Service
public class ComandServiceImpl implements ComandService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComandServiceImpl.class);

    private final ComandRepository comandRepository;
    private final ComandListMapper comandListMapper;
    private final AddComandMapper addComandMapper;
    private final GetComandDtoMaper getComandDtoMaper;


    @Autowired
    public ComandServiceImpl(
            ComandRepository comandRepository
            ,ComandListMapper comandListMapper
            ,AddComandMapper addComandMapper
            , GetComandDtoMaper getComandDtoMaper

    ) {

        this.comandRepository = comandRepository;
        this.comandListMapper = comandListMapper;
        this.addComandMapper = addComandMapper;
        this.getComandDtoMaper = getComandDtoMaper;

    }

    @Override
    public List<ComandDto> findAll() {
        List<Comand> comands = comandRepository.findAll();
        return comandListMapper.mapToDto(comands);
    }

    @Override
    public ComandDto findById(Long id) {
        Comand comand = comandRepository.findById(id).orElse(null);

        ComandDto comandDto = new ComandDto();
        comandDto.setComandId(comand.getComandId());
        comandDto.setEnginePower(comand.getEnginePower());
        comandDto.setLedFrequency(comand.getLedFrequency());
        comandDto.setLedLimitedValue(comand.getLedLimitedValue());
        comandDto.setUserId(comand.getUserId());
        return comandDto;
    }

    @Override
    public Comand addComand(CreateComandDto createComandDto) {

       Comand comand = comandRepository.save(addComandMapper.createComandDtoToComandDto(createComandDto));
              return comand ;
    }

    @Override
    public void deleteComand(Long id) {
       comandRepository.deleteById(id);
    }

    


    @Override
    public Comand updateComand(CreateComandDto createComandDto, Long id)  throws NullPointerException  {
        if (id == null)

            throw new NullPointerException();
         Comand comand = addComandMapper.createComandDtoToComandDto(createComandDto);
         comand.setComandId(id);
         comandRepository.save(comand);
         return  comand;

    }



    @Override
    public ComandDto getComandDtoToIot() {
         Comand comand =comandRepository.findAll().get(0);
         comandRepository.deleteById(comand.getComandId());
        return getComandDtoMaper.mapToDto(comand);


    }


}
