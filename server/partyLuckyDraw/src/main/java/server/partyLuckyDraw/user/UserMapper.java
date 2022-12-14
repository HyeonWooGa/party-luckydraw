package server.partyLuckyDraw.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", typeConversionPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @Mapping(source = "userName.userName", target = "name")
    User toUser(UserDto.RequestUserSimpleUser userName);

    @Mapping(source = "user.id", target = "userId")
    UserDto.ResponseUserId toUserId(User user);

    @Mapping(source = "user.name", target = "userName")
    @Mapping(source = "user.id", target = "userId")
    UserDto.ResponseSimpleUser toSimpleUser(User user);
}
