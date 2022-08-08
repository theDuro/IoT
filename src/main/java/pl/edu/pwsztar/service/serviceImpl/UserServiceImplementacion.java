package pl.edu.pwsztar.service.serviceImpl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.domain.dto.UserRegistrationDto;
import pl.edu.pwsztar.domain.dto.UserToShowDto;
import pl.edu.pwsztar.domain.entity.User_;
import pl.edu.pwsztar.domain.mapper.AddNewUsserMapper;
import pl.edu.pwsztar.domain.mapper.UserToUserDtoMaper;
import pl.edu.pwsztar.domain.mapper.UserToUserToShowDtoMapper;
import pl.edu.pwsztar.domain.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementacion implements UserService {
    private  final UserRepository userRepository;
    private final AddNewUsserMapper addNewUsserMapper;
    private final UserToUserDtoMaper userToUserDtoMaper;
    private final UserToUserToShowDtoMapper userToUserToShowDtoMapper;

    public UserServiceImplementacion(
            UserRepository userRepository
            ,AddNewUsserMapper addNewUsserMapper
            ,UserToUserDtoMaper userToUserDtoMaper
            ,UserToUserToShowDtoMapper userToUserToShowDtoMapper
             ) {
        this.userRepository = userRepository;
        this.addNewUsserMapper = addNewUsserMapper;
        this.userToUserDtoMaper = userToUserDtoMaper;
        this.userToUserToShowDtoMapper = userToUserToShowDtoMapper;

    }

    @Override
        public  String getUserNamebyId(Long id) {
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



    @Override
    public String checkIsUnikueUser(String login) {

        return userRepository.itNickIsFrea(login);
    }

    @Override
    public String getNickById(long id) {
       return getUserNamebyId(id);


    }
    public String getNickByIdForMapping(long id) {
        try {
            return  userRepository.getNickByID(id);
        } catch (Exception e){
            return "Anonim";
        }

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
    public List<UserToShowDto> getAllUsers() {
         return userRepository
                 .findAll()
                 .stream()
                 .map(userToUserToShowDtoMapper::userToUserShowDto)
                 .collect(Collectors.toList());

    }

    @Override
    public void delteUserById(Long id) {
      userRepository.deleteById(id);
    }




    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User_ user = userRepository.findByFirstName(s)
                .orElseThrow(() -> new UsernameNotFoundException("There is no user with name: " + s));
        return user;
    }
}
