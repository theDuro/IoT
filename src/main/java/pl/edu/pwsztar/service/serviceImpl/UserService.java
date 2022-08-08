package pl.edu.pwsztar.service.serviceImpl;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.edu.pwsztar.domain.dto.ComandDto;
import pl.edu.pwsztar.domain.dto.UserRegistrationDto;
import pl.edu.pwsztar.domain.dto.UserToShowDto;
import pl.edu.pwsztar.domain.entity.StateOfCurrentRule;
import pl.edu.pwsztar.domain.entity.User_;

import java.util.List;

public interface UserService extends UserDetailsService {

    String getUserNamebyId(Long id) ;
    void addUser(UserRegistrationDto userRegistrationDto);
    String checkIsUnikueUser(String login);///////
    String getNickById(long id);
    void addNewUser(UserRegistrationDto userRegistrationDto);
    void addTest();
    List<UserToShowDto> getAllUsers();
    void delteUserById(Long id);


}
