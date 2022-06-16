package pl.edu.pwsztar.domain.mapper;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.entity.User_;
@Component


public class UserToUserDtoMaper {
    public String  mapToString(@NotNull User_ user){

        return user.getFirstName();

    }


}
