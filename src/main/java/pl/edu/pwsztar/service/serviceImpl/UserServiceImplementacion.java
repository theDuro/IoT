package pl.edu.pwsztar.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.domain.dto.UserRegistrationDto;
import pl.edu.pwsztar.domain.entity.User_;
import pl.edu.pwsztar.domain.mapper.AddNewUsserMapper;
import pl.edu.pwsztar.domain.mapper.UserToUserDtoMaper;
import pl.edu.pwsztar.domain.repository.UserRepository;

@Service
public class UserServiceImplementacion implements UserService {
    private final UserRepository userRepository;
    private final AddNewUsserMapper addNewUsserMapper;
    private final UserToUserDtoMaper userToUserDtoMaper;


    public UserServiceImplementacion(UserRepository userRepository,
                                     AddNewUsserMapper addNewUsserMapper,
                                     UserToUserDtoMaper userToUserDtoMaper) {
        this.userRepository = userRepository;
        this.addNewUsserMapper = addNewUsserMapper;
        this.userToUserDtoMaper = userToUserDtoMaper;
    }

    @Override
    public String getUserNamebyId(Long id) {
        //todo maper User) -> UserDto
        String fakeuser = userToUserDtoMaper.mapToString(userRepository.getOne(id));
        System.out.println(fakeuser);
        return fakeuser;//userRepository.getOne(id).getFirstName();
        ///
    }

    @Override
    public void addUser(UserRegistrationDto userRegistrationDto) {
       User_ u = userRepository.save(addNewUsserMapper.createUsserDtoToUser(userRegistrationDto));



    }



////////////
    @Override
    public void changeToken(String token,String login) {
   userRepository.updateNewToken(login,token);


    }

    @Override
    public String checkIsUnikueUser(String login) {

        return userRepository.itNickIsFrea(login);
    }

    @Override
    public String getNickById(long id) {
       return userRepository.getNickByID(id);


    }

    @Override
    public void addNewUser(UserRegistrationDto userRegistrationDto) {
        User_ u = userRepository.save(addNewUsserMapper.createUsserDtoToUser(userRegistrationDto));


    }
    @Override
    public void addTest() {
        User_ user = userRepository.save(userRepository.getOne(1L));


    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User_ user = userRepository.findByFirstName(s)
                .orElseThrow(() -> new UsernameNotFoundException("There is no user with name: " + s));
        return user;
    }
}
