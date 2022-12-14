package server.partyLuckyDraw.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", typeConversionPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @Mapping(source = "username.username", target = "name")
    User toUser(UserDto.RequestUserSimpleUser username);

    @Mapping(source = "user.id", target = "userId")
    UserDto.ResponseUserId toUserId(User user);

    @Mapping(source = "user.name", target = "username")
    @Mapping(source = "user.id", target = "userId")
    UserDto.ResponseSimpleUser toSimpleUserDto(User user);
}
