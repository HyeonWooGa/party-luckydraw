package server.partyLuckyDraw.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto.ResponseUserId registerUserName(UserDto.RequestUserSimpleUser userName) {
        User user = userMapper.toUser(userName);
        userRepository.save(user);
        return userMapper.toUserId(user);
    }

    public List<UserDto.ResponseSimpleUser> getUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toSimpleUser)
                .collect(Collectors.toList());
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
