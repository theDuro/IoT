package pl.edu.pwsztar.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.dto.CreateComandDto;
import pl.edu.pwsztar.service.serviceImpl.ComandService;

@Component
public class DataLoader implements CommandLineRunner {

    private final ComandService comandService;

    public DataLoader(ComandService comandService) {
        this.comandService = comandService;
    }


    @Override
    public void run(String... args) {

        CreateComandDto comand1 = new CreateComandDto(1.0F, 2.0F, 3.0F,100, 2L);
        CreateComandDto comand2 = new CreateComandDto(4.0F, 1.0F, 1.2F,100, 3L);
        CreateComandDto comand3 = new CreateComandDto(3.0F, 1.0F, 3.3F,100, 3L);
        CreateComandDto comand4 = new CreateComandDto(4.0F, 2.0F, 6.3F,100, 4L);
        CreateComandDto comand5 = new CreateComandDto(5.0F, 6, 5.3F,100, 1L);
        //comandService.addComand(comand1);
        //comandService.addComand(comand2);
       // comandService.addComand(comand3);
       // comandService.addComand(comand4);

        //comandService.addComand(comand5);



    }
}
