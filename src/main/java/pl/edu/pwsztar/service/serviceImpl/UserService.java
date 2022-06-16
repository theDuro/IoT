package pl.edu.pwsztar.service.serviceImpl;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.edu.pwsztar.domain.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

    String getUserNamebyId(Long id);
    void addUser(UserRegistrationDto userRegistrationDto);
    void changeToken(String token,String Login);
    String checkIsUnikueUser(String login);///////
    String getNickById(long id);
    void addNewUser(UserRegistrationDto userRegistrationDto);
    void addTest();
}
