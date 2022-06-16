package pl.edu.pwsztar.domain.mapper;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.dto.UserRegistrationDto;
import pl.edu.pwsztar.domain.entity.Comand;
import pl.edu.pwsztar.domain.entity.User_;


@Component
public class AddNewUsserMapper {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AddNewUsserMapper(@Lazy BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User_ createUsserDtoToUser(UserRegistrationDto userRegistrationDto) {
        User_ user = new User_();
        user.setFirstName(userRegistrationDto.getFirstName());
        user.setPassword(bCryptPasswordEncoder.encode(userRegistrationDto.getPassword()));
        //todo zmenic sendEmail na Token



        return user;
    }


}
