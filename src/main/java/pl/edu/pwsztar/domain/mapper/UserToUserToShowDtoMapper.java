package pl.edu.pwsztar.domain.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.dto.UserToShowDto;
import pl.edu.pwsztar.domain.entity.User_;

@Component
public class UserToUserToShowDtoMapper {
    public UserToShowDto userToUserShowDto(User_ user) {
        return new UserToShowDto(user.getUserId(), user.getUsername(), user.getRole());
    }
}
